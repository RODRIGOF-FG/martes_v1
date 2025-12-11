package com.example.sistema_procesos.Admin;

import com.example.sistema_procesos.User.Role;
import com.example.sistema_procesos.User.User;
import com.example.sistema_procesos.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // -------- SANITIZAR --------
    private String sanitize(String s) {
        if (s == null) return null;
        return s.replaceAll("<[^>]*>", "").trim();
    }

    private UserResponse toResponse(User u) {
        boolean pwSet = u.getPassword() != null && !u.getPassword().isBlank();
        String masked = pwSet ? "******" : null;

        return new UserResponse(
                u.getId(),
                u.getFirstname(),
                u.getLastname(),
                u.getEmail(),
                u.getCountry(),
                u.getRole(),
                pwSet,
                masked
        );
    }

    // -------- GET ALL --------
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // -------- GET BY ID --------
    public UserResponse getUserById(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return toResponse(u);
    }

    // -------- CREATE USER --------
    @Transactional
    public UserResponse createUser(CreateUserRequest req) {

        if (req.getEmail() == null || req.getEmail().isBlank())
            throw new BadRequestException("Email obligatorio");

        String email = sanitize(req.getEmail()).toLowerCase();

        if (userRepository.findByEmail(email).isPresent())
            throw new ConflictException("El correo ya está registrado");

        String roleNormalized = req.normalizedRole();
        Role roleEnum = Role.valueOf(roleNormalized);

        User user = User.builder()
                .firstname(sanitize(req.getFirstname()))
                .lastname(sanitize(req.getLastname()))
                .country(sanitize(req.getCountry()))
                .email(email)
                .username(email)
                .password(passwordEncoder.encode(req.getPassword()))
                .role(roleEnum)
                .build();

        userRepository.save(user);
        return toResponse(user);
    }

    // -------- UPDATE USER --------
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest req) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // ----- EMAIL -----
        if (req.getEmail() != null && !req.getEmail().isBlank()) {

            String email = sanitize(req.getEmail()).toLowerCase();

            // Busca si otro usuario tiene el correo
            userRepository.findByEmail(email).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new ConflictException("El correo ya está registrado por otro usuario");
                }
            });

            // Si es el mismo correo o está libre → asignar
            user.setEmail(email);
            user.setUsername(email);
        }

        // ----- OTROS CAMPOS -----
        if (req.getFirstname() != null) user.setFirstname(sanitize(req.getFirstname()));
        if (req.getLastname() != null) user.setLastname(sanitize(req.getLastname()));
        if (req.getCountry() != null) user.setCountry(sanitize(req.getCountry()));

        // ----- PASSWORD -----
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }

        // ----- ROLE -----
        if (req.getRole() != null && !req.getRole().isBlank()) {
            String normalized = (new CreateUserRequest() {{
                setRole(req.getRole());
            }}).normalizedRole();

            user.setRole(Role.valueOf(normalized));
        }

        userRepository.save(user);
        return toResponse(user);
    }

    // -------- DELETE USER --------
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("Usuario no existe");

        userRepository.deleteById(id);
    }

    // ---- EXCEPTIONS ----
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String m) { super(m); }
    }

    public static class ConflictException extends RuntimeException {
        public ConflictException(String m) { super(m); }
    }

    public static class BadRequestException extends RuntimeException {
        public BadRequestException(String m) { super(m); }
    }
}

package com.example.sistema_procesos.asistencia;

import com.example.sistema_procesos.User.Role;
import com.example.sistema_procesos.User.User;
import com.example.sistema_procesos.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository repository;
    private final UserRepository userRepository;

    // ✅ REGISTRAR ASISTENCIA
    public Asistencia registrar(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getRole() != Role.ROLE_OPERADOR) {
            throw new RuntimeException("Solo operadores pueden registrar asistencia");
        }

        LocalDate hoy = LocalDate.now();

        if (repository.existsByUserAndFecha(user, hoy)) {
            throw new RuntimeException("Ya registraste asistencia hoy");
        }

        String nombreCompleto = user.getFirstname() + " " + user.getLastname();

        Asistencia asistencia = Asistencia.builder()
                .user(user) // ✅ Hibernate guarda el user_id solo
                .nombreUsuario(nombreCompleto)
                .fecha(hoy)
                .build();

        return repository.save(asistencia);
    }

    // ✅ VER MIS ASISTENCIAS
    public List<Asistencia> misAsistencias(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return repository.findByUser(user);
    }
}

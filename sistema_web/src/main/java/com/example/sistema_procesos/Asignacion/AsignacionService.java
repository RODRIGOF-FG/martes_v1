package com.example.sistema_procesos.Asignacion;

import com.example.sistema_procesos.User.User;
import com.example.sistema_procesos.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AsignacionService {

    private final AsignacionRepository repository;
    private final UserRepository userRepository;

    public Asignacion asignar(CreateAsignacionRequest req) {

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Asignacion a = new Asignacion();
        a.setUserId(user.getId());
        a.setEmail(user.getEmail());
        a.setFirstname(user.getFirstname());
        a.setLastname(user.getLastname());
        a.setRole(user.getRole());
        a.setArea(req.toAreaEnum());
        a.setTareasAsignadas(req.getTareas());
        a.setTareasCompletadas(null);
        a.setFechaAsignacion(LocalDateTime.now());
        a.setFechaActualizacion(LocalDateTime.now());

        return repository.save(a);
    }

    public Asignacion actualizar(Long id, UpdateAsignacionRequest req) {

        Asignacion a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        a.setArea(req.toAreaEnum());
        a.setTareasAsignadas(req.getTareas());
        a.setFechaActualizacion(LocalDateTime.now());

        return repository.save(a);
    }

    public Asignacion obtenerPorUserId(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No tiene asignación"));
    }

    public Asignacion completarTareas(Long userId, CompletarTareasRequest req) {

        Asignacion a = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

        a.setTareasCompletadas(req.getTareasCompletadas());
        a.setFechaActualizacion(LocalDateTime.now());

        return repository.save(a);
    }

    public java.util.List<Asignacion> listarTodo() {
        return repository.findAll();
    }
}

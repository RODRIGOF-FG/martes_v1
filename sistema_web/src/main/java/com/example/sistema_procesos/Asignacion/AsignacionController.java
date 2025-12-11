package com.example.sistema_procesos.Asignacion;

import com.example.sistema_procesos.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/asignar")
@RequiredArgsConstructor
public class AsignacionController {

    private final AsignacionService service;
    private final UserRepository userRepository;

    // ADMIN — crear asignación
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CreateAsignacionRequest req) {
        return ResponseEntity.ok(service.asignar(req));
    }

    // ADMIN — actualizar asignación
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody UpdateAsignacionRequest req) {
        return ResponseEntity.ok(service.actualizar(id, req));
    }

    // ADMIN — ver todas las asignaciones
    @GetMapping("/todas")
    public ResponseEntity<?> todas() {
        return ResponseEntity.ok(service.listarTodo());
    }

    // OPERADOR — ver su asignación
    @GetMapping("/mi-asignacion")
    public ResponseEntity<?> miAsignacion(Authentication auth) {

        String email = auth.getName();
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"))
                .getId();

        return ResponseEntity.ok(service.obtenerPorUserId(userId));
    }

    // OPERADOR — completar tareas
    @PostMapping("/completar")
    public ResponseEntity<?> completar(Authentication auth, @RequestBody CompletarTareasRequest req) {

        String email = auth.getName();
        Long userId = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"))
                .getId();

        return ResponseEntity.ok(service.completarTareas(userId, req));
    }
}

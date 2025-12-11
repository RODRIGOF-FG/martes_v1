package com.example.sistema_procesos.asistencia;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operador/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    // ✅ REGISTRAR ASISTENCIA
    @PostMapping
    public ResponseEntity<?> registrar(Authentication auth) {

        String email = auth.getName();
        return ResponseEntity.ok(service.registrar(email));
    }

    // ✅ VER MIS ASISTENCIAS
    @GetMapping("/mis-asistencias")
    public ResponseEntity<?> misAsistencias(Authentication auth) {

        String email = auth.getName();
        return ResponseEntity.ok(service.misAsistencias(email));
    }
}

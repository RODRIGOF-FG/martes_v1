package com.example.sistema_procesos.admin_asistencia;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/asistencias")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAsistenciaController {

    private final AdminAsistenciaService service;

    @GetMapping
    public List<AdminAsistenciaResponse> listarTodas() {
        return service.listarTodas();
    }
}

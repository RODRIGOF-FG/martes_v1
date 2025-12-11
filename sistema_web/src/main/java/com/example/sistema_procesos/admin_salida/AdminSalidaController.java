package com.example.sistema_procesos.admin_salida;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/salida")
@RequiredArgsConstructor
public class AdminSalidaController {

    private final AdminSalidaService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminSalida> verTodasLasSalidas() {
        return service.listarTodas();
    }
}

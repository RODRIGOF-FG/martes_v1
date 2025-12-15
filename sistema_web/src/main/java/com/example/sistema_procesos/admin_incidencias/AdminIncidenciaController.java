package com.example.sistema_procesos.admin_incidencias;

import com.example.sistema_procesos.registrar_incidencia.Incidencia;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/incidencias")
@RequiredArgsConstructor
public class AdminIncidenciaController {

    private final AdminIncidenciaService service;

    @GetMapping
    public List<Incidencia> listarTodas() {
        return service.listarTodas();
    }

    @PatchMapping("/{id}/atendida")
    public Incidencia marcarAtendida(@PathVariable Long id) {
        return service.marcarAtendida(id);
    }
}

package com.example.sistema_procesos.registrar_incidencia;

import com.example.sistema_procesos.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operador/incidencias")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService service;

    @PostMapping
    public Incidencia registrar(@AuthenticationPrincipal User user,
                                @RequestParam String descripcion) {
        return service.registrarIncidencia(user, descripcion);
    }

    @GetMapping
    public List<Incidencia> listar(@AuthenticationPrincipal User user) {
        return service.listarMisIncidencias(user);
    }
}

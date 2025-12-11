package com.example.sistema_procesos.registrar_salida;

import com.example.sistema_procesos.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operador/salida")
@RequiredArgsConstructor
public class SalidaController {

    private final SalidaService service;

    @PostMapping
    public Salida registrar(@AuthenticationPrincipal User user) {
        return service.registrarSalida(user);
    }
}


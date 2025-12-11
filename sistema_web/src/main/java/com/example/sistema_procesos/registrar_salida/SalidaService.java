package com.example.sistema_procesos.registrar_salida;

import com.example.sistema_procesos.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SalidaService {

    private final SalidaRepository repository;

    public Salida registrarSalida(User user) {

        LocalDate hoy = LocalDate.now();

        if (repository.findByUserIdAndFechaSalida(user.getId(), hoy).isPresent()) {
            throw new RuntimeException("Ya registraste tu salida hoy");
        }

        Salida salida = Salida.builder()
                .userId(user.getId())
                .nombreUsuario(user.getFirstname() + " " + user.getLastname())
                .role(user.getRole().name())
                .fechaSalida(hoy)
                .build();

        return repository.save(salida);
    }
}

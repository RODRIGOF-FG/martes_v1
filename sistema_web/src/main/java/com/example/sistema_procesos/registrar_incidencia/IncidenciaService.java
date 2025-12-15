package com.example.sistema_procesos.registrar_incidencia;

import com.example.sistema_procesos.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository repository;

    // Registrar incidencia nueva
    public Incidencia registrarIncidencia(User user, String descripcion) {
        Incidencia incidencia = Incidencia.builder()
                .userId(user.getId())
                .nombreUsuario(user.getFirstname() + " " + user.getLastname())
                .descripcion(descripcion)
                .estado("PENDIENTE")
                .fechaRegistro(LocalDateTime.now())
                .usuario(user)
                .build();

        return repository.save(incidencia);
    }

    // Ver incidencias propias del usuario
    public List<Incidencia> listarMisIncidencias(User user) {
        return repository.findByUserId(user.getId());
    }
}

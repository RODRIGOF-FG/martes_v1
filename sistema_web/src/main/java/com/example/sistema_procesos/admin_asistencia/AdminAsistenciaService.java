package com.example.sistema_procesos.admin_asistencia;

import com.example.sistema_procesos.asistencia.Asistencia;
import com.example.sistema_procesos.asistencia.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminAsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public List<AdminAsistenciaResponse> listarTodas() {

        List<Asistencia> asistencias = asistenciaRepository.findAll();

        return asistencias.stream().map(a ->
                new AdminAsistenciaResponse(
                        a.getId(),
                        a.getUser().getId(),
                        a.getNombreUsuario(),
                        a.getUser().getEmail(),
                        a.getUser().getRole().name(),
                        a.getFecha()
                )
        ).collect(Collectors.toList());
    }
}

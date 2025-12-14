package com.example.sistema_procesos.admin_incidencias;

import com.example.sistema_procesos.registrar_incidencia.Incidencia;
import com.example.sistema_procesos.registrar_incidencia.IncidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminIncidenciaService {

// admin incidencias
private final IncidenciaRepository repository;

    public List<Incidencia> listarTodas() {
        return repository.findAll();
    }
    // Marcar como atendida
    public Incidencia marcarAtendida(Long id) {
        Incidencia incidencia = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        incidencia.setEstado("ATENDIDO");
        return repository.save(incidencia);
    }
}

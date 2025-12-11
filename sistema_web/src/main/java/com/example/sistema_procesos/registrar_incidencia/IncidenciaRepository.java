package com.example.sistema_procesos.registrar_incidencia;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByUserId(Long userId);
    Optional<Incidencia> findByIdAndUserId(Long id, Long userId);
}

package com.example.sistema_procesos.Asignacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    Optional<Asignacion> findByUserId(Long userId);

}

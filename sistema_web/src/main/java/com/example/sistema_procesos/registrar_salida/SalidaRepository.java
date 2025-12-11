package com.example.sistema_procesos.registrar_salida;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface SalidaRepository extends JpaRepository<Salida, Long> {

    Optional<Salida> findByUserIdAndFechaSalida(Long userId, LocalDate fechaSalida);
}

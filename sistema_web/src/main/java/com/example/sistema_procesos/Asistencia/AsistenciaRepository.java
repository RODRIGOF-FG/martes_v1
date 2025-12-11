package com.example.sistema_procesos.asistencia;

import com.example.sistema_procesos.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    boolean existsByUserAndFecha(User user, LocalDate fecha);

    List<Asistencia> findByUser(User user);
}
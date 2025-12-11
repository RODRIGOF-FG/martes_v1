package com.example.sistema_procesos.admin_salida;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminSalidaRepository extends JpaRepository<AdminSalida, Long> {

    List<AdminSalida> findByUserId(Long userId);
}
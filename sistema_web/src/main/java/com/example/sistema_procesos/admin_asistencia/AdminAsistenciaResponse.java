package com.example.sistema_procesos.admin_asistencia;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AdminAsistenciaResponse {

    private Long idAsistencia;
    private Long idUsuario;
    private String nombreUsuario;
    private String email;
    private String rol;
    private LocalDate fecha;
}

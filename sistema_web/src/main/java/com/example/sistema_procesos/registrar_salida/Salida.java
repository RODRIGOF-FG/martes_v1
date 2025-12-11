package com.example.sistema_procesos.registrar_salida;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "salidas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "user_id")
    private Long userId;

    private String role;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;
}

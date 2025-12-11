package com.example.sistema_procesos.asistencia;

import com.example.sistema_procesos.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "asistencias",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "fecha"})
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ SOLO ESTA RELACIÓN (ELIMINAMOS userId DUPLICADO)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // ✅ Este sí puedes guardarlo como texto
    @Column(nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private LocalDate fecha;
}
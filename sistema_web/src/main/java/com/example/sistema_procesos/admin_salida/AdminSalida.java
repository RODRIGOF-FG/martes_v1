package com.example.sistema_procesos.admin_salida;

import com.example.sistema_procesos.User.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "salidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSalida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String role;

    // ✅ RELACIÓN BIEN DEFINIDA
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
package com.example.sistema_procesos.Asignacion;

import com.example.sistema_procesos.User.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "asignaciones")
@Data
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String email;
    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AsignacionArea area;

    @ElementCollection
    @CollectionTable(name = "asignacion_tareas")
    private List<String> tareasAsignadas;

    @ElementCollection
    @CollectionTable(name = "asignacion_tareas_completadas")
    private List<String> tareasCompletadas;

    private LocalDateTime fechaAsignacion;
    private LocalDateTime fechaActualizacion;
}

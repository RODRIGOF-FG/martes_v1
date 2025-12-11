package com.example.sistema_procesos.Asignacion;

import lombok.Data;
import java.util.List;

@Data
public class UpdateAsignacionRequest {

    private String area;
    private List<String> tareas;

    public AsignacionArea toAreaEnum() {
        return AsignacionArea.valueOf(area.trim().toUpperCase().replace(" ", "_"));
    }
}

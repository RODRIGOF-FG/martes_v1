package com.example.sistema_procesos.Asignacion;

import lombok.Data;
import java.util.List;

@Data
public class CompletarTareasRequest {
    private List<String> tareasCompletadas;
}

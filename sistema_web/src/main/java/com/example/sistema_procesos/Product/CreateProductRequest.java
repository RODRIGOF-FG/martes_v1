package com.example.sistema_procesos.Product;

import lombok.Data;

@Data
public class CreateProductRequest {

    private String area;
    private String name;
    private Integer quantity;
    private Double price;

    public ProductArea toAreaEnum() {
        return switch (area.trim().toUpperCase()) {
            case "ALIMENTOS_BASICOS" -> ProductArea.ALIMENTOS_BASICOS;
            case "PRODUCTOS_LIMPIEZA" -> ProductArea.PRODUCTOS_LIMPIEZA;
            case "HIGIENE_PERSONAL" -> ProductArea.HIGIENE_PERSONAL;
            default -> throw new RuntimeException("Área no válida. Solo: ALIMENTOS_BASICOS, PRODUCTOS_LIMPIEZA, HIGIENE_PERSONAL");
        };
    }
}

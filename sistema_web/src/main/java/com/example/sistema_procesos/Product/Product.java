package com.example.sistema_procesos.Product;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductArea area;

    @Column(unique = true, nullable = false)
    private String name;

    private Integer quantity;

    private Double price;
}

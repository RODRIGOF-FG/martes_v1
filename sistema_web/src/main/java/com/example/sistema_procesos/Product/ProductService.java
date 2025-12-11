package com.example.sistema_procesos.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(CreateProductRequest req) {

        if (repository.existsByName(req.getName())) {
            throw new RuntimeException("El nombre del producto ya existe");
        }

        Product p = new Product();
        p.setArea(req.toAreaEnum());
        p.setName(req.getName());
        p.setQuantity(req.getQuantity());
        p.setPrice(req.getPrice());

        return repository.save(p);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product update(Long id, UpdateProductRequest req) {

        Product p = findById(id);

        if (!p.getName().equals(req.getName()) &&
                repository.existsByName(req.getName())) {
            throw new RuntimeException("Ese nombre ya está en uso");
        }

        p.setArea(req.toAreaEnum());
        p.setName(req.getName());
        p.setQuantity(req.getQuantity());
        p.setPrice(req.getPrice());

        return repository.save(p);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no existe");
        }
        repository.deleteById(id);
    }
}

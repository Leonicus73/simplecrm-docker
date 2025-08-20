package com.example.simplecrm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.simplecrm.entity.Product;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ArrayList<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product("Laptop", "High-performance laptop", 1599.99));
        products.add(new Product("Smartphone", "Latest-gen smartphone", 899.49));
        products.add(new Product("Desk Chair", "Ergonomic mesh chair", 249.95));
    }

    // GET all
    @GetMapping
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        int index = getProductIndex(id);
        if (index == -1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products.get(index));
    }

    // POST create
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        int index = getProductIndex(id);
        if (index == -1) {
            return ResponseEntity.notFound().build();
        }
        updatedProduct.setId(id);
        products.set(index, updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        int index = getProductIndex(id);
        if (index == -1) {
            return ResponseEntity.notFound().build();
        }
        products.remove(index);
        return ResponseEntity.noContent().build();
    }

    // Helper method
    private int getProductIndex(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return products.indexOf(product);
            }
        }
        return -1;
    }
}
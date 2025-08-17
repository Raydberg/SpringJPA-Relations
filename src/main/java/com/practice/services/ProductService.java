package com.practice.services;

import com.practice.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    void delete(Long id);
    boolean existsBySku(String sku);
}

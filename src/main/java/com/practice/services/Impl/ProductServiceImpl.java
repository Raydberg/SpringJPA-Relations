package com.practice.services.Impl;

import com.practice.entities.Product;
import com.practice.repositories.ProductRepository;
import com.practice.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        return productRepository.findById(id).map(p -> {
            if (product.getName() != null && product.getName().trim().isEmpty())
                p.setName(product.getName());
            if (product.getDescription() != null && !product.getDescription().trim().isEmpty())
                p.setDescription(product.getDescription());
            if (product.getPrice() != null)
                p.setPrice(product.getPrice());
            if (product.getSku() != null && product.getSku().trim().isEmpty())
                p.setSku(product.getSku());
            return productRepository.save(p);
        });
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {
            throw new EntityNotFoundException("Producto con id " + id + "no encontrado");
        });
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }
}

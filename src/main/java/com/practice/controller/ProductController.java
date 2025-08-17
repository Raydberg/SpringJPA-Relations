package com.practice.controller;

import com.practice.entities.Product;
import com.practice.services.ProductService;
import com.practice.validations.ProductValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    private final ProductValidation validation;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> view(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {
        log.info(result.toString());
        validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(productService.save(product));
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
        validation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(productService.update(id, product));
    }

    private ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

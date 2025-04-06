package com.practice.controller;

import com.practice.entities.Invoce;
import com.practice.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("")
    public ResponseEntity<List<Invoce>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices().orElseThrow());
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoce> getById(@PathVariable Long id) {
        return ResponseEntity.ok(invoiceService.getById(id).orElseThrow());
    }

    /**
     * {
     * "description": "Descripción de la factura",
     * "total": 2500,
     * "client": {
     * "id": 1
     * }
     * }
     */
    @PostMapping("")
    public ResponseEntity<Invoce> create(@RequestBody Invoce invoce) {
        return ResponseEntity.ok(invoiceService.createBy(invoce));
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoce> update(@PathVariable Long id, @RequestBody Invoce invoce) {
        return ResponseEntity.ok(invoiceService.updateById(id, invoce).orElseThrow());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

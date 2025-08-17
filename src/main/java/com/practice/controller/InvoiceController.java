package com.practice.controller;

import com.practice.entities.Invoice;
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
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices().orElseThrow());
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id) {
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
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.createBy(invoice));
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.updateById(id, invoice).orElseThrow());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.practice.services;

import com.practice.entities.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    Optional<List<Invoice>> getAllInvoices();

    Optional<Invoice> getById(Long id);

    Invoice createBy(Invoice invoice);

    Optional<Invoice> updateById(Long id, Invoice invoice);

    void deleteById(Long id);
}

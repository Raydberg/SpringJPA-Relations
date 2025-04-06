package com.practice.services;

import com.practice.entities.Invoce;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    Optional<List<Invoce>> getAllInvoices();

    Optional<Invoce> getById(Long id);

    Invoce createBy(Invoce invoce);

    Optional<Invoce> updateById(Long id, Invoce invoce);

    void deleteById(Long id);
}

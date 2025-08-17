package com.practice.services.Impl;

import com.practice.entities.Client;
import com.practice.entities.Invoice;
import com.practice.repositories.ClientRepository;
import com.practice.repositories.InvoiceRepository;
import com.practice.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Invoice>> getAllInvoices() {
        return Optional.of(invoiceRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Invoice> getById(Long id) {
        if (invoiceRepository.existsById(id)) {
            return Optional.of(invoiceRepository.findById(id)).orElseThrow();
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Invoice createBy(Invoice invoice) {
        if (invoice.getClient() == null || invoice.getClient().getId() == null) {
            throw new IllegalArgumentException("Se requiere el ID del cliente");
        }

        Long clientId = invoice.getClient().getId();

        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("No existe el cliente con ID " + clientId);
        }

        Client clientFromDB = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el cliente con ID " + clientId));

        invoice.setClient(clientFromDB);

        return invoiceRepository.saveAndFlush(invoice);
    }

    @Transactional
    @Override
    public Optional<Invoice> updateById(Long id, Invoice invoice) {
        return invoiceRepository.findById(id).map(invoiceExist -> {
            if (invoice.getDescription() != null) {
                invoiceExist.setDescription(invoice.getDescription());
            }
            if (invoice.getTotal() != null) {
                invoiceExist.setTotal(invoice.getTotal());
            }
            if (invoice.getClient() != null && invoice.getClient().getId() != null) {
                Long clientId = invoice.getClient().getId();
                Client clientFromDb = clientRepository.findById(clientId).orElseThrow();
                invoiceExist.setClient(clientFromDb);
            }

            return Optional.of(invoiceRepository.saveAndFlush(invoiceExist));
        }).orElseThrow();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}

package com.practice.services.Impl;

import com.practice.entities.Client;
import com.practice.entities.Invoce;
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
    public Optional<List<Invoce>> getAllInvoices() {
        return Optional.of(invoiceRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Invoce> getById(Long id) {
        if (invoiceRepository.existsById(id)) {
            return Optional.of(invoiceRepository.findById(id)).orElseThrow();
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Invoce createBy(Invoce invoce) {
        if (invoce.getClient() == null || invoce.getClient().getId() == null) {
            throw new IllegalArgumentException("Se requiere el ID del cliente");
        }

        Long clientId = invoce.getClient().getId();

        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("No existe el cliente con ID " + clientId);
        }

        Client clientFromDB = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el cliente con ID " + clientId));

        invoce.setClient(clientFromDB);

        return invoiceRepository.saveAndFlush(invoce);
    }

    @Transactional
    @Override
    public Optional<Invoce> updateById(Long id, Invoce invoce) {
        return invoiceRepository.findById(id).map(invoiceExist -> {
            if (invoce.getDescription() != null) {
                invoiceExist.setDescription(invoce.getDescription());
            }
            if (invoce.getTotal() != null) {
                invoiceExist.setTotal(invoce.getTotal());
            }
            if (invoce.getClient() != null && invoce.getClient().getId() != null) {
                Long clientId = invoce.getClient().getId();
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

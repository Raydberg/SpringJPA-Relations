package com.practice.services.Impl;

import com.practice.entities.Address;
import com.practice.entities.Client;
import com.practice.repositories.AddressRepository;
import com.practice.repositories.ClientRepository;
import com.practice.repositories.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaService {
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final InvoiceRepository invoiceRepository;

    public Client createOneToMany(String name, String lastName, Set<Address> direcciones) {
        var client = Client
                .builder()
                .name(name)
                .lastName(lastName)
                .addresses(direcciones)
                .build();
        return clientRepository.save(client);
    }

}

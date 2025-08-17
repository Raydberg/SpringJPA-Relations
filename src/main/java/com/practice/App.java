package com.practice;

import com.practice.entities.Address;
import com.practice.entities.Client;
import com.practice.repositories.AddressRepository;
import com.practice.repositories.ClientRepository;
import com.practice.repositories.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        oneToManyDelete();
    }

    @Transactional
    public void oneToManyDelete() {
        Set<Address> addresses = Stream.of(
                Address.builder()
                        .street("Calle Principal")
                        .number(123)
                        .build(),
                Address.builder()
                        .street("Avenida Secundaria")
                        .number(456)
                        .build()
        ).collect(Collectors.toSet());

        Client client = Client
                .builder()
                .name("Test client")
                .lastName("Test lastname")
                .addresses(addresses)
                .build();

        clientRepository.save(client);

        clientRepository.findById(7L).ifPresent(c -> {
            log.info("Direcciones {}", c.getAddresses().toString());
            clientRepository.save(c);
            System.out.println(c);
        });
    }

    @Transactional
    public void createOneToMany() {
        Set<Address> addresses = Stream.of(
                Address.builder()
                        .street("Calle Principal")
                        .number(123)
                        .build(),
                Address.builder()
                        .street("Avenida Secundaria")
                        .number(456)
                        .build()
        ).collect(Collectors.toSet());

        Client client = Client
                .builder()
                .name("Test client")
                .lastName("Test lastname")
                .addresses(addresses)
                .build();

        clientRepository.save(client);
    }

}

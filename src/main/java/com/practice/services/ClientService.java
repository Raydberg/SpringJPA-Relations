package com.practice.services;

import com.practice.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<List<Client>> findAllClients();

    Optional<Client> findById(Long id);

    Client createByClient(Client client);

    Optional<Client> updateById(Long id, Client client);

    void deleteByClient(Long id);
}

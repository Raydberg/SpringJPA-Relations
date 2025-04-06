package com.practice.services.Impl;

import com.practice.entities.Client;
import com.practice.repositories.ClientRepository;
import com.practice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Client>> findAllClients() {
        return Optional.of(clientRepository.findAll());
    }

    @Override
    public Optional<Client> findById(Long id) {
        if (clientRepository.existsById(id)) {
            return clientRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Client createByClient(Client client) {
        if (!clientRepository.existsClientByName(client.getName())) {
            return clientRepository.saveAndFlush(client);
        }
        throw new IllegalArgumentException("Client with ID " + client.getId() + " already exists");
    }

    @Transactional
    @Override
    public Optional<Client> updateById(Long id, Client client) {
        return clientRepository.findById(id).map(existClient -> {
            if (client.getName() != null) {
                existClient.setName(client.getName());
            }
            if (client.getLastName() != null) {
                existClient.setLastName(client.getLastName());
            }
//            if (existClient.get)
            return Optional.of(clientRepository.saveAndFlush(existClient));
        }).orElseThrow();
    }

    @Transactional
    @Override
    public void deleteByClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        }
    }
}

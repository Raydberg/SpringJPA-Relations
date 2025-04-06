package com.practice.controller;


import com.practice.entities.Client;
import com.practice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("")
    ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.findAllClients().orElseThrow());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> findByClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id).orElseThrow());
    }

    @PostMapping("")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createByClient(client));
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        return ResponseEntity.ok(clientService.updateById(id, client).orElseThrow());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        clientService.deleteByClient(id);
        return ResponseEntity.noContent().build();
    }

}

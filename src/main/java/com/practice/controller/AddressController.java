package com.practice.controller;

import com.practice.entities.Address;
import com.practice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("")
    public ResponseEntity<List<Address>> findAllAddress() {
        return ResponseEntity.ok(addressService.findAll().orElseThrow());
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> findByAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.findById(id).orElseThrow());
    }

    @PostMapping("")
    public ResponseEntity<Address> create(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.createAddress(address));
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
        return ResponseEntity.ok(addressService.updateById(id, address).orElseThrow());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

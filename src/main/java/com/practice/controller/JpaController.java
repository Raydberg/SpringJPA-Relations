package com.practice.controller;

import com.practice.entities.Address;
import com.practice.services.AddressService;
import com.practice.services.Impl.JpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("relaciones-jpa")
@RequiredArgsConstructor
public class JpaController {
    private final JpaService jpaService;


    @PostMapping("createOneToMany")
    public ResponseEntity<?> createOneToMany(@RequestBody String name, @RequestBody String lastName, @RequestBody Set<Address> direcciones) {
        return ResponseEntity.ok(jpaService.createOneToMany(name, lastName, direcciones));
    }
}

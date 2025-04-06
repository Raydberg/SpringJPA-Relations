package com.practice.controller;

import com.practice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("address")
public class AddressController {
    private final AddressService addressService;
}

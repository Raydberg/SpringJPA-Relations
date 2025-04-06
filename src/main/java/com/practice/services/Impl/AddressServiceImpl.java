package com.practice.services.Impl;

import com.practice.repositories.AddressRepository;
import com.practice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
     private final AddressRepository addressRepository;
}

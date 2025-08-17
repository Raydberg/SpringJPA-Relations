package com.practice.services.Impl;

import com.practice.entities.Address;
import com.practice.repositories.AddressRepository;
import com.practice.services.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<List<Address>> findAll() {
        return Optional.of(addressRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Address> findById(Long id) {
        if (addressRepository.existsById(id)) {
            return Optional.of(addressRepository.findById(id).orElseThrow());
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Address createAddress(Address address) {
        if (!addressRepository.existsAddressByStreet(address.getStreet())) {
            return addressRepository.saveAndFlush(address);
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<Address> updateById(Long id, Address address) {
        return addressRepository.findById(id).map(addressExist -> {
            if (address.getStreet() != null) {
                addressExist.setStreet(address.getStreet());
            }
            if (address.getNumber() != null) {
                addressExist.setNumber(address.getNumber());
            }
            return Optional.of(addressRepository.saveAndFlush(addressExist));
        }).orElseThrow();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
    }
}

package com.practice.services;

import com.practice.entities.Address;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<List<Address>> findAll();

    Optional<Address> findById(Long id);

    Address createAddress(Address address);

    Optional<Address> updateById(Long id, Address address);

    void deleteById(Long id);
}

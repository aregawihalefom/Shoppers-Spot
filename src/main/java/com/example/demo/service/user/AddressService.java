package com.example.demo.service.user;

import com.example.demo.domain.Address;

import java.util.List;

public interface AddressService {

    public List<Address> getAll();

    public Address getById(long id);

    public void addAddress(Address address);

    public void deleteById(long id);
}

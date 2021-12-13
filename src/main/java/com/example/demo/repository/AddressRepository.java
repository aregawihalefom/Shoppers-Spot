package com.example.demo.repository;

import com.example.demo.domain.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {
    public List<Address> findAll();
    Address getById(long id);
}

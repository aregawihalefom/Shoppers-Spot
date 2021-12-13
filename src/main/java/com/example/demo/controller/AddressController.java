package com.example.demo.controller;

import com.example.demo.domain.Address;
import com.example.demo.service.user.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAll(){
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable long id){
        return addressService.getById(id);
    }

    @PostMapping()
    public void addAddress(@RequestBody Address address){
        addressService.addAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id){
        addressService.deleteById(id);
    }


}

package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findAll();
    public List<Product> getProductByName(String name);
    public List<Product> findProductByUserUsername(String username);

}

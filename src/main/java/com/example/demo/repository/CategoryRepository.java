package com.example.demo.repository;

import com.example.demo.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    public List<Category> findAll();
    public Optional<Category> findByName(String name);
    Boolean existsByName(String name);
}

package com.example.demo.service;

import com.example.demo.domain.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAll();

    public <Optional>Category getById(long id);

    public void addCategory(Category category);

    public void deleteById(long id);
}

package com.example.demo.service;


import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAll();

    public List<User> findAll();

    public User findById(long id);

    public User save(User user);

    public void deleteById(long id);
}

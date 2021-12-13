package com.example.demo.service;


import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAll();

    public UserDto getById(long id);

    public void addUser(UserDto user);

    public void deleteById(long id);


}

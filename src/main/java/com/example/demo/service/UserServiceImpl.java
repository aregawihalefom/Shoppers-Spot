package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.helper.ListMapper;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired

    ListMapper<User,UserDto> listMapperUserToDto;

    @Override
    public List<UserDto> getAll() {
        return (List<UserDto>) listMapperUserToDto.mapList(userRepository.findAll(),new UserDto());
    }

    @Override
    public UserDto getById(long id) {
        return modelMapper.map(userRepository.findById(id).orElse(null),UserDto.class);
    }

    @Override
    public void addUser(UserDto user) {
        userRepository.save(modelMapper.map(user,User.class));
    }


    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


}


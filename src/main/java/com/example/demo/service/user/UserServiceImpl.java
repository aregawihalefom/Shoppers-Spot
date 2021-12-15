package com.example.demo.service.user;

import com.example.demo.domain.ProductReview;
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

    ListMapper<User, UserDto> listMapperUserToDto;

    @Override
    public List<UserDto> getAll() {
        return (List<UserDto>) listMapperUserToDto.mapList(userRepository.findAll(), new UserDto());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User approveSeller(Long id, boolean approve) {
        User user = userRepository.findById(id).get();
        user.setEnabled(approve);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<ProductReview> findByUserId(Long id) {
        return null;
    }

}


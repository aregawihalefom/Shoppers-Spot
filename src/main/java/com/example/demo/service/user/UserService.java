package com.example.demo.service.user;


import com.example.demo.domain.Product;
import com.example.demo.domain.ProductReview;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {

    public List<UserDto> getAll();

    public List<User> findAll();

    public User findById(long id);

    public User save(User user);

    public void deleteById(long id);

    public User approveSeller(Long id, boolean b);

    List<ProductReview> findByUserId(Long id);

}

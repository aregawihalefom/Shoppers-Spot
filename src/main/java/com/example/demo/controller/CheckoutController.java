package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.service.order.OrderService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public class CheckoutController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/users/{id}")
    public List<Order> findByUser(@PathVariable("id") Long userId){
        return orderService.findByUserId(userId);
    }

}

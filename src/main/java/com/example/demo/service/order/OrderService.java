package com.example.demo.service.order;

import com.example.demo.domain.Order;
import com.example.demo.domain.ProductReview;

import java.util.List;

public interface OrderService {


    public List<Order> findAll();

    public Order findById(long id);

    public Order save(Order order);

    public void deleteById(long id);

    public Order updateOrderStatus(Long id, String status);

    public List<Order> findByUserId(Long id);

    public List<Order> findOrderByOrderStatusName(String Status);
}

package com.example.demo.service.order;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    public List<OrderStatus> findAll();
    public OrderStatus save(OrderStatus orderStatus);
    public OrderStatus findById(Long id);
    public void deleteById(Long id);
    public OrderStatus findOrderStatusByName(String name);
}

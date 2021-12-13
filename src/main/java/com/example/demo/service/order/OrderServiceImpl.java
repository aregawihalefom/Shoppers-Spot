package com.example.demo.service.order;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(long id) {
  orderRepository.deleteById(id);

    }

    @Override
    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow();
        OrderStatus status1 = new OrderStatus(status);
        order.setOrderStatus(status1);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepository.findOrderByUserId(id) ;
    }

    @Override
    public List<Order> findOrderByOrderStatusName(String status) {
        return orderRepository.findOrderByOrderStatusName(status);
    }
}

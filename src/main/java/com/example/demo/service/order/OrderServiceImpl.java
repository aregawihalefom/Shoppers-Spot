package com.example.demo.service.order;

import com.example.demo.config.Constants;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

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
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setOrderStatus(status);
        order.setStatusUpdateAt(Constants.CURRENT_TIME);
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findByUserId(Long id) {
        User user = userService.findById(id);
        return user.getOrders() ;
    }

    @Override
    public List<Order> findOrderByOrderStatusName(String status) {
        return orderRepository.findOrderByOrderStatusName(status);
    }
}

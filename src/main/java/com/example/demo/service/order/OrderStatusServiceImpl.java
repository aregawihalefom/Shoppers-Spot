package com.example.demo.service.order;

import com.example.demo.domain.OrderStatus;
import com.example.demo.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderStatus> findAll() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        orderStatusRepository.findAll().forEach(orderStatuses::add);
        return orderStatuses;
    }

    @Override
    public OrderStatus save(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus findById(Long id) {
        return orderStatusRepository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        orderStatusRepository.deleteById(id);
    }

    @Override
    public OrderStatus findOrderStatusByName(String name) {
        return orderStatusRepository.findOrderStatusByName(name);
    }
}

package com.example.demo.repository;

import com.example.demo.domain.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    public OrderStatus findOrderStatusByName(String name);
}

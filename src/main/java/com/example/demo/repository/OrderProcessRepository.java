package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderProcessRepository extends CrudRepository<Order, Long> {
}

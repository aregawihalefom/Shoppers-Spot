package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Long> {

    public List<Order> findOrderByOrderStatusName(String name);
}

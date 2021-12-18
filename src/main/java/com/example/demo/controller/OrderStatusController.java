package com.example.demo.controller;

import com.example.demo.domain.OrderStatus;
import com.example.demo.service.order.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/order-status")
public class OrderStatusController {

    @Autowired
   private OrderStatusService orderStatusService;

    @GetMapping
    public List<OrderStatus> getAll() {
        return orderStatusService.findAll();
    }

    @GetMapping("/{id}")
    public OrderStatus getById(@PathVariable long id) {
        return orderStatusService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody OrderStatus orderStatus) {
        orderStatusService.save(orderStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id) {
        orderStatusService.deleteById(id);
    }

    @PutMapping("/{id}")
    public OrderStatus update(@PathVariable("id")Long id, @RequestBody OrderStatus orderStatus){
        return orderStatusService.save(orderStatus);
    }

    @GetMapping("/{name}")
    public OrderStatus findOrderStatusByName(@PathVariable("name") String name){
        return orderStatusService.findOrderStatusByName(name);
    }

}

package com.example.demo.controller;

import com.example.demo.config.Constants;
import com.example.demo.config.Utilities;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.User;
import com.example.demo.domain.enums.OrderStatusEnums;
import com.example.demo.dto.CheckoutRecordDto;
import com.example.demo.service.order.OrderService;
import com.example.demo.service.order.OrderStatusService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orders")
public class OrderController {


    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id")Long id){
        return orderService.findById(id);
    }

    // active
    @GetMapping("/users/{id}")
    public List<Order> findByUser(@PathVariable("id") Long userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping("/users/{id}/cancelled")
    public List<Order> findByUserCancelled(@PathVariable("id") Long userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping("/seller/{id}/")
    public List<Order> fidBySeller(@PathVariable("id") Long userId) {
        return orderService.findByUserId(userId);
    }

    @GetMapping("/filter")
    public List<Order> getById(@RequestParam("status") String status) {
        return orderService.findOrderByOrderStatusName(status.toUpperCase().trim());
    }

    /***
     *
     * Saving new orders
     *
     */

    @PostMapping
    public Order save(@RequestBody CheckoutRecordDto checkoutRecordDto) {

        // Get user
        User user = userService.findByUsername(checkoutRecordDto.getUsername());

        // Create an order instance and add it
        Order newOrder = new Order();
        newOrder.setTaxes(Constants.TAX_RATE);
        newOrder.setTotalPrice(Utilities.calculateTotalPrice(checkoutRecordDto.getProducts()));
        newOrder.setSubTotal(Utilities.calculateSubTotal(newOrder.getTotalPrice()));
        newOrder.setOrderDate(Constants.CURRENT_TIME);
        newOrder.setBillingAddress(checkoutRecordDto.getBillingAddress());
        newOrder.setShippingAddress(checkoutRecordDto.getShippingAddress());
        newOrder.setStatusUpdateAt(Constants.CURRENT_TIME);
        newOrder.setProducts(checkoutRecordDto.getProducts());
        newOrder.setCardPayment(checkoutRecordDto.getCardPayment());
        OrderStatus orderStatus = orderStatusService.findOrderStatusByName(OrderStatusEnums.ORDER_PLACED.name());
        newOrder.setOrderStatus(orderStatus);
        user.addOrder(newOrder);
        userService.save(user);

        return newOrder;
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@RequestParam("status") String status, @PathVariable Long id) {
        OrderStatus orderStatus = orderStatusService.findOrderStatusByName(status.toLowerCase().trim());
        if (orderStatus != null)
            return orderService.updateOrderStatus(id, orderStatus);
        return null;

    }

    @DeleteMapping("/{id}")
    public Order cancelOrder(@PathVariable("id")Long id){
        OrderStatus orderStatus = orderStatusService.findOrderStatusByName(OrderStatusEnums.ORDER_CANCELLED.name());
        Order order = orderService.findById(id);
        order.setOrderStatus(orderStatus);
        return orderService.save(order);
    }

}

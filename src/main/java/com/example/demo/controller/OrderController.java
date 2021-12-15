package com.example.demo.controller;

import com.example.demo.config.Constants;
import com.example.demo.config.Utilities;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderStatus;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.enums.OrderStatusEnums;
import com.example.demo.dto.CheckoutRecordDto;
import com.example.demo.service.order.OrderService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }


    @GetMapping("/users/{id}")
    public List<Order> findByUser(@PathVariable("id") Long userId){
        return orderService.findByUserId(userId);
    }

    @GetMapping("/filter")
    public List<Order> getById(@RequestParam("status") String status) {
        return orderService.findOrderByOrderStatusName(status);
    }

    /***
     *
     * Saving new orders
     *
     */

    @PostMapping
    public User save(@RequestBody CheckoutRecordDto checkoutRecordDto){

        // Get user
        User user = userService.findByUsername(checkoutRecordDto.getUsername());

        // Create an order instance and add it
        Order newOrder = new Order();
        newOrder.setTaxes(Constants.TAX_RATE);
        newOrder.setTotalPrice(Utilities.calculateTotalPrice(checkoutRecordDto.getProducts()));
        newOrder.setSubTotal(Utilities.calculateSubTotal(newOrder.getTotalPrice()));
        newOrder.setOrderStatus(new OrderStatus(OrderStatusEnums.ORDER_PLACED.name()));
        newOrder.setOrderDate(Constants.CURRENT_TIME);
        newOrder.setBillingAddress(checkoutRecordDto.getBillingAddress());
        newOrder.setShippingAddress(checkoutRecordDto.getShippingAddress());
        newOrder.setStatusUpdateAt(Constants.CURRENT_TIME);
        newOrder.setProducts(checkoutRecordDto.getProducts());
        newOrder.setCardPayment(checkoutRecordDto.getCardPayment());
        user.addOrder(newOrder);


        for(Order order : user.getOrders()){
            order.setUser(user);
        }
        userService.save(user);

        return user;
    }


}

package com.example.demo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private Double taxes;
    private Double subTotal;
    private LocalDate orderDate;
    private LocalDate statusUpdateAt; // could be any update . cancel , confirmation

    @ManyToMany()
    @JoinTable()
    private Set<Product> products = new HashSet<>();

    @OneToOne()
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id")
    public Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id")
    public Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_method_id")
    public CardPayment cardPayment;


}

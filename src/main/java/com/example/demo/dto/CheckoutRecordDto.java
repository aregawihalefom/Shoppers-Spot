package com.example.demo.dto;

import com.example.demo.domain.Address;
import com.example.demo.domain.CardPayment;
import com.example.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRecordDto {
    private Address billingAddress;
    private Address shippingAddress;
    private Set<Product> products;
    private String username;
    private CardPayment cardPayment;
}

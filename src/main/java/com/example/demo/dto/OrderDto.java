package com.example.demo.dto;

import com.example.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Double totalPrice;
    private Double taxes;
    private Double subTotal;
    private LocalDate orderDate;
    private Set<Product> products;
}

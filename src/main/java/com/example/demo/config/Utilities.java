package com.example.demo.config;

import com.example.demo.domain.Product;

import java.util.List;
import java.util.Set;

public class Utilities {

    public static double calculateTotalPrice(Set<Product> products){
        return products.stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0.0, Double::sum);
    }

    public static double calculateSubTotal(Double total){
        return total * Constants.TAX_RATE;
    }
}

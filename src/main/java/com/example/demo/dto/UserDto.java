package com.example.demo.dto;

import com.example.demo.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    long id;
    String fistName;
    String middleName;
    String lastName;
    String username;
    String email;
    String points;
    Set<Product> products;
}

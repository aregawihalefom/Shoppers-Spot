package com.example.demo.domain;

import com.example.demo.domain.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String sku;
    private Double price;
    private Integer quantity;
    private String banner;
    private LocalDate createdAt;
    private String productStatus = ProductStatusEnum.NEW.name();

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductReview> productReview = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public void addReview(ProductReview review){
        productReview.add(review);
    }


}

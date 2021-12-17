package com.example.demo.domain;

import com.example.demo.domain.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String sku;
    private Double price;
    private Integer quantity;

    public List<ProductReview> getProductReview() {
        return productReview;
    }

    public void setProductReview(List<ProductReview> productReview) {
        this.productReview = productReview;
    }

    private String banner;
    private LocalDate createdAt;
    private String productStatus = ProductStatusEnum.NEW.name();

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductReview> productReview = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addReview(ProductReview review){
        productReview.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(sku, product.sku) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(banner, product.banner) && Objects.equals(createdAt, product.createdAt) && Objects.equals(productStatus, product.productStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, sku, price, quantity, banner, createdAt, productStatus);
    }
}

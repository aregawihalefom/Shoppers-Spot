package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String firstName;
    String middleName;
    String lastName;

    String username;
    String password;
    boolean isEnabled;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>();
    private Integer points;

    @OneToMany(cascade = {CascadeType.ALL} , mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    @JsonIgnore
    private List<ProductReview> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Address> addresses= new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<CardPayment> cardPayments;

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void addReview(ProductReview review){
        reviews.add(review);
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}

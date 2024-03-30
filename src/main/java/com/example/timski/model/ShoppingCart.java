package com.example.timski.model;

import com.example.timski.model.enums.ShoppingCartEnumerations;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> productList;
    @Enumerated(EnumType.STRING)
    private ShoppingCartEnumerations status;

    public ShoppingCart(User user) {
        this.localDateTime = LocalDateTime.now();
        this.user = user;
        this.productList = new ArrayList<>();
        this.status = ShoppingCartEnumerations.CREATED;
    }

    public ShoppingCart() {

    }

    public List<Product> getProductList() {
        return productList;
    }

    public Long getId() {
        return id;
    }

}


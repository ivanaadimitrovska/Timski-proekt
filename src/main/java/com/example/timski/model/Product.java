package com.example.timski.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer inStock;

    private String ingredients;

    private byte[] picture;

    @ManyToOne
    private Category category;


    public Product() {
    }

    public Product(String name, Double price, Integer inStock, Category category) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }
}


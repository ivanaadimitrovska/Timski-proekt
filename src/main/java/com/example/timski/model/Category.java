package com.example.timski.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000)
    private String description;

    @Lob
    private byte[] picture;

    private String pictureUrl; // Store image URL as a string

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name, String description, String pictureUrl) {
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

    public Category() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public byte[] getPicture() { return picture; }
}

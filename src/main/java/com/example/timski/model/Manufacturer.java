package com.example.timski.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Data
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manufacturer_address")
    private String address;

    private String name;

    public Manufacturer(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Manufacturer() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

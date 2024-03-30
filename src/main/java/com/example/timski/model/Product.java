package com.example.timski.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer inStock;

    private String ingredients;


    private byte[] image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Manufacturer manufacturer;

    public Product() {
        // Default constructor
    }

    public Product(String name, Double price, Integer inStock, Category category, Manufacturer manufacturer, byte[] image, String ingredients) {
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.category = category;
        this.manufacturer = manufacturer;
        this.image = image;
        this.ingredients = ingredients;
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

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setManufacturer(Manufacturer manufacturer) {this.manufacturer = manufacturer;}

    public Long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public byte[] getImage() { return image; }

    public Integer getInStock() {
        return inStock;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String generateBase64Image() {
        return Base64.encodeBase64String(this.image);
    }
}


package com.example.timski.service;

import com.example.timski.model.Category;
import com.example.timski.model.Product;
import com.example.timski.model.errors.CategoryNotFound;
import com.example.timski.model.errors.ProductNotFound;
import com.example.timski.repository.CategoryRepository;
import com.example.timski.repository.ProductRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    //private final ManufacturerRepository memoryManufacturerRepository;



    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Optional<Product> findById(Long Id) {
        return productRepository.findById(Id);
    }


    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }



    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
//        Manufacturer manufacturer = this.memoryManufacturerRepository.findById(manufacturerId)
//                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, category)));
    }




    public Optional<Product> edit(Long id, String name, Double price, Integer inStock, Long categoryId, Long manufacturerId, byte[] image) {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        product.setName(name);
        product.setPrice(price);
        product.setInStock(inStock);
        product.setPicture(image);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        product.setCategory(category);

//        Manufacturer manufacturer = this.memoryManufacturerRepository.findById(manufacturerId)
//                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));
//        product.setManufacturer(manufacturer);

        this.productRepository.delete(product);
        //this.memoryProductRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, inStock, category)));
    }



    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}

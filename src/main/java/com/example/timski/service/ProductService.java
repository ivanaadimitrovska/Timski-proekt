package com.example.timski.service;

import com.example.timski.model.Category;
import com.example.timski.model.Manufacturer;
import com.example.timski.model.Product;
import com.example.timski.model.errors.CategoryNotFound;
import com.example.timski.model.errors.ManufacturerNotFound;
import com.example.timski.model.errors.ProductNotFound;
import com.example.timski.repository.CategoryRepository;
import com.example.timski.repository.ManufacturerRepository;
import com.example.timski.repository.ProductRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;



    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
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

    public List<Product> findByCategoryId(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        return productRepository.findByCategory(category);
    }

    public Optional<Product> save(String name, Double price, Integer inStock, Long categoryId, Long manufacturerId, byte[] image, String ingredients) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, inStock, category, manufacturer, image, ingredients)));
    }
//
//    public Optional<Product> edit(Long id, String name, Double price, Integer inStock, Long categoryId, Long manufacturerId, byte[] image) {
//
//        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));
//
//        product.setName(name);
//        product.setPrice(price);
//        product.setInStock(inStock);
//        product.setImage(image);
//
//        Category category = this.categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new CategoryNotFound(categoryId));
//        product.setCategory(category);
//
//        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
//                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));
//        product.setManufacturer(manufacturer);
//
//        this.productRepository.delete(product);
//        //this.memoryProductRepository.deleteByName(name);
//        return Optional.of(this.productRepository.save(new Product(name, price, inStock, category, manufacturer, image)));
//    }

    public Optional<Product> edit(Long id, String name, Double price, Integer inStock, Long categoryId, Long manufacturerId, byte[] image, String ingredients) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));

        product.setName(name);
        product.setPrice(price);
        product.setInStock(inStock);
        product.setImage(image);
        product.setIngredients(ingredients);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFound(manufacturerId));
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));
    }

    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}

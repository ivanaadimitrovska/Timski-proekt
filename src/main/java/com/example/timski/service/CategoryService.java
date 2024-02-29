package com.example.timski.service;


import com.example.timski.model.Category;
import com.example.timski.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category create(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c=new Category(name, description);
        categoryRepository.save(c);
        return c;
    }


    public Category update(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c=new Category(name, description);
        categoryRepository.save(c);
        return c;
    }


    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteAllByName(name);
    }


    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }


    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}

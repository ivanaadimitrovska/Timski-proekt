package com.example.timski.controller;

import com.example.timski.model.Category;
import com.example.timski.model.Product;
import com.example.timski.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = {"/categories"})
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategory(@RequestParam(required = false) String error, Model model){
//        if(error!=null && !error.isEmpty()){
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
        List<Category> categories=categoryService.listCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }
}

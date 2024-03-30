package com.example.timski.controller;


import com.example.timski.model.Category;
import com.example.timski.model.Manufacturer;
import com.example.timski.model.Product;
import com.example.timski.model.errors.ProductNotFound;
import com.example.timski.service.CategoryService;
import com.example.timski.service.ManufacturerService;
import com.example.timski.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductController(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/{categoryId}")
    public String getProductsByCategory(@PathVariable Long categoryId, Model model) {
        List<Product> lista = productService.findByCategoryId(categoryId);
        model.addAttribute("listaProdukti", lista);
        model.addAttribute("bodyContent", "product");
        return "product";
    }
    @GetMapping
    public String getProduct(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> lista=productService.findAll();
        model.addAttribute("listaProdukti", lista);
        model.addAttribute("bodyContent", "product");
        return "product";
    }


    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("/add-form")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProduct(Model model){
        List<Category> categories=this.categoryService.listCategories();
        List<Manufacturer> manufacturerList=this.manufacturerService.manufacturerList();
        model.addAttribute("kategorii", categories);
        model.addAttribute("proizvoditeli", manufacturerList);
        model.addAttribute("bodyContent", "add-product");
        return "add-product";
    }



    @GetMapping("/{id}/edit-form")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFound(id));
        List<Category> categories = categoryService.listCategories();
        List<Manufacturer> manufacturerList = manufacturerService.manufacturerList();
        model.addAttribute("kategorii", categories);
        model.addAttribute("proizvoditeli", manufacturerList);
        model.addAttribute("produkt", product);
        model.addAttribute("bodyContent", "edit-product"); // Use "edit-product" as the body content for the edit form
        return "add-product"; // Return the edit-product Thymeleaf template
    }
    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(name="id", required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer inStock,
            @RequestParam Long category,
            @RequestParam Long manufacturer,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam String ingredients) {
        byte[] imageBytes;
        try {
            imageBytes = imageFile.getBytes();
        } catch (IOException e) {
            // Handle exception
            return "redirect:/product?error=ImageUploadFailed";
        }
        if (id != null) {
            this.productService.edit(id, name, price, inStock, category, manufacturer, imageBytes, ingredients);
        } else {
            this.productService.save(name, price, inStock, category, manufacturer, imageBytes, ingredients);
        }
        return "redirect:/product";
    }


}

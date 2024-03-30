package com.example.timski.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("bodyContent", "home");
        return "home";
    }

    @GetMapping("/access_denied")
    public String access_denied(Model model){
        model.addAttribute("bodyContent", "access_denied");
        return "home";
    }

    @PostMapping
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model){
        String product=request.getParameter("product");
        request.getSession().setAttribute("product", product);
        return "redirect:/product";
    }
}

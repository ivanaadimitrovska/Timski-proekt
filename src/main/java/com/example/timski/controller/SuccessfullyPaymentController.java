package com.example.timski.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/successfully-payment")
public class SuccessfullyPaymentController {

    @GetMapping
    public String getPaymentForm(@RequestParam(required = false) String error, Model model){
        return "successfully";
    }
}

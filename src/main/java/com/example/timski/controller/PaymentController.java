package com.example.timski.controller;


import com.example.timski.service.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;


@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String getPaymentForm(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "payment";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam String cardholderName,
            @RequestParam String billingAddress,
            @RequestParam String cardNumber,
            @RequestParam String expiryDate,
            @RequestParam Integer cvv,
            @RequestParam Integer paymentAmount) {

        this.paymentService.save(cardholderName, billingAddress, cardNumber, expiryDate, cvv, paymentAmount);
        return "redirect:/successfully-payment";
    }


}

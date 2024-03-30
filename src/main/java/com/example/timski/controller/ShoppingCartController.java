package com.example.timski.controller;

import com.example.timski.model.ShoppingCart;
import com.example.timski.model.User;
import com.example.timski.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/shop-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String viewShoppingCarts(@RequestParam(required = false) String error, HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String sessionId = null;
        Object details = auth.getDetails();
        WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
        sessionId = webDetails.getSessionId();
        if (sessionId != null) {
            User user = (User) request.getSession().getAttribute("user");
            ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCart(user.getUsername());
            model.addAttribute("user", user.getUsername());
            model.addAttribute("produkti", shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
            model.addAttribute("bodyContent", "shop-cart");
            return "shop-cart";
        } else {
            // User is not authenticated, redirect to login page
            return "redirect:/login";
        }

//        if(error!=null && !error.isEmpty()){
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//        String username=request.getRemoteUser();
//        ShoppingCart shoppingCart=serviceImplementation.getActiveShoppingCart(username);
//        model.addAttribute("user", username);
//        model.addAttribute("produkti", serviceImplementation.listAllProductsInShoppingCart(shoppingCart.getId()));
//        model.addAttribute("bodyContent", "shopping-cart");
//        return "master-template";
//    }


    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request,
                                           Authentication authentication){
        try{
            User user = (User) request.getSession().getAttribute("user");
            //User user= (User) authentication.getPrincipal();
            String username=request.getRemoteUser();
            ShoppingCart shoppingCart=shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shop-cart";
        }catch (RuntimeException exception){
            return "redirect:/shop-cart?error=hooinoi" + exception.getMessage();
        }
    }
}

package com.example.timski.controller;


import com.example.timski.model.enums.Role;
import com.example.timski.model.errors.InvalidArgumentsException;
import com.example.timski.model.errors.PasswordsDoNotMatch;
import com.example.timski.service.Authentication;
import com.example.timski.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final Authentication authentication;
    private final UserService userService;

    public RegisterController(Authentication authentication, UserService userService) {
        this.authentication = authentication;
        this.userService = userService;
    }

    @GetMapping
    public String vrati(@RequestParam(required = false) String error, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "register";
    }
    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role,
                           @RequestParam String email) {
        try{
            this.userService.register(username, password, repeatedPassword, name, surname, role, email);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatch exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}

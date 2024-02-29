package com.example.timski.controller;



import com.example.timski.model.User;
import com.example.timski.model.errors.InvalidUser;
import com.example.timski.service.Authentication;
import com.example.timski.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/login")
public class LoginController {

    private final Authentication authentication;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public LoginController(Authentication authentication, PasswordEncoder passwordEncoder, UserService userService) {
        this.authentication = authentication;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public String getloginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        UserDetails user = null;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Retrieve user by username
            user = userService.loadUserByUsername(username);

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                // Authentication successful
                request.getSession().setAttribute("user", user);
                return "redirect:/home";
            } else {
                // Invalid credentials
                throw new InvalidUser();
            }
        } catch (InvalidUser exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

}

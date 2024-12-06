package com.example.PetShop.controller;

import com.example.PetShop.models.UserModel;
import com.example.PetShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Trả về login.html
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserModel());
        return "register"; // Trả về register.html
    }

    @PostMapping("/register")
    public String registerUser(UserModel user) {
        userService.saveUser(user); // Lưu user vào DB
        return "redirect:/api/user/login"; // Chuyển hướng đến trang login
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Home Page");
        return "home"; // Tên file trong thư mục templates: home.html
    }

}

package ru.arsentiev.controller;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }
}

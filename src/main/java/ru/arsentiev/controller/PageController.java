package ru.arsentiev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/users")
    public String usersPage() {
        return "users";
    }
}
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tests")
    public String tests() {
        return "tests";
    }

    @GetMapping("/create-test")
    public String createTest() {
        return "create-test";
    }

    @GetMapping("/results")
    public String results() {
        return "results";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
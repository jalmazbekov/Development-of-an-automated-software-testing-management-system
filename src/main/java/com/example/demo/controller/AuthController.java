package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String fullName,
                               @RequestParam String role,
                               Model model) {
        try {
            if ("TEACHER".equals(role)) {
                userService.saveTeacher(username, password, fullName);
            } else {
                userService.saveStudent(username, password, fullName);
            }
            model.addAttribute("success", "Регистрация успешна! Войдите в систему.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Пользователь с таким логином уже существует");
            return "register";
        }
    }
}
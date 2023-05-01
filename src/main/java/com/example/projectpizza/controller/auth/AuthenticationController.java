package com.example.projectpizza.controller.auth;

import com.example.projectpizza.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user) {  //todo: make validator for unique name
        return null;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}

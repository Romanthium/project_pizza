package com.example.projectpizza.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @PostMapping("/register")
    public String register() {
        return null;
    }

    @PostMapping("/authenticate")
    public String authenticate() {
        return null;
    }
}

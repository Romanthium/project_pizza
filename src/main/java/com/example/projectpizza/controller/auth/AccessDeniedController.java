package com.example.projectpizza.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/access-denied")
public class AccessDeniedController {
    @GetMapping()
    public String getAccessDenied() {
        return "auth/access-denied";
    }
}

package com.example.projectpizza.controller.auth;

import com.example.projectpizza.model.User;
import com.example.projectpizza.service.UserRoleService;
import com.example.projectpizza.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final UserRoleService userRoleService;
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userRoleService.findAll());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user) {  //todo: make validator for unique name
        userService.save(user);                                             //toDo: make separate controller for users
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }
}

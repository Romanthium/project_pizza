package com.example.projectpizza.controller.auth;

import com.example.projectpizza.model.User;
import com.example.projectpizza.service.UserRoleService;
import com.example.projectpizza.service.UserService;
import com.example.projectpizza.utils.validator.auth.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    private final PasswordEncoder passwordEncoder;

    private final UserValidator userValidator;

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", userRoleService.findAll());
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult,
                               Model model) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", userRoleService.findAll());
            return "users/registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());

        return "users/users";
    }

    @GetMapping("/{id}/edit-user")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findOne(id));
        model.addAttribute("roles", userRoleService.findAll());
        return "users/edit-user";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        userValidator.validate(user, bindingResult);

        model.addAttribute("roles", userRoleService.findAll());

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", userRoleService.findAll());
            return "users/edit-user";
        }

        userService.update(id, user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
        userService.delete(id);
        attributes.addFlashAttribute("deleted", id);
        return "redirect:/users";
    }
}

package com.example.projectpizza.controller;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.service.CafeService;
import com.example.projectpizza.service.DishService;
import com.example.projectpizza.service.UserService;
import com.example.projectpizza.utils.validator.CafeValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/cafes")
public class CafesController {
    private final CafeService cafeService;
    private final DishService dishService;
    private final CafeValidator cafeValidator;
    private final UserService userService;

    @Autowired
    public CafesController(CafeService cafeService, DishService dishService, CafeValidator cafeValidator, UserService userService) {
        this.cafeService = cafeService;
        this.dishService = dishService;
        this.cafeValidator = cafeValidator;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_CAFE_MANAGER")) {
            model.addAttribute("cafes", cafeService.findAllByManagerId(getUserID(request.getUserPrincipal())));
        } else {
            model.addAttribute("cafes", cafeService.findAllOrdered());
        }
        return "cafes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        Cafe cafe = cafeService.findOne(id);
        if (request.isUserInRole("ROLE_CAFE_MANAGER")) {
            if (cafe.getManager() != null && cafe.getManager().getId().compareTo(getUserID(request.getUserPrincipal())) == 0) {
                model.addAttribute("cafe", cafe);
            } else {
                return "/auth/access-denied";
            }
        } else {
            model.addAttribute("cafe", cafe);
        }
        return "cafes/show";
    }

    @GetMapping("/new")
    public String newCafe(@ModelAttribute("cafe") Cafe cafe, Model model) {
        model.addAttribute("dishes", dishService.findAllOrdered());
        model.addAttribute("managers", userService.findAllManagers());
        return "cafes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("cafe") @Valid Cafe cafe,
                         BindingResult bindingResult, Model model) {

        cafeValidator.validate(cafe, bindingResult);

        model.addAttribute("dishes", dishService.findAllOrdered());
        model.addAttribute("managers", userService.findAllManagers());

        if (bindingResult.hasErrors()) {
            model.addAttribute("dishes", dishService.findAllOrdered());
            model.addAttribute("managers", userService.findAllManagers());
            return "cafes/new";
        }
        if (cafe.getManager().getId() == -1) { //need for deselecting
            cafe.setManager(null);
        }

        cafeService.save(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dishes", dishService.findAllOrdered());
        model.addAttribute("cafe", cafeService.findOne(id));
        model.addAttribute("managers", userService.findAllManagers());
        return "cafes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("cafe") @Valid Cafe cafe,
                         BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {

        cafeValidator.validate(cafe, bindingResult);

        model.addAttribute("dishes", dishService.findAllOrdered());
        model.addAttribute("managers", userService.findAllManagers());

        if (bindingResult.hasErrors()) {
            model.addAttribute("dishes", dishService.findAllOrdered());
            model.addAttribute("managers", userService.findAllManagers());
            return "cafes/edit";
        }
        if (cafe.getManager().getId() == -1) { //need fo deselecting
            cafe.setManager(null);
        }
        cafeService.update(id, cafe);
        return "redirect:/cafes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cafeService.delete(id);
        return "redirect:/cafes";
    }

    private Integer getUserID(Principal principal) {
        return userService.findByLogin(principal.getName()).get().getId();  //user always has an id
    }

}

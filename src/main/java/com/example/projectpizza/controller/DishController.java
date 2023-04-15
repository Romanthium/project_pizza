package com.example.projectpizza.controller;

import com.example.projectpizza.model.Dish;
import com.example.projectpizza.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "dishes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("dish", dishService.findOne(id));
        return "dishes/show";
    }

    @GetMapping("/new")
    public String newDish(@ModelAttribute("dish") Dish dish) {
        return "dishes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("dish") @Valid Dish dish,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "dishes/new";

        dishService.save(dish);
        return "redirect:/dishes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dish", dishService.findOne(id));
        return "dishes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dish") @Valid Dish dish,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "dishes/edit";

        dishService.update(id, dish);
        return "redirect:/dishes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }


}

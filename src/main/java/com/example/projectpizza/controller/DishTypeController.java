package com.example.projectpizza.controller;

import com.example.projectpizza.model.DishType;
import com.example.projectpizza.service.DishTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dish-types")
public class DishTypeController {
    private final DishTypeService dishTypeService;

    @Autowired
    public DishTypeController(DishTypeService dishTypeService) {
        this.dishTypeService = dishTypeService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("dishTypes", dishTypeService.findAll());
        return "dish-types/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("dishType", dishTypeService.findOne(id));
        return "dish-types/show";
    }

    @GetMapping("/new")
    public String newDishType(@ModelAttribute("dishType") DishType dishType) {
        return "dish-types/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("dishType") @Valid DishType dishType,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "dish-types/new";

        dishTypeService.save(dishType);
        return "redirect:/dish-types";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dishType", dishTypeService.findOne(id));
        return "dish-types/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dishType") @Valid DishType dishType,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "dish-types/edit";

        dishTypeService.update(id, dishType);
        return "redirect:/dish-types";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dishTypeService.delete(id);
        return "redirect:/dish-types";
    }


}

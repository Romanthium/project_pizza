package com.example.projectpizza.controller;

import com.example.projectpizza.model.Ingredient;
import com.example.projectpizza.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("ingredient", ingredientService.findOne(id));
        return "ingredients/show";
    }

    @GetMapping("/new")
    public String newIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        return "ingredients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("ingredient") @Valid Ingredient ingredient,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "ingredients/new";

        ingredientService.save(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("ingredient", ingredientService.findOne(id));
        return "ingredients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ingredient") @Valid Ingredient ingredient,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "ingredients/edit";

        ingredientService.update(id, ingredient);
        return "redirect:/ingredients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }


}

package com.example.projectpizza.controller;

import com.example.projectpizza.model.Dish;
import com.example.projectpizza.service.DishService;
import com.example.projectpizza.service.DishTypeService;
import com.example.projectpizza.service.IngredientService;
import com.example.projectpizza.service.UnitService;
import com.example.projectpizza.utils.validator.DishValidator;
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
    private final UnitService unitService;
    private final DishTypeService dishTypeService;
    private final IngredientService ingredientService;

    private final DishValidator dishValidator;

    @Autowired
    public DishController(DishService dishService, UnitService unitService,
                          DishTypeService dishTypeService, IngredientService ingredientService,
                          DishValidator dishValidator) {
        this.dishService = dishService;
        this.unitService = unitService;
        this.dishTypeService = dishTypeService;
        this.ingredientService = ingredientService;
        this.dishValidator = dishValidator;
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
    public String newDish(@ModelAttribute("dish") Dish dish, Model model) {

        model.addAttribute("units", unitService.findAll());
        model.addAttribute("dishTypes", dishTypeService.findAll());
        model.addAttribute("ingredients", ingredientService.findAll());

        return "dishes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("dish") @Valid Dish dish,
                         BindingResult bindingResult, Model model) {

        dishValidator.validate(dish, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("units", unitService.findAll());
            model.addAttribute("dishTypes", dishTypeService.findAll());
            model.addAttribute("ingredients", ingredientService.findAll());
            return "dishes/new";
        }

        dishService.save(dish);
        return "redirect:/dishes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("units", unitService.findAll());
        model.addAttribute("dishTypes", dishTypeService.findAll());
        model.addAttribute("ingredients", ingredientService.findAll());

        model.addAttribute("dish", dishService.findOne(id));
        return "dishes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dish") @Valid Dish dish,
                         BindingResult bindingResult, Model model,
                         @PathVariable("id") int id) {

        dishValidator.validate(dish, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("units", unitService.findAll());
            model.addAttribute("dishTypes", dishTypeService.findAll());
            model.addAttribute("ingredients", ingredientService.findAll());

            return "dishes/edit";
        }

        dishService.update(id, dish);
        return "redirect:/dishes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }


}

package com.example.projectpizza.controller;

import com.example.projectpizza.dto.IngredientDto;
import com.example.projectpizza.mapper.IngredientMapper;
import com.example.projectpizza.model.Ingredient;
import com.example.projectpizza.service.IngredientService;
import com.example.projectpizza.utils.validator.IngredientValidator;
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

    private final IngredientValidator ingredientValidator;

    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientController(IngredientService ingredientService, IngredientValidator ingredientValidator,
                                IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientValidator = ingredientValidator;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientMapper.toDto(ingredientService.findAll()));
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("ingredient", ingredientMapper.toDto(ingredientService.findOne(id)));
        return "ingredients/show";
    }

    @GetMapping("/new")
    public String newIngredient(@ModelAttribute("ingredient") IngredientDto ingredientDto) {
        return "ingredients/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("ingredient") @Valid IngredientDto ingredientDto,
                         BindingResult bindingResult) {

        ingredientValidator.validate(ingredientMapper.toEntity(ingredientDto), bindingResult);

        if (bindingResult.hasErrors())
            return "ingredients/new";

        ingredientService.save(ingredientMapper.toEntity(ingredientDto));
        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("ingredient", ingredientMapper.toDto(ingredientService.findOne(id)));
        return "ingredients/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("ingredient") @Valid IngredientDto ingredientDto,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        ingredientValidator.validate(ingredientMapper.toEntity(ingredientDto), bindingResult);

        if (bindingResult.hasErrors())
            return "ingredients/edit";

        ingredientService.update(id, ingredientMapper.toEntity(ingredientDto));
        return "redirect:/ingredients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }
}

package com.example.projectpizza.controller;

import com.example.projectpizza.dto.DishDto;
import com.example.projectpizza.mapper.DishMapper;
import com.example.projectpizza.mapper.DishTypeMapper;
import com.example.projectpizza.mapper.IngredientMapper;
import com.example.projectpizza.mapper.UnitMapper;
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

    private final DishMapper dishMapper;
    private final DishTypeMapper dishTypeMapper;
    private final IngredientMapper ingredientMapper;
    private final UnitMapper unitMapper;


    @Autowired
    public DishController(DishService dishService, UnitService unitService,
                          DishTypeService dishTypeService, IngredientService ingredientService,
                          DishValidator dishValidator, DishMapper dishMapper,
                          DishTypeMapper dishTypeMapper, IngredientMapper ingredientMapper, UnitMapper unitMapper) {
        this.dishService = dishService;
        this.unitService = unitService;
        this.dishTypeService = dishTypeService;
        this.ingredientService = ingredientService;
        this.dishValidator = dishValidator;
        this.dishMapper = dishMapper;
        this.dishTypeMapper = dishTypeMapper;
        this.ingredientMapper = ingredientMapper;
        this.unitMapper = unitMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));
        return "dishes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("dish", dishMapper.toDto(dishService.findOne(id)));
        return "dishes/show";
    }

    @GetMapping("/new")
    public String newDish(@ModelAttribute("dish") DishDto dishDto, Model model) {

        model.addAttribute("units", unitMapper.toDto(unitService.findAll()));
        model.addAttribute("dishTypes", dishTypeMapper.toDto(dishTypeService.findAll()));
        model.addAttribute("ingredients", ingredientMapper.toDto(ingredientService.findAll()));

        return "dishes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("dish") @Valid DishDto dishDto,
                         BindingResult bindingResult, Model model) {

        dishValidator.validate(dishMapper.toEntity(dishDto), bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("units", unitMapper.toDto(unitService.findAll()));
            model.addAttribute("dishTypes", dishTypeMapper.toDto(dishTypeService.findAll()));
            model.addAttribute("ingredients", ingredientMapper.toDto(ingredientService.findAll()));
            return "dishes/new";
        }

        dishService.save(dishMapper.toEntity(dishDto));
        return "redirect:/dishes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("units", unitMapper.toDto(unitService.findAll()));
        model.addAttribute("dishTypes", dishTypeMapper.toDto(dishTypeService.findAll()));
        model.addAttribute("ingredients", ingredientMapper.toDto(ingredientService.findAll()));

        model.addAttribute("dish", dishMapper.toDto(dishService.findOne(id)));
        return "dishes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dish") @Valid DishDto dishDto,
                         BindingResult bindingResult, Model model,
                         @PathVariable("id") int id) {

        dishValidator.validate(dishMapper.toEntity(dishDto), bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("units", unitMapper.toDto(unitService.findAll()));
            model.addAttribute("dishTypes", dishTypeMapper.toDto(dishTypeService.findAll()));
            model.addAttribute("ingredients", ingredientMapper.toDto(ingredientService.findAll()));

            return "dishes/edit";
        }

        dishService.update(id, dishMapper.toEntity(dishDto));
        return "redirect:/dishes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}

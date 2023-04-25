package com.example.projectpizza.controller;

import com.example.projectpizza.dto.DishTypeDto;
import com.example.projectpizza.mapper.DishTypeMapper;
import com.example.projectpizza.model.DishType;
import com.example.projectpizza.service.DishTypeService;
import com.example.projectpizza.utils.validator.DishTypeValidator;
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
    private final DishTypeValidator dishTypeValidator;

    private final DishTypeMapper dishTypeMapper;

    @Autowired
    public DishTypeController(DishTypeService dishTypeService, DishTypeValidator dishTypeValidator,
                              DishTypeMapper dishTypeMapper) {
        this.dishTypeService = dishTypeService;
        this.dishTypeValidator = dishTypeValidator;
        this.dishTypeMapper = dishTypeMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("dishTypes", dishTypeMapper.toDto(dishTypeService.findAll()));
        return "dish-types/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("dishType", dishTypeMapper.toDto(dishTypeService.findOne(id)));
        return "dish-types/show";
    }

    @GetMapping("/new")
    public String newDishType(@ModelAttribute("dishType") DishTypeDto dishTypeDto) {
        return "dish-types/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("dishType") @Valid DishTypeDto dishTypeDto,
                         BindingResult bindingResult) {

        dishTypeValidator.validate(dishTypeMapper.toEntity(dishTypeDto), bindingResult);

        if (bindingResult.hasErrors())
            return "dish-types/new";

        dishTypeService.save(dishTypeMapper.toEntity(dishTypeDto));
        return "redirect:/dish-types";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dishType", dishTypeMapper.toDto(dishTypeService.findOne(id)));
        return "dish-types/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("dishType") @Valid DishTypeDto dishTypeDto,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        dishTypeValidator.validate(dishTypeMapper.toEntity(dishTypeDto), bindingResult);

        if (bindingResult.hasErrors())
            return "dish-types/edit";

        dishTypeService.update(id, dishTypeMapper.toEntity(dishTypeDto));
        return "redirect:/dish-types";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dishTypeService.delete(id);
        return "redirect:/dish-types";
    }
}

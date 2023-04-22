package com.example.projectpizza.controller;

import com.example.projectpizza.model.Unit;
import com.example.projectpizza.service.UnitService;
import com.example.projectpizza.utils.validator.UnitValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/units")
public class UnitController {
    private final UnitService unitService;

    private final UnitValidator unitValidator;

    @Autowired
    public UnitController(UnitService unitService, UnitValidator unitValidator) {
        this.unitService = unitService;
        this.unitValidator = unitValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("units", unitService.findAll());
        return "units/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("unit", unitService.findOne(id));
        return "units/show";
    }

    @GetMapping("/new")
    public String newUnit(@ModelAttribute("unit") Unit unit) {
        return "units/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("unit") @Valid Unit unit,
                         BindingResult bindingResult) {

        unitValidator.validate(unit, bindingResult);

        if (bindingResult.hasErrors())
            return "units/new";

        unitService.save(unit);
        return "redirect:/units";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("unit", unitService.findOne(id));
        return "units/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("unit") @Valid Unit unit,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        unitValidator.validate(unit, bindingResult);

        if (bindingResult.hasErrors())
            return "units/edit";

        unitService.update(id, unit);
        return "redirect:/units";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        unitService.delete(id);
        return "redirect:/units";
    }


}

package com.example.projectpizza.controller;

import com.example.projectpizza.dto.UnitDto;
import com.example.projectpizza.mapper.UnitMapper;
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
    private final UnitMapper unitMapper;

    @Autowired
    public UnitController(UnitService unitService, UnitValidator unitValidator, UnitMapper unitMapper) {
        this.unitService = unitService;
        this.unitValidator = unitValidator;
        this.unitMapper = unitMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("units", unitMapper.toDto(unitService.findAll()));
        return "units/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("unit", unitMapper.toDto(unitService.findOne(id)));
        return "units/show";
    }

    @GetMapping("/new")
    public String newUnit(@ModelAttribute("unit") UnitDto unitDto) {
        return "units/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("unit") @Valid UnitDto unitDto,
                         BindingResult bindingResult) {

        unitValidator.validate(unitMapper.toEntity(unitDto), bindingResult);

        if (bindingResult.hasErrors())
            return "units/new";

        unitService.save(unitMapper.toEntity(unitDto));
        return "redirect:/units";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("unit", unitMapper.toDto(unitService.findOne(id)));
        return "units/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("unit") @Valid UnitDto unitDto,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        unitValidator.validate(unitMapper.toEntity(unitDto), bindingResult);

        if (bindingResult.hasErrors())
            return "units/edit";

        unitService.update(id, unitMapper.toEntity(unitDto));
        return "redirect:/units";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        unitService.delete(id);
        return "redirect:/units";
    }
}

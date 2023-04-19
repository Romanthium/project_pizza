package com.example.projectpizza.controller;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.service.CafeService;
import com.example.projectpizza.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cafes")
public class CafesController {
    private final CafeService cafeService;
    private final DishService dishService;

    @Autowired
    public CafesController(CafeService cafeService, DishService dishService) {
        this.cafeService = cafeService;
        this.dishService = dishService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cafes", cafeService.findAll());
        return "cafes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("cafe", cafeService.findOne(id));
        return "cafes/show";
    }

    @GetMapping("/new")
    public String newCafe(@ModelAttribute("cafe") Cafe cafe, Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "cafes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("cafe") @Valid Cafe cafe,
                         BindingResult bindingResult, Model model) {
        model.addAttribute("dishes", dishService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("dishes", dishService.findAll());
            return "cafes/new";
        }

        cafeService.save(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dishes", dishService.findAll());
        model.addAttribute("cafe", cafeService.findOne(id));
        return "cafes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("cafe") @Valid Cafe cafe,
                         BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {
        model.addAttribute("dishes", dishService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("dishes", dishService.findAll());
            return "cafes/edit";
        }

        cafeService.update(id, cafe);
        return "redirect:/cafes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cafeService.delete(id);
        return "redirect:/cafes";
    }


}

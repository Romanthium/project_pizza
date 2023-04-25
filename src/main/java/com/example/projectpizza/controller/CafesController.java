package com.example.projectpizza.controller;

import com.example.projectpizza.dto.CafeDto;
import com.example.projectpizza.mapper.CafeMapper;
import com.example.projectpizza.mapper.DishMapper;
import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.service.CafeService;
import com.example.projectpizza.service.DishService;
import com.example.projectpizza.utils.validator.CafeValidator;
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
    private final CafeValidator cafeValidator;
    private final CafeMapper cafeMapper;
    private final DishMapper dishMapper;

    @Autowired
    public CafesController(CafeService cafeService, DishService dishService,
                           CafeValidator cafeValidator, CafeMapper cafeMapper, DishMapper dishMapper) {
        this.cafeService = cafeService;
        this.dishService = dishService;
        this.cafeValidator = cafeValidator;
        this.cafeMapper = cafeMapper;
        this.dishMapper = dishMapper;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cafes", cafeMapper.toDto(cafeService.findAll()));
        return "cafes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("cafe", cafeMapper.toDto(cafeService.findOne(id)));
        return "cafes/show";
    }

    @GetMapping("/new")
    public String newCafe(@ModelAttribute("cafe") CafeDto cafeDto, Model model) {
        model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));
        return "cafes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("cafe") @Valid CafeDto cafeDto,
                         BindingResult bindingResult, Model model) {

        cafeValidator.validate(cafeMapper.toEntity(cafeDto), bindingResult);

        model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));

        if (bindingResult.hasErrors()) {
            model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));
            return "cafes/new";
        }

        cafeService.save(cafeMapper.toEntity(cafeDto));
        return "redirect:/cafes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));
        model.addAttribute("cafe", cafeMapper.toDto(cafeService.findOne(id)));
        return "cafes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("cafe") /*@Valid*/ CafeDto cafeDto,
                         BindingResult bindingResult,
                         @PathVariable("id") int id, Model model) {

//        cafeValidator.validate(cafeMapper.toEntity(cafeDto), bindingResult);

        model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));

//        if (bindingResult.hasErrors()) {
//            model.addAttribute("dishes", dishMapper.toDto(dishService.findAll()));
//            return "cafes/edit";
//        }

//        Cafe cafe = cafeMapper.toEntity(cafeDto);
//        cafe.setDishes(dishMapper.toEntity(cafeDto.getDishes()));
//
//        cafeService.update(id, cafe);
        cafeService.update(id, cafeMapper.toEntity(cafeDto));
        return "redirect:/cafes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cafeService.delete(id);
        return "redirect:/cafes";
    }
}

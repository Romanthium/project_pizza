package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.Dish;
import com.example.projectpizza.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DishValidator implements Validator {

    private final DishService dishService;

    @Autowired
    public DishValidator(DishService dishService) {
        this.dishService = dishService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Dish.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dish dish = (Dish) target;

        if (dishService.findByName(dish.getName()).isPresent()
                || dishService.findByNameAndIdNot(dish.getName(), dish.getId()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Dish;
import com.example.projectpizza.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishValidator extends NameValidator<Dish> {

    @Autowired
    public DishValidator(DishService dishService) {
        super(dishService);
    }

    @Override
    protected Class<Dish> getSupportedClass() {
        return Dish.class;
    }
}

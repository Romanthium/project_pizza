package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.DishType;
import com.example.projectpizza.service.DishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DishTypeValidator implements Validator {
    private final DishTypeService dishTypeService;

    @Autowired
    public DishTypeValidator(DishTypeService dishTypeService) {
        this.dishTypeService = dishTypeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return DishType.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DishType dishType = (DishType) target;

        if (dishTypeService.findByName(dishType.getName()).isPresent()
                || dishTypeService.findByNameAndIdNot(dishType.getName(), dishType.getId()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

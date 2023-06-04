package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.DishType;
import com.example.projectpizza.service.DishTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishTypeValidator extends NameValidator<DishType> {

    @Autowired
    public DishTypeValidator(DishTypeService dishTypeService) {
        super(dishTypeService);
    }

    @Override
    protected Class<DishType> getSupportedClass() {
        return DishType.class;
    }
}

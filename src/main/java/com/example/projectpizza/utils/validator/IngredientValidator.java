package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Ingredient;
import com.example.projectpizza.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientValidator extends NameValidator<Ingredient> {

    @Autowired
    public IngredientValidator(IngredientService ingredientService) {
        super(ingredientService);
    }

    @Override
    protected Class<Ingredient> getSupportedClass() {
        return Ingredient.class;
    }
}

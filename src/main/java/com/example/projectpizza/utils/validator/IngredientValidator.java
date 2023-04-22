package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.Ingredient;
import com.example.projectpizza.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IngredientValidator implements Validator {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientValidator(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Ingredient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
         Ingredient ingredient = (Ingredient) target;

        if (ingredientService.findByName(ingredient.getName()).isPresent()
                || ingredientService.findByNameAndIdNot(ingredient.getName(), ingredient.getId()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

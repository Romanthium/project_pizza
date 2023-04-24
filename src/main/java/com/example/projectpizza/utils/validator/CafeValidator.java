package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CafeValidator implements Validator {
    private final CafeService cafeService;

    @Autowired
    public CafeValidator(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Cafe.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cafe cafe = (Cafe) target;

        if ((cafeService.findByName(cafe.getName()).isPresent() && cafe.getId() == null)
                || (cafeService.findByNameAndIdNot(cafe.getName(), cafe.getId()).isPresent())) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

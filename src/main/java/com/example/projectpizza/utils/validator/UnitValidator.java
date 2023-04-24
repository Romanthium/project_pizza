package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Unit;
import com.example.projectpizza.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UnitValidator implements Validator {
    private final UnitService unitService;

    @Autowired
    public UnitValidator(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Unit.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Unit unit = (Unit) target;

        if ((unitService.findByName(unit.getName()).isPresent() && unit.getId() == null)
                || (unitService.findByNameAndIdNot(unit.getName(), unit.getId()).isPresent())) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

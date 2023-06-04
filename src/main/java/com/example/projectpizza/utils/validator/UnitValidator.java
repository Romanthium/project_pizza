package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Unit;
import com.example.projectpizza.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitValidator extends NameValidator<Unit> {

    @Autowired
    public UnitValidator(UnitService unitService) {
        super(unitService);
    }

    @Override
    protected Class<Unit> getSupportedClass() {
        return Unit.class;
    }
}

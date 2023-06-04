package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CafeValidator extends NameValidator<Cafe> {

    @Autowired
    public CafeValidator(CafeService cafeService) {
        super(cafeService);
    }

    @Override
    protected Class<Cafe> getSupportedClass() {
        return Cafe.class;
    }
}

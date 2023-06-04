package com.example.projectpizza.utils.validator;

import com.example.projectpizza.model.AbstractEntity;
import com.example.projectpizza.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public abstract class NameValidator<T extends AbstractEntity> implements Validator {
    protected final EntityService<T> entityService;

    @Autowired
    protected NameValidator(EntityService<T> entityService) {
        this.entityService = entityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return getSupportedClass().equals(clazz);
    }

    protected abstract Class<T> getSupportedClass();

    @Override
    public void validate(Object target, Errors errors) {
        T entity = getSupportedClass().cast(target);

        if ((entity.getId() == null && entityService.findByName(entity.getName()).isPresent())
                || (entityService.findByNameAndIdNot(entity.getName(), entity.getId()).isPresent())) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}

package com.example.projectpizza.utils.validator.auth;

import com.example.projectpizza.model.User;
import com.example.projectpizza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByLogin(user.getLogin()).isPresent() && user.getId() == null
                || (userService.findByLoginAndIdNot(user.getLogin(), user.getId()).isPresent())) {
            errors.rejectValue("login", "", "This user name is already taken");
        }
    }
}

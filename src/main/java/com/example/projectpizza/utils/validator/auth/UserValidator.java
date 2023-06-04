package com.example.projectpizza.utils.validator.auth;

import com.example.projectpizza.model.User;
import com.example.projectpizza.service.UserService;
import com.example.projectpizza.utils.validator.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends NameValidator<User> {

    @Autowired
    public UserValidator(UserService userService) {
        super(userService);
    }

    @Override
    protected Class<User> getSupportedClass() {
        return User.class;
    }
}

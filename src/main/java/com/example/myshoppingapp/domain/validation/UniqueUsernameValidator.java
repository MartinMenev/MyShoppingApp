package com.example.myshoppingapp.domain.validation;

import com.example.myshoppingapp.domain.users.RegisterUserDTO;
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    @Autowired
    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository
                .findByUsername(username)
                .isEmpty();
    }
}

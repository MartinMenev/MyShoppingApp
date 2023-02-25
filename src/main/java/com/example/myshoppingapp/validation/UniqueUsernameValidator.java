package com.example.myshoppingapp.validation;

import com.example.myshoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository userRepository;

    @Autowired
    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository
<<<<<<< HEAD
                .findUserEntityByUsername(username)
=======
                .findByUsername(username)
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
                .isEmpty();
    }
}

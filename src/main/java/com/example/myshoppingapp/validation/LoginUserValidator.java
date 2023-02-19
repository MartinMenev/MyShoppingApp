package com.example.myshoppingapp.validation;

import com.example.myshoppingapp.domain.users.LoginDTO;
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class LoginUserValidator implements ConstraintValidator<ValidateLoginUser, LoginDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LoginUserValidator(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initialize(ValidateLoginUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LoginDTO loginDTO, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(loginDTO.getUsername());
        return loginCandidate.isPresent()
                && loginCandidate.get()
                .getPassword()
                .equals(loginDTO.getPassword());

    }
}

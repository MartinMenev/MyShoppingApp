package com.example.myshoppingapp.validation;

import com.example.myshoppingapp.domain.users.LoginDTO;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
import com.example.myshoppingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
=======
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class LoginUserValidator implements ConstraintValidator<ValidateLoginUser, LoginDTO> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LoginUserValidator(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
=======

    @Autowired
    public LoginUserValidator(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    }

    @Override
    public void initialize(ValidateLoginUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LoginDTO loginDTO, ConstraintValidatorContext constraintValidatorContext) {
<<<<<<< HEAD
        Optional<UserEntity> loginCandidate = this.userRepository.findUserEntityByUsername(loginDTO.getUsername());
        return loginCandidate.isPresent()
                && passwordEncoder
                .matches(loginDTO.getPassword(),loginCandidate.get().getPassword());
=======
        Optional<User> loginCandidate = this.userRepository.findByUsername(loginDTO.getUsername());
        return loginCandidate.isPresent()
                && loginCandidate.get()
                .getPassword()
                .equals(loginDTO.getPassword());
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    }
}

package com.example.myshoppingapp.domain.validation;

import com.example.myshoppingapp.domain.users.RegisterUserDTO;
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, RegisterUserDTO> {

  private final UserRepository userRepository;

  @Autowired
  public UniqueUserEmailValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void initialize(UniqueUserEmail constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(RegisterUserDTO registerUserDTO,
                         ConstraintValidatorContext constraintValidatorContext) {
    Optional<User> registerCandidate = this.userRepository.findByEmail(registerUserDTO.getEmail());
    return registerCandidate.isEmpty();
  }
}

package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.enums.UserRole;
import com.example.myshoppingapp.domain.users.LoginDTO;
import com.example.myshoppingapp.domain.users.RegisterUserDTO;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.ProductRepository;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import com.example.myshoppingapp.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
=======
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private LoggedUser loggedUser;
<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
=======

    @Autowired
    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    }

    @Modifying
    public boolean register(RegisterUserDTO registerUserDTO) {
<<<<<<< HEAD
        UserEntity userEntity = this.modelMapper.map(registerUserDTO, UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        if (this.userRepository.count() == 0) {
            userEntity.setUserRole(UserRole.ADMIN);
        } else {
            userEntity.setUserRole(UserRole.USER);
        }
        this.userRepository.save(userEntity);
=======
        User user = this.modelMapper.map(registerUserDTO, User.class);
        // set passwordEncoder.encode(password)....
        if (this.userRepository.count() == 0) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }
        this.userRepository.save(user);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        return true;
    }



    public void login(LoginDTO loginDTO) {
<<<<<<< HEAD
        UserEntity userEntity = userRepository.findUserEntityByUsername(loginDTO.getUsername()).get();
        this.loggedUser
                .setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setUserRole(userEntity.getUserRole());
=======
        User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).get();
        this.loggedUser
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setUserRole(user.getUserRole());
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    }

    public void logout() {
        this.loggedUser.clearFields();
    }
}

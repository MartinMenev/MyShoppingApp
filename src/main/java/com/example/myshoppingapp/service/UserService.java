package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.users.LoginDTO;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(LoginDTO loginDTO) {
        return this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
    }
}

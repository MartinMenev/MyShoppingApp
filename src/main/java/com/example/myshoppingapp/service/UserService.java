package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.users.LoginDTO;
import com.example.myshoppingapp.models.users.RegisterUserDTO;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Getter
@Setter
@Service
public class UserService {

    private UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private String loggedInUser;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.loggedInUser = null;
    }



    public User login(LoginDTO loginDTO) {
        User user = this.userRepository
                .findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).orElseThrow(NoSuchElementException::new);

        this.loggedInUser = user.getUsername();

        return user;
    }

    @Modifying
    public Boolean register(RegisterUserDTO registerUserDTO) {
        User user = this.modelMapper.map(registerUserDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setIsAdmin(true);
        }

        this.userRepository.save(user);
        return true;
    }

    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
    }
}



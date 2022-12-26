package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.users.LoginDTO;
import com.example.myshoppingapp.models.users.RegisterUserDTO;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Getter
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

        System.out.println(user);
        this.userRepository.save(user);
        return true;
    }
}



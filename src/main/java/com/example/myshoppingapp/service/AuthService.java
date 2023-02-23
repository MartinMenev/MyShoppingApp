package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.enums.UserRole;
import com.example.myshoppingapp.domain.users.LoginDTO;
import com.example.myshoppingapp.domain.users.RegisterUserDTO;
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.ProductRepository;
import com.example.myshoppingapp.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private LoggedUser loggedUser;

    @Autowired
    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Modifying
    public boolean register(RegisterUserDTO registerUserDTO) {
        User user = this.modelMapper.map(registerUserDTO, User.class);
        // set passwordEncoder.encode(password)....
        if (this.userRepository.count() == 0) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            user.setUserRole(UserRole.USER);
        }
        this.userRepository.save(user);
        return true;
    }



    public void login(LoginDTO loginDTO) {
        User user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword()).get();
        this.loggedUser
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setUserRole(user.getUserRole());
    }

    public void logout() {
        this.loggedUser.clearFields();
    }
}

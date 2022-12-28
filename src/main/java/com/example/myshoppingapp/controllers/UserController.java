package com.example.myshoppingapp.controllers;

import com.example.myshoppingapp.models.users.LoginDTO;
import com.example.myshoppingapp.models.users.RegisterUserDTO;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(LoginDTO loginDTO) throws NoSuchFieldException {

        User user = userService.login(loginDTO);

        if (user != null) {
            return "redirect:/home";
        }

        return "user/login";
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/users/register")
    public String doRegister(RegisterUserDTO registerUserDTO) {
       Boolean successfulRegistration = userService.register(registerUserDTO);

        if (successfulRegistration) {
            return "redirect:/home";
        }

        return "user/register";
    }


}

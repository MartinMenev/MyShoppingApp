package com.example.myshoppingapp.web;

import com.example.myshoppingapp.models.products.InputProductDTO;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.*;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
//        this.userService.setLoggedInUser(null);
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

    @GetMapping("user/profile")
    public String ShowUserProfile(Model model){
        UserOutputDTO userOutputDTO = this.userService.getLoggedUserDTO();
        model.addAttribute("user", userOutputDTO);
        return "user/profile";
    }

    @GetMapping("/update-user")
    public String updateProduct(Model model) {
        UserOutputDTO currentUser = this.userService.getLoggedUserDTO();
        model.addAttribute("user", currentUser);
        return "user/update-user";
    }

    @PutMapping("/update-user")
    public String doUpdateProduct(Model model, UserInputDTO userInputDTO) {
        UserOutputDTO currentUser = this.userService.getLoggedUserDTO();
        model.addAttribute("user", currentUser);
        userService.updateUser(userInputDTO);
        return "redirect:/user/profile";
    }

}

package com.example.myshoppingapp.web;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.users.*;
import com.example.myshoppingapp.service.PictureService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final PictureService pictureService;

    @Autowired
    public UserController(UserService userService, PictureService pictureService) {
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(LoginDTO loginDTO)  {
        LoggedUser user = userService.login(loginDTO);

        if (user != null) {
            return "redirect:/home";
        }

        return "user/login";
    }

    @PostMapping("/users/logout")
    public String doLogout(){
        this.userService.logout();
        return "redirect:/users/login";
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO initForm() {
        return new RegisterUserDTO();
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/users/register")
    public String doRegister(@Valid RegisterUserDTO registerUserDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO", bindingResult);
            redirectAttributes.addFlashAttribute("registerUserDTO", registerUserDTO);


            return "redirect:/register";
        }
       userService.register(registerUserDTO);
        return "redirect:/users/login";
    }

    @GetMapping("user/profile")
    public String ShowUserProfile(Model model){
        UserOutputDTO userOutputDTO = this.userService.getLoggedUserDTO();
        String currentUrl = this.pictureService.getPictureUrlByLoggedUser();
        model.addAttribute("pictureUrl", currentUrl);
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

    @GetMapping("/delete-profile/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

}

package com.example.myshoppingapp.web;

import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("username", userService.getLoggedInUser());
        this.userService.setLoggedInUser(null);
        return "index2";
    }



    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("username", userService.getLoggedInUser());
        return "home";
    }
}

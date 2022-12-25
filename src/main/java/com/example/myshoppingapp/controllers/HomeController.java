package com.example.myshoppingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {

//        model.addAttribute("username", "Pesho");

        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }


}

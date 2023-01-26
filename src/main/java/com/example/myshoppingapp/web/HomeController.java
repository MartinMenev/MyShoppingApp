package com.example.myshoppingapp.web;

import com.example.myshoppingapp.service.ProductService;
import com.example.myshoppingapp.service.RecipeService;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;
    private final ProductService productService;
    private final RecipeService recipeService;

    @Autowired
    public HomeController(UserService userService, ProductService productService, RecipeService recipeService) {
        this.userService = userService;
        this.productService = productService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("username", userService.getLoggedInUser());
        model.addAttribute("recipes", recipeService.showLast3Recipes());
        this.userService.setLoggedInUser(null);
        return "index";
    }



    @GetMapping("home")
    public String home(Model model) {
        model.addAttribute("username", userService.getLoggedInUser());
        if (userService.getLoggedInUser() !=null) {
            model.addAttribute("products", productService.getListedProducts());
        }
        return "home";
    }
}

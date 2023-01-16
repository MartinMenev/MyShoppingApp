package com.example.myshoppingapp.web;

import com.example.myshoppingapp.models.recipes.InputRecipeDTO;
import com.example.myshoppingapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/add-recipe")
    public String addRecipe(){
        return "recipe/add-recipe";
    }

    @PostMapping("/add-recipe")
    public String doAddRecipe(InputRecipeDTO inputRecipeDTO){
            recipeService.addRecipe(inputRecipeDTO);
        return "redirect:/all-recipes";
    }
    @GetMapping("/all-recipes")
    public String showRecipes(
        Model model,
        @PageableDefault(
                sort = "position",
                direction = Sort.Direction.DESC,
                page = 0,
                size = 5) Pageable pageable) {
            model.addAttribute("recipes", recipeService.showAllRecipes(pageable));
            return "recipe/all-recipes";
    }

}

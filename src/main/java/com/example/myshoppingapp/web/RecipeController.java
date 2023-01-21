package com.example.myshoppingapp.web;

import com.example.myshoppingapp.models.pictures.OutputPictureDTO;
import com.example.myshoppingapp.models.recipes.InputRecipeDTO;
import com.example.myshoppingapp.models.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.service.CommentService;
import com.example.myshoppingapp.service.PictureService;
import com.example.myshoppingapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CommentService commentService;
    private final PictureService pictureService;

    @Autowired
    public RecipeController(RecipeService recipeService, CommentService commentService, PictureService pictureService) {
        this.recipeService = recipeService;
        this.commentService = commentService;
        this.pictureService = pictureService;
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

    @GetMapping("/recipe/{id}")
    public String reviewRecipe(@PathVariable(value = "id") Long id, Model model) {
        OutputRecipeDTO outputRecipeDTO = recipeService.getRecipeById(id);
        model.addAttribute("recipe", outputRecipeDTO);
        model.addAttribute("comments", commentService.showLatestComments(id));
//        List<OutputPictureDTO> allPics = this.pictureService.getAllPictures();
//        model.addAttribute("pictures", allPics);
        return "recipe/recipe-details";
    }

}

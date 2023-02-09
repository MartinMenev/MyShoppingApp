package com.example.myshoppingapp.web;

import com.example.myshoppingapp.domain.enums.Category;
import com.example.myshoppingapp.domain.recipes.InputRecipeDTO;
import com.example.myshoppingapp.domain.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.service.CommentService;
import com.example.myshoppingapp.service.PictureService;
import com.example.myshoppingapp.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                size = 9) Pageable pageable) {
            model.addAttribute("recipes", recipeService.showAllRecipes(pageable));

            return "recipe/all-recipes";
    }

    @GetMapping("/recipe/{id}")
    public String reviewRecipe(@PathVariable(value = "id") Long id, Model model) {
        OutputRecipeDTO outputRecipeDTO = recipeService.getRecipeById(id);
        model.addAttribute("recipe", outputRecipeDTO);
        model.addAttribute("comments", commentService.showLatestComments(id));
        return "recipe/recipe-details";
    }

    @GetMapping("/edit-recipe/{id}")
    public String editRecipe(@PathVariable(value = "id") Long id, Model model) {
        OutputRecipeDTO outputRecipeDTO = recipeService.getRecipeById(id);
        model.addAttribute("recipe", outputRecipeDTO);
        return "recipe/update-recipe";
    }

    @PutMapping("/update-recipe/{id}")
    public String doEditRecipe(@PathVariable(value = "id") Long id, Model model, InputRecipeDTO inputRecipeDTO) {
        OutputRecipeDTO outputRecipeDTO = recipeService.getRecipeById(id);
        model.addAttribute("recipe", outputRecipeDTO);
        recipeService.updateRecipe(inputRecipeDTO);
        return "redirect:/recipe/{id}";
    }

    @GetMapping("/delete-recipe/{id}")
    public String deleteById(@PathVariable(value = "id") long id) {
        recipeService.deleteById(id);
        return "redirect:/all-recipes";
    }

    @GetMapping("/filter-recipes")
    public String reviewRecipe(@RequestParam(value = "category") String category, Model model) {
        List<OutputRecipeDTO> recipeDTOList = this.recipeService.getRecipesByCategory(category);
        model.addAttribute("recipes", recipeDTOList);
        model.addAttribute("allRecipes", recipeService.showLast5Recipes());
        model.addAttribute("category", category);
        return "recipe/filter-recipe-by";
    }

    @GetMapping("/search-recipes")
    public String searchRecipes(@RequestParam(value = "text") String text, Model model) {
        List<OutputRecipeDTO> recipeDTOList = this.recipeService.getRecipesByTextContent(text);
        model.addAttribute("recipes", recipeDTOList);
        model.addAttribute("allRecipes", recipeService.showLast5Recipes());
        model.addAttribute("text", text);
        return "recipe/search-recipes";
    }

}

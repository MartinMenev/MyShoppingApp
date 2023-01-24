package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.recipes.InputRecipeDTO;
import com.example.myshoppingapp.model.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.model.recipes.Recipe;
import com.example.myshoppingapp.model.users.User;
import com.example.myshoppingapp.repository.RecipeRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Getter
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public void addRecipe(InputRecipeDTO inputRecipeDTO) {
        User authorId = this.userService.findByUsername(userService.getLoggedInUser());
        if (inputRecipeDTO.getImageUrl().isBlank()) {
            inputRecipeDTO.setImageUrl("https://images.pexels.com/photos/4033165/pexels-photo-4033165.jpeg?auto=compress&cs=tinysrgb&w=1600");
        }
        Recipe recipe = this.modelMapper.map(inputRecipeDTO, Recipe.class);
        recipe.setAuthor(authorId);
        this.recipeRepository.save(recipe);
        recipe.setPosition(recipe.getId());
        this.recipeRepository.saveAndFlush(recipe);
    }

    public Page<OutputRecipeDTO> showAllRecipes(Pageable pageable) {
        return recipeRepository.
                findAll(pageable).
                map(recipe -> this.modelMapper.map(recipe, OutputRecipeDTO.class));

    }

    public OutputRecipeDTO getRecipeById(Long id) {
        Recipe recipe = this.recipeRepository
                .getRecipeById(id)
                .orElseThrow(NoSuchElementException::new);
        return modelMapper.map(recipe, OutputRecipeDTO.class);
    }

    @Transactional
    @Modifying
    public void addRecipeRating(double rating, long id) {
        Recipe recipe = this.recipeRepository.getById(id);
        recipe.addRating(rating);
        this.recipeRepository.saveAndFlush(recipe);
    }
}

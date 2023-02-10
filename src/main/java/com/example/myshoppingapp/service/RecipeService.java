package com.example.myshoppingapp.service;

import com.example.myshoppingapp.domain.beans.LoggedUser;
import com.example.myshoppingapp.domain.enums.Category;
import com.example.myshoppingapp.domain.pictures.Picture;
import com.example.myshoppingapp.domain.recipes.InputRecipeDTO;
import com.example.myshoppingapp.domain.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.domain.recipes.Recipe;
import com.example.myshoppingapp.domain.users.User;
import com.example.myshoppingapp.repository.RecipeRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }


    @Transactional
    @Modifying
    public void addRecipe(InputRecipeDTO inputRecipeDTO) {
        User authorId = this.userService.findByUsername(loggedUser.getUsername());
        if (inputRecipeDTO.getImageUrl().isBlank()) {
            inputRecipeDTO.setImageUrl("https://images.pexels.com/photos/4033165/pexels-photo-4033165.jpeg?auto=compress&cs=tinysrgb&w=1600");
        }

        Recipe recipe = this.modelMapper.map(inputRecipeDTO, Recipe.class);
        recipe
                .setAuthor(authorId)
                .addPicture(new Picture(inputRecipeDTO.getImageUrl(), authorId));
        this.recipeRepository.save(recipe);
        recipe.setPosition(recipe.getId());
        this.recipeRepository.saveAndFlush(recipe);
    }

    public Page<OutputRecipeDTO> showAllRecipes(Pageable pageable) {
        return recipeRepository.
                findAll(pageable).
                map(recipe -> this.modelMapper.map(recipe, OutputRecipeDTO.class));

    }

    public List<OutputRecipeDTO> showLast5Recipes() {
        return this.recipeRepository
                .findAll()
                .stream()
                .map(recipe -> modelMapper.map(recipe, OutputRecipeDTO.class))
                .sorted((a, b) -> b.getId().compareTo(a.getId()))
                .limit(5)
                .toList();
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

    public List<Recipe> showRecipesByLoggedUser() {
        return this.recipeRepository
                .findAllByAuthorOrderByIdDesc(this.userService.getLoggedUser());
    }

    public List<OutputRecipeDTO> getRecipesByCategory(String category) {
        return this.recipeRepository.findAllByCategory(Category.valueOf(category)).orElseThrow(null)
                .stream().map(recipe -> (modelMapper.map(recipe, OutputRecipeDTO.class)))
                .toList();
    }

    public List<OutputRecipeDTO> getRecipesByTextContent(String text) {
        return this.recipeRepository.findAllContainingSearchText(text).orElseThrow(null)
                .stream().map(recipe -> (modelMapper.map(recipe, OutputRecipeDTO.class)))
                .toList();

    }

    @Modifying
    @Transactional
    public void deleteById(long id) {
        this.recipeRepository.deleteById(id);
    }

    @Modifying
    @Transactional
    public void updateRecipe(InputRecipeDTO inputRecipeDTO) {
        Recipe recipeToUpdate = this.recipeRepository.getRecipeById(inputRecipeDTO.getId()).get();
        recipeToUpdate
                .setName(inputRecipeDTO.getName())
                .setUrl(inputRecipeDTO.getUrl())
                .setDescription(inputRecipeDTO.getDescription())
                .setCategory(inputRecipeDTO.getCategory());

      if (!inputRecipeDTO.getImageUrl().isEmpty()) {
            Picture picture = new Picture(inputRecipeDTO.getImageUrl(), recipeToUpdate.getAuthor());
            recipeToUpdate.addPicture(picture);
       }

        this.recipeRepository.save(recipeToUpdate);

    }

}

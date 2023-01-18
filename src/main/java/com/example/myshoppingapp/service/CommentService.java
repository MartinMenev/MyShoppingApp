package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.comments.InputCommentDTO;
import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.repositories.CommentRepository;
import com.example.myshoppingapp.repositories.RecipeRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserService userService;
    private final RecipeService recipeService;


    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, RecipeService recipeService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.recipeService = recipeService;
    }


    public void addComment(InputCommentDTO inputCommentDTO, Long recipeId) {
        inputCommentDTO.setId(null);
        Comment comment = modelMapper.map(inputCommentDTO, Comment.class);
        String authorName = this.userService.getLoggedInUser();
        if (authorName == null) {
            authorName = "Guest";
        }
        Recipe recipe = this.recipeService.getRecipeRepository().getById(recipeId);
        comment.setRecipe(recipe);
        comment.setAuthorName(authorName);
        this.commentRepository.saveAndFlush(comment);
    }


}

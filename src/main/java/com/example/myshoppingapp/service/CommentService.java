package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.comments.InputCommentDTO;
import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.CommentRepository;
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
        User author = this.userService.findByUsername(userService.getLoggedInUser());
        Recipe recipe = modelMapper
                .map(this.recipeService.getRecipeById(recipeId), Recipe.class);

        Comment comment = modelMapper.map(inputCommentDTO, Comment.class);
        this.commentRepository.saveAndFlush(comment);
        comment.setAuthor(author);
        comment.setRecipe(recipe);
        this.commentRepository.saveAndFlush(comment);
    }


}

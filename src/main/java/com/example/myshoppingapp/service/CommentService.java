package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.comments.InputCommentDTO;
import com.example.myshoppingapp.models.comments.OutputCommentDTO;
import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.CommentRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        User author = this.userService.findByUsername(userService.getLoggedInUser());
        Recipe recipe = this.recipeService.getRecipeRepository().getById(recipeId);
        comment.setRecipe(recipe);
        if (author != null) {
            comment.setAuthor(author);
            this.commentRepository.saveAndFlush(comment);
        }
    }

    public List<OutputCommentDTO> showAllComments(Long recipeId) {
        return this.commentRepository
                .findAllByRecipeIdOrderByIdDesc(recipeId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(comment -> this.modelMapper.map(comment, OutputCommentDTO.class))
                .toList();
    }
    public List<OutputCommentDTO> showLatestComments(Long recipeId) {
        return showAllComments(recipeId)
                .stream()
                .limit(3)
                .toList();
    }
}

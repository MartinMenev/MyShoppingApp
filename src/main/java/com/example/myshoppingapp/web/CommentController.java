package com.example.myshoppingapp.web;

import com.example.myshoppingapp.models.comments.InputCommentDTO;
import com.example.myshoppingapp.models.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/save-comment/{id}")
    public String reviewRecipe(@PathVariable(value = "id") Long id,
                               InputCommentDTO inputCommentDTO) {
        commentService.addComment(inputCommentDTO, id);
        return "redirect:/recipe/{id}";
    }

}

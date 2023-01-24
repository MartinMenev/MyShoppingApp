package com.example.myshoppingapp.model.comments;

import com.example.myshoppingapp.model.recipes.Recipe;
import com.example.myshoppingapp.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class InputCommentDTO {
    private Long id;
    private String text;
    private LocalDateTime dateAdded;
    private long rating;
    private User author;
    private Recipe recipe;

    public InputCommentDTO() {
        this.dateAdded = LocalDateTime.now();
    }
}

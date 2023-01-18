package com.example.myshoppingapp.models.comments;

import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputCommentDTO {
    private Long id;
    private String text;
    private LocalDateTime dateAdded;
    private long rating;
    private String authorName;
    private Recipe recipe;
}

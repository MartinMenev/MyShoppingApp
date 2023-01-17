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
public class InputCommentDTO {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime dateAdded;
    private long rating;
    private User author;
    private Recipe recipe;

}

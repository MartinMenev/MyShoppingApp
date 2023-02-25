package com.example.myshoppingapp.domain.comments;

import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
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
<<<<<<< HEAD
    private UserEntity author;
=======
    private User author;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    private Recipe recipe;

    public InputCommentDTO() {
        this.dateAdded = LocalDateTime.now();
    }
}

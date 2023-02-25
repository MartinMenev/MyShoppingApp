package com.example.myshoppingapp.domain.comments;

import com.example.myshoppingapp.domain.pictures.Picture;
import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputCommentDTO {

    private Long id;
    private String text;
    private LocalDateTime dateAdded;
    private Long rating;
<<<<<<< HEAD
    private UserEntity author;
=======
    private User author;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
    private Recipe recipe;

    private LocalDate boughtOn;
    public String getDate(){
        return this.dateAdded.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String getAuthorPicUrl() {

        Picture picture = this.author.getPicture();
        if (picture == null) {
            return null;
        }

        return "/images/"+ picture.getPictureUrl();
    }

    public String getRecipeName() {
        return this.recipe.getName();
    }
}

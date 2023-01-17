package com.example.myshoppingapp.models.recipes;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.enums.Category;
import com.example.myshoppingapp.models.pictures.Picture;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutputRecipeDTO {
    private Long id;
    private String name;
    private String url;
    private String description;
    private Category category;
    private long rating;
    private Picture picture;
    private long position;
    private String imageUrl;
    private User author;
    private List<Comment> commentList;

    private LocalDate dateAdded;

    public String getAuthorName(){
        return this.author.getUsername();
    }

    public boolean hasImageUrl(){
        return this.imageUrl != null;
    }
}

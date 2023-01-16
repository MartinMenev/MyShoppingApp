package com.example.myshoppingapp.models.recipes;

import com.example.myshoppingapp.models.enums.Category;
import com.example.myshoppingapp.models.pictures.Picture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputRecipeDTO implements Serializable {
    private Long id;
    private String name;
    private String url;
    private String description;
    private Category category;
    private long rating;
    private Picture picture;
    private long position;
    private String imageUrl;

}

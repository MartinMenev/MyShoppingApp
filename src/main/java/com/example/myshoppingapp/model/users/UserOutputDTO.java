package com.example.myshoppingapp.model.users;

import com.example.myshoppingapp.model.enums.UserRole;
import com.example.myshoppingapp.model.pictures.Picture;
import com.example.myshoppingapp.model.products.Product;
import com.example.myshoppingapp.model.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class UserOutputDTO implements Serializable {
    private long id;
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
    private Picture picture;

    private List<Product> boughtProducts;

    private List<Recipe> favoriteRecipes;
}

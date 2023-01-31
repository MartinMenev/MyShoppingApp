package com.example.myshoppingapp.domain.users;

import com.example.myshoppingapp.domain.BaseEntity;
import com.example.myshoppingapp.domain.enums.UserRole;
import com.example.myshoppingapp.domain.pictures.Picture;
import com.example.myshoppingapp.domain.products.Product;
import com.example.myshoppingapp.domain.recipes.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne
    private Picture picture;

    @OneToMany (mappedBy = "buyer",cascade = CascadeType.REMOVE)
    private List<Product> boughtProducts;

    @ManyToMany (mappedBy = "fan", cascade = CascadeType.REMOVE)
    private List<Recipe> favoriteRecipes;

    public User() {
        this.boughtProducts = new ArrayList<>();
        this.favoriteRecipes = new ArrayList<>();
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public User setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }
}

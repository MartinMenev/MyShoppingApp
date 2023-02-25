package com.example.myshoppingapp.domain.products;

import com.example.myshoppingapp.domain.BaseEntity;
import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn
<<<<<<< HEAD
    private UserEntity userEntity;
=======
    private User user;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    @Column
    private long position;

    @ManyToMany (cascade = CascadeType.DETACH)
    private List<Recipe> recipeList;
    @Column
    private LocalDate boughtOn;

    @ManyToOne
<<<<<<< HEAD
    private UserEntity buyer;
=======
    private User buyer;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    public Product() {
        this.recipeList = new ArrayList<>();
    }

    public Product(String name) {
        this();
        this.name = name;
    }

<<<<<<< HEAD
    public Product setBuyer(UserEntity buyer) {
=======
    public Product setBuyer(User buyer) {
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        this.buyer = buyer;
        return this;
    }

    public Product setBoughtOn(LocalDate boughtOn) {
        this.boughtOn = boughtOn;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

<<<<<<< HEAD
    public Product setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
=======
    public Product setUser(User user) {
        this.user = user;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        return this;
    }

    public Product setPosition(long position) {
        this.position = position;
        return this;
    }


}

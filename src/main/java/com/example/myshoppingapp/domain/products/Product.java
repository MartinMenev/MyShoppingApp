package com.example.myshoppingapp.domain.products;

import com.example.myshoppingapp.domain.BaseEntity;
import com.example.myshoppingapp.domain.recipes.Recipe;
import com.example.myshoppingapp.domain.users.User;
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
    private User user;

    @Column
    private long position;

    @ManyToMany (cascade = CascadeType.DETACH)
    private List<Recipe> recipeList;
    @Column
    private LocalDate boughtOn;

    @ManyToOne
    private User buyer;

    public Product() {
        this.recipeList = new ArrayList<>();
    }

    public Product(String name) {
        this();
        this.name = name;
    }

    public Product setBuyer(User buyer) {
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

    public Product setUser(User user) {
        this.user = user;
        return this;
    }

    public Product setPosition(long position) {
        this.position = position;
        return this;
    }


}

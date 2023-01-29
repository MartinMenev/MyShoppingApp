package com.example.myshoppingapp.model.products;

import com.example.myshoppingapp.model.BaseEntity;
import com.example.myshoppingapp.model.recipes.Recipe;
import com.example.myshoppingapp.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;

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

    @ManyToOne
    private Recipe recipe;
    @Column
    private LocalDate boughtOn;

    @ManyToOne
    private User buyer;

    public Product() {
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

    public Product setRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }
}

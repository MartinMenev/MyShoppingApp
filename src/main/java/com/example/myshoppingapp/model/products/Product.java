package com.example.myshoppingapp.model.products;

import com.example.myshoppingapp.model.BaseEntity;
import com.example.myshoppingapp.model.recipes.Recipe;
import com.example.myshoppingapp.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    public Product() {
    }

    public Product(String name) {
        this();
        this.name = name;
    }
}

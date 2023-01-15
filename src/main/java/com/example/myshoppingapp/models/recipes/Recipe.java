package com.example.myshoppingapp.models.recipes;

import com.example.myshoppingapp.models.comments.Comment;
import com.example.myshoppingapp.models.enums.Category;
import com.example.myshoppingapp.models.pictures.Picture;
import com.example.myshoppingapp.models.products.Product;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
   private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate dateAdded;

   @Column
    private long rating;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
   @JoinColumn
   private User author;

   @OneToOne
   @JoinColumn
   private Picture picture;

   @OneToMany(targetEntity = Comment.class,mappedBy = "recipe")
   private List<Comment> commentList;

  @OneToMany(targetEntity = Product.class, mappedBy = "recipe")
   private List<Product> productList;

  public Recipe() {
      this.dateAdded = LocalDate.now();
      this.commentList = new ArrayList<>();
      this.productList = new ArrayList<>();
  }

}

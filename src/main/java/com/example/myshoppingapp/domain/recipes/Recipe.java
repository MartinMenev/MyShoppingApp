package com.example.myshoppingapp.domain.recipes;

import com.example.myshoppingapp.domain.BaseEntity;
import com.example.myshoppingapp.domain.comments.Comment;
import com.example.myshoppingapp.domain.enums.Category;
import com.example.myshoppingapp.domain.pictures.Picture;
import com.example.myshoppingapp.domain.products.Product;
import com.example.myshoppingapp.domain.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private LocalDate dateAdded;

   @Column(scale = 1, precision = 2)
    private double rating;

   @ElementCollection
   private List<Double> ratingList;

   @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
   @JoinColumn
   private User author;

   @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
   @JoinColumn
   private List<Picture> pictureList;

   @Column(columnDefinition = "TEXT")
   private String imageUrl;

   @OneToMany(targetEntity = Comment.class, mappedBy= "recipe",
           cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
   @LazyCollection(LazyCollectionOption.FALSE)
   private List<Comment> commentList;

  @OneToMany(targetEntity = Product.class, mappedBy = "recipe",
          cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
   private List<Product> productList;

  @ManyToMany
  private List<User> fan;

  @Column
  private long position;

  public Recipe() {
      this.dateAdded = LocalDate.now();
      this.commentList = new ArrayList<>();
      this.productList = new ArrayList<>();
      this.ratingList = new ArrayList<>();
      this.pictureList = new ArrayList<>();
  }

  public boolean hasImageUrl(){
      return this.imageUrl != null;
  }

  public void addRating(double currentRating) {
    this.ratingList.add(currentRating);
    this.rating = ratingList
            .stream()
            .mapToDouble(Double::doubleValue)
            .average().orElse(0);
//      double sum = 0;
//      for (Double rating : ratingList) {
//          sum += rating;
//          this.rating = sum / ratingList.size();
//      }

  }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public Recipe setUrl(String url) {
        this.url = url;
        return this;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public Recipe setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Recipe setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public Recipe setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Recipe addPicture(Picture picture) {
        this.pictureList.add(picture);
        return this;
    }

    public Recipe setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Recipe setPosition(long position) {
        this.position = position;
        return this;
    }
}

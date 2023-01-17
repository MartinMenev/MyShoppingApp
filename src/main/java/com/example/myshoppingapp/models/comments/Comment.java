package com.example.myshoppingapp.models.comments;

import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    private Long id;
    @Column
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private LocalDateTime dateAdded;

    @Column
    private long rating;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn
    private User author;

    @ManyToOne
    private Recipe recipe;

    public Comment() {
        this.dateAdded = LocalDateTime.now();
    }
}

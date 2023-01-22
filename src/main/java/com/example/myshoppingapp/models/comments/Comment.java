package com.example.myshoppingapp.models.comments;

import com.example.myshoppingapp.models.recipes.Recipe;
import com.example.myshoppingapp.models.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;
    @Column
    private LocalDateTime dateAdded;

    @Column
    private long rating;

   @ManyToOne
    private User author;

    @ManyToOne
    private Recipe recipe;

    public Comment() {
        this.dateAdded = LocalDateTime.now();
    }

    public String getAuthorPicUrl() {

        String url = this.getAuthor().getPicture().getUrl();
        if (url == null) {
            return "/images/default-user-pic.jpg";
        }

        return "/images/"+url;
    }
}

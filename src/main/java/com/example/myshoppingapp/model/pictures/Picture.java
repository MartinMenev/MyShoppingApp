package com.example.myshoppingapp.model.pictures;

import com.example.myshoppingapp.model.BaseEntity;
import com.example.myshoppingapp.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(nullable = false)
    private String url;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn()
    private User author;


    public Picture setUrl(String url) {
        this.url = url;
        return this;
    }

    public Picture setAuthor(User author) {
        this.author = author;
        return this;
    }
}

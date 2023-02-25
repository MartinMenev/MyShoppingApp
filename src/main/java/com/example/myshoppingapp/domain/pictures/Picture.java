package com.example.myshoppingapp.domain.pictures;

import com.example.myshoppingapp.domain.BaseEntity;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String pictureUrl;
    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn()
<<<<<<< HEAD
    private UserEntity author;
=======
    private User author;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7


    public Picture setPictureUrl(String url) {
        this.pictureUrl = url;
        return this;
    }

<<<<<<< HEAD
    public Picture setAuthor(UserEntity author) {
=======
    public Picture setAuthor(User author) {
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
        this.author = author;
        return this;
    }
}

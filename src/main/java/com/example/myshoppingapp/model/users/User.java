package com.example.myshoppingapp.model.users;

import com.example.myshoppingapp.model.BaseEntity;
import com.example.myshoppingapp.model.enums.UserRole;
import com.example.myshoppingapp.model.pictures.Picture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne
    private Picture picture;
}

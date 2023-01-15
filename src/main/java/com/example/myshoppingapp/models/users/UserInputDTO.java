package com.example.myshoppingapp.models.users;

import com.example.myshoppingapp.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDTO implements Serializable {

        private long id;
        private String username;
        private String password;
        private String email;
        private UserRole userRole;

}

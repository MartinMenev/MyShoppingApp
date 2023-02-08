package com.example.myshoppingapp.domain.users;

import com.example.myshoppingapp.domain.validation.FieldMatch;
import com.example.myshoppingapp.domain.validation.UniqueUserEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class RegisterUserDTO implements Serializable {
@NotNull
@Size(min = 5, max = 20)
    private String username;
    @NotNull(message = "User email should be provided.")
    @Email (message = "User email should be valid.")
//    @UniqueUserEmail(message = "User email should be unique.")
    private String email;
    @NotNull (message = "password should be provided.")
    @Size(min = 5, max = 20)
    private String password;

    @NotNull (message = "you should confirm your password")
    @Size(min = 5, max = 20)
    private String confirmPassword;


}

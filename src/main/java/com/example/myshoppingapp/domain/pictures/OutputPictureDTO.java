package com.example.myshoppingapp.domain.pictures;

<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutputPictureDTO {
    private Long id;
    private String pictureUrl;
<<<<<<< HEAD
    private UserEntity author;
=======
    private User author;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

}

package com.example.myshoppingapp.model.pictures;

import com.example.myshoppingapp.model.users.User;
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
    private String url;
    private User author;

}

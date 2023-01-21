package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.pictures.OutputPictureDTO;
import com.example.myshoppingapp.models.pictures.Picture;
import com.example.myshoppingapp.models.users.User;
import com.example.myshoppingapp.repositories.PictureRepository;
import com.example.myshoppingapp.repositories.UserRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public PictureService(PictureRepository pictureRepository, UserRepository userRepository, UserService userService) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void addPicture(String pictureUrl) {
        Picture picture = this.pictureRepository.findByAuthorId(userService.getLoggedUserId()).orElse(null);
        if (picture != null) {
            picture.setUrl(pictureUrl);
            this.pictureRepository.saveAndFlush(picture);

        } else {
            picture = new Picture();
            picture.setAuthor(this.userService.findByUsername(userService.getLoggedInUser()));
            picture.setUrl(pictureUrl);
            this.pictureRepository.saveAndFlush(picture);
        }
        User user = this.userService.findByUsername(this.userService.getLoggedInUser());
        user.setPicture(picture);
        this.userRepository.saveAndFlush(user);

    }

    public String getPictureUrlByLoggedUser(){
       Optional<Picture> picture = this.pictureRepository.findByAuthorId(this.userService.getLoggedUserId());
        String picUrl = picture.map(Picture::getUrl).orElse(null);
        if (picUrl != null) {
            picUrl = "/images/"+picUrl;
        }
        return picUrl;
    }

    public String getPictureUrlByUserId(Long userId){
        Optional<Picture> picture = this.pictureRepository.findByAuthorId(userId);
        String picUrl = picture.map(Picture::getUrl).orElse(null);
        if (picUrl != null) {
            picUrl = "/images/"+picUrl;
        }
        return picUrl;
    }

    public List<OutputPictureDTO> getAllPictures() {
        return this.pictureRepository.findAll()
                .stream()
                .map(picture -> modelMapper.map(picture, OutputPictureDTO.class))
                .toList();
    }
}

package com.example.myshoppingapp.service;

import com.example.myshoppingapp.models.pictures.OutputPictureDTO;
import com.example.myshoppingapp.models.pictures.Picture;
import com.example.myshoppingapp.models.products.OutputProductDTO;
import com.example.myshoppingapp.repositories.PictureRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final UserService userService;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public PictureService(PictureRepository pictureRepository, UserService userService) {
        this.pictureRepository = pictureRepository;
        this.userService = userService;
    }


    public void addPicture(String pictureUrl) {
        Picture picture = this.pictureRepository.findByAuthorId(userService.getLoggedUserId()).orElse(null);
        if (picture != null) {
            picture.setUrl(pictureUrl);
            this.pictureRepository.save(picture);
        } else {
            picture = new Picture();
            picture.setAuthor(this.userService.findByUsername(userService.getLoggedInUser()));
            picture.setUrl(pictureUrl);
            this.pictureRepository.save(picture);
        }


    }

    public String getPictureUrlByUserId(){
       Optional<Picture> picture = this.pictureRepository.findByAuthorId(this.userService.getLoggedUserId());
        String picUrl = picture.map(Picture::getUrl).orElse(null);
        if (picUrl != null) {
            picUrl = "/images/"+picUrl;
        }
        return picUrl;
    }
}

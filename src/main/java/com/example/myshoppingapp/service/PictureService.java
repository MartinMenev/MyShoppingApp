package com.example.myshoppingapp.service;

import com.example.myshoppingapp.repositories.PictureRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


}

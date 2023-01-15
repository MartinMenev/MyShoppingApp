package com.example.myshoppingapp.service;

import com.example.myshoppingapp.repositories.CommentRepository;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }




}

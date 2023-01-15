package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.pictures.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {


}

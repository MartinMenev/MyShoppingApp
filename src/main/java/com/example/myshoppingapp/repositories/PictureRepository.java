package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.pictures.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findByAuthorId(Long id);

    List<Picture> findAll();


}

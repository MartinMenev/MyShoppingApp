package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findAllByRecipeIdOrderByIdDesc(Long recipeId);
}

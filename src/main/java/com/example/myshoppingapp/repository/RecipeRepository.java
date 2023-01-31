package com.example.myshoppingapp.repository;

import com.example.myshoppingapp.domain.recipes.Recipe;
import com.example.myshoppingapp.domain.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>,
        JpaSpecificationExecutor<Recipe> {


    Optional<Recipe> getRecipeById(Long id);


    List<Recipe> findAllByAuthorOrderByIdDesc(User author);
}

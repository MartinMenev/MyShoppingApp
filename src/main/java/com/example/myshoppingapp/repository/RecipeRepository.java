package com.example.myshoppingapp.repository;

import com.example.myshoppingapp.domain.enums.Category;
import com.example.myshoppingapp.domain.recipes.Recipe;
<<<<<<< HEAD
import com.example.myshoppingapp.domain.users.UserEntity;
=======
import com.example.myshoppingapp.domain.users.User;
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>,
        JpaSpecificationExecutor<Recipe> {


    Optional<Recipe> getRecipeById(Long id);


<<<<<<< HEAD
    List<Recipe> findAllByAuthorOrderByIdDesc(UserEntity author);
=======
    List<Recipe> findAllByAuthorOrderByIdDesc(User author);
>>>>>>> 51bc36dd907306a4a92338269502a5a80dcf1bb7

    Optional<List<Recipe>> findAllByCategory(Category category);

    @Query(value = "select * from recipes as r where locate(:text, r.name) > 0 "+
        "or locate(:text, r.description) > 0", nativeQuery = true)
    Optional<List<Recipe>> findAllContainingSearchText(String text);
}

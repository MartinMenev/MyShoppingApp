package com.example.myshoppingapp.repositories;

import com.example.myshoppingapp.models.recipes.OutputRecipeDTO;
import com.example.myshoppingapp.models.recipes.Recipe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RecipeSpecification implements Specification<Recipe> {

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        return p;
    }
}

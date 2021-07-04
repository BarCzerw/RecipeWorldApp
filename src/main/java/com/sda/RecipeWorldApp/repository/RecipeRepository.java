package com.sda.RecipeWorldApp.repository;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByOwner(Account owner);
}

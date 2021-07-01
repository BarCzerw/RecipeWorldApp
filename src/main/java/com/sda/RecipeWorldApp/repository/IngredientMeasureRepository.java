package com.sda.RecipeWorldApp.repository;

import com.sda.RecipeWorldApp.model.recipeModel.IngredientMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientMeasureRepository extends JpaRepository<IngredientMeasure,Long> {
}

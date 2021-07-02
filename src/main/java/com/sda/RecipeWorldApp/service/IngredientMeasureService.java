package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.model.recipeModel.IngredientMeasure;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.repository.IngredientMeasureRepository;
import com.sda.RecipeWorldApp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientMeasureService {
    private final IngredientMeasureRepository ingredientMeasureRepository;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    public void addMeasure(IngredientMeasure measure, long recipeId, long ingredientId) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        Optional<Ingredient> ingredientOptional = ingredientService.getIngredientById(ingredientId);
        if (recipeOptional.isPresent() && ingredientOptional.isPresent()) {
            measure.setRecipe(recipeOptional.get());
            measure.setIngredient(ingredientOptional.get());
            ingredientMeasureRepository.save(measure);
        }
    }
}

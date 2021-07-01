package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    RecipeRepository recipeRepository;
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public void deleteRecipe(long recipeId) {
        Optional<Recipe> ingredientOptional = recipeRepository.findById(recipeId);
        ingredientOptional.ifPresent(recipeRepository::delete);
    }
}

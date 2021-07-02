package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public void addIngredient(Ingredient ingredient) {
        if (ingredientRepository.findByName(ingredient.getName()).isEmpty()) {
            ingredientRepository.save(ingredient);
        }
    }

    public void deleteIngredient(long ingredientId) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
        ingredientOptional.ifPresent(ingredientRepository::delete);
    }

    public Optional<Ingredient> getIngredientById(long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

}

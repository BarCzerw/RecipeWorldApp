package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.repository.AccountRepository;
import com.sda.RecipeWorldApp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final AccountRepository accountRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public void addRecipe(Recipe recipe, Long ownerId) {
        Optional<Account> accountOptional = accountRepository.findById(ownerId);
        if (accountOptional.isPresent()) {
            recipe.setOwner(accountOptional.get());
            recipeRepository.save(recipe);
        }
    }

    public void deleteRecipe(long recipeId) {
        Optional<Recipe> ingredientOptional = recipeRepository.findById(recipeId);
        ingredientOptional.ifPresent(recipeRepository::delete);
    }

    public Optional<Recipe> getRecipeById(long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    public List<Recipe> getRecipesByOwner(Account owner) {
        return recipeRepository.findAllByOwner(owner);
    }
}

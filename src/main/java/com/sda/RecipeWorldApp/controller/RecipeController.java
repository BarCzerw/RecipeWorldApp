package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipe-list";
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model) {
        model.addAttribute("newRecipe", new Recipe());
        return "recipe-add-form";
    }

    @PostMapping("/add")
    public String addIngredient(Recipe recipe) {
        recipeService.addRecipe(recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/recipe";
    }
}

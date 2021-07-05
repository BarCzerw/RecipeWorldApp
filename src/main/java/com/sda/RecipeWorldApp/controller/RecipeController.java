package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.recipeModel.IngredientMeasure;
import com.sda.RecipeWorldApp.model.recipeModel.MeasureUnit;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.service.AccountService;
import com.sda.RecipeWorldApp.service.IngredientService;
import com.sda.RecipeWorldApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final AccountService accountService;
    private final IngredientService ingredientService;

    @GetMapping
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipe-list";
    }

    @GetMapping("/{id}")
    public String getRecipe(Model model, @PathVariable(name = "id") long recipeId) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            model.addAttribute("ingredientList", ingredientService.getAllIngredients());
            return "recipe-details";
        }
        return "redirect:/recipe";
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model, Authentication authentication) {
            Account account = (Account) authentication.getPrincipal();
            model.addAttribute("accountId", account.getId());
            model.addAttribute("newRecipe", new Recipe());
            return "recipe-add-form";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe, Long ownerId) {
        recipeService.addRecipe(recipe, ownerId);
        return "redirect:/myaccount/recipes";
    }

    @GetMapping("/edit/{id}")
    public String editRecipe(Model model, Authentication authentication, @PathVariable long id) {
        Account account = (Account) authentication.getPrincipal();
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            if (recipe.getOwner().getId().equals(account.getId())) {
                model.addAttribute("newRecipe", recipeOptional.get());
                model.addAttribute("accountId", account.getId());
                return "recipe-add-form";
            }
        }
        return "redirect:/myaccount/recipes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/myaccount/recipes";
    }
}

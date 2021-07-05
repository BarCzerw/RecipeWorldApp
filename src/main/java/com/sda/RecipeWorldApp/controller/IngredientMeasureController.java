package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.model.recipeModel.IngredientMeasure;
import com.sda.RecipeWorldApp.model.recipeModel.MeasureUnit;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.service.IngredientMeasureService;
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
@RequestMapping("/measure")
@RequiredArgsConstructor
public class IngredientMeasureController {

    private final IngredientMeasureService ingredientMeasureService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @GetMapping("/add/{id}")
    public String addMeasureForm(Model model, Authentication authentication, @PathVariable(name = "id") long recipeId) {
        Account account = (Account) authentication.getPrincipal();
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            if (recipe.getOwner().getId().equals(account.getId())) {
                model.addAttribute("newMeasure", new IngredientMeasure());
                model.addAttribute("recipe", recipe);
                model.addAttribute("ingredientList", ingredientService.getAllIngredients());
                model.addAttribute("unitList", MeasureUnit.values());
                return "ingredientmeasure-add-form";
            }
        }
        return "redirect:/myaccount/recipes";
    }

    @PostMapping("/add")
    public String addMeasure(IngredientMeasure measure, long recipeId, long ingredientId) {
        ingredientMeasureService.addMeasure(measure, recipeId, ingredientId);
        return "redirect:/measure/add/" + recipeId;
    }

}

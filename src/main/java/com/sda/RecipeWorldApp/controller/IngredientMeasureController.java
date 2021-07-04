package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.model.recipeModel.IngredientMeasure;
import com.sda.RecipeWorldApp.service.IngredientMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/measure")
@RequiredArgsConstructor
public class IngredientMeasureController {

    private final IngredientMeasureService ingredientMeasureService;

    @PostMapping("/add")
    public String addMeasure(IngredientMeasure measure, long recipeId, Ingredient ingredient) {
        ingredientMeasureService.addMeasure(measure, recipeId, ingredient.getId());
        return "redirect:/recipe/" + recipeId;
    }

}

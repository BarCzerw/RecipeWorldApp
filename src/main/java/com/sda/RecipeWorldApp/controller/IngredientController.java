package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.recipeModel.Ingredient;
import com.sda.RecipeWorldApp.model.recipeModel.IngredientType;
import com.sda.RecipeWorldApp.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public String getAllIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "ingredients-list";
    }

    @GetMapping("/add")
    public String addIngredientForm(Model model) {
        model.addAttribute("newIngredient", new Ingredient());
        model.addAttribute("typeList", IngredientType.values());
        return "ingredient-add-form";
    }

    @PostMapping("/add")
    public String addIngredient(Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
        return "redirect:/ingredient";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable(name = "id") long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return "redirect:/ingredient";
    }
}

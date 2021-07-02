package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.recipeModel.Recipe;
import com.sda.RecipeWorldApp.service.AccountService;
import com.sda.RecipeWorldApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final AccountService accountService;

    @GetMapping
    public String getAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipe-list";
    }

    @GetMapping("{/id}")
    public String getRecipe(Model model, @PathVariable(name = "id") long recipeId) {
        Optional<Recipe> recipeOptional = recipeService.getRecipeById(recipeId);
        if (recipeOptional.isPresent()) {
            model.addAttribute("recipe", recipeOptional.get());
            return "recipe-detail";
        }
        return "redirect:/recipe";
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model) {
        model.addAttribute("newRecipe", new Recipe());
        return "recipe-add-form";
    }

    @PostMapping("/add")
    public String addRecipe(Recipe recipe, Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            if (usernamePasswordAuthenticationToken.getPrincipal() instanceof Account) {
                Account account = (Account) usernamePasswordAuthenticationToken.getPrincipal();
                recipeService.addRecipe(recipe, account);
            }
        }
        return "redirect:/recipe";
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable(name = "id") long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return "redirect:/recipe";
    }
}

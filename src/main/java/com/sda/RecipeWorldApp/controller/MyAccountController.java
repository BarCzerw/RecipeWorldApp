package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.service.AccountService;
import com.sda.RecipeWorldApp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myaccount")
public class MyAccountController {
    private final AccountService accountService;
    private final RecipeService recipeService;

    @GetMapping
    public String getMyAccountPage(Model model) {
        return "my-account";
    }

    @GetMapping("/recipes")
    public String geyMyRecipes(Model model, Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();
        model.addAttribute("myRecipes", recipeService.getRecipesByOwner(account));
        return "my-recipes";
    }
}

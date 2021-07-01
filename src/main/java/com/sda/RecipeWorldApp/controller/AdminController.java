package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AccountService accountService;

    @GetMapping
    public String getAdminIndexPage() {
        return "admin-index";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", accountService.findAllUsers());
        return "admin-user-list";
    }
}

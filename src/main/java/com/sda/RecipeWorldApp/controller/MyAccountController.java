package com.sda.RecipeWorldApp.controller;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myaccount")
public class MyAccountController {
    private final AccountService accountService;

    @GetMapping("/name")
    @ResponseBody
    public String getMyAccount(Authentication authentication) {
        Account userAccount = (Account) authentication.getPrincipal();
        return userAccount.getId() + " - " + userAccount.getUsername() + " - " + userAccount.getRoles();
    }
}

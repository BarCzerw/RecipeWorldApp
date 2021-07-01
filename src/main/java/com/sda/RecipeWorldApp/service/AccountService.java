package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> findAllUsers() {
        return accountRepository.findAll();
    }
}

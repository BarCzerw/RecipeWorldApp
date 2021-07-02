package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> findAllUsers() {
        return accountRepository.findAll();
    }

    public Optional<Account> findUserById(long ownerId) {
        return accountRepository.findById(ownerId);
    }
}

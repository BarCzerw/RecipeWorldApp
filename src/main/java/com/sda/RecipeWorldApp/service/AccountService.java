package com.sda.RecipeWorldApp.service;

import com.sda.RecipeWorldApp.model.Account;
import com.sda.RecipeWorldApp.model.RegisterRequest;
import com.sda.RecipeWorldApp.repository.AccountRepository;
import com.sda.RecipeWorldApp.repository.AccountRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Account> findAllUsers() {
        return accountRepository.findAll();
    }

    public Optional<Account> findUserById(long ownerId) {
        return accountRepository.findById(ownerId);
    }

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public void deleteUser(long userId) {
        accountRepository.deleteById(userId);
    }

    public void registerUser(RegisterRequest registerRequest) {
        if (validateUsername(registerRequest.getUsername())
                && validatePassword(registerRequest.getPassword(), registerRequest.getConfirmPassword())) {
            Account newAccount = Account.builder()
                    .username(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();

            accountRepository.save(newAccount);
        }
    }

    private boolean validatePassword(String password, String confirmPassword) {
        return !password.isEmpty() && password.equals(confirmPassword);
    }

    private boolean validateUsername(String username) {
        return accountRepository.findByUsername(username).isEmpty() && !username.isEmpty();
    }

}

package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


public interface AccountService {
    public void createAccount(Account account);
    public void updateAccount(Long id, Account account);

    public void deleteAccount();

    public void addBooking();

    public void deleteBooking();

    public List<Account> getAccounts();
}

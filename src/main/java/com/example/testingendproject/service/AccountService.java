package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


public interface AccountService {
    public boolean createAccount(Account account);
    public boolean updateAccount(Account account);

    public boolean deleteAccount(String username);

    public void addBooking(String id, String username);

    public void deleteBooking(String id, String username);
    public Account findByUsername(String username);

    public List<Account> getAccounts();
}

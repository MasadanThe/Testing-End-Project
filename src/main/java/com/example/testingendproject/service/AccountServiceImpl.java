package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl {
    @Autowired
    AccountRepository accountRepository;
    public void createAccount(Account account){

    }

    public void updateAccount(){

    }

    public void deleteAccount(){

    }

    public void addBooking(){

    }

    public void deleteBooking(){

    }

    public List<Account> getAccounts(){
        return null;
    }
}

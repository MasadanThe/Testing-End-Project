package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    AccountService accountService;
    @Autowired
    JwtService jwtService;
    @Override
    public String authenticate(String username)
    {
        Account account = accountService.findByUsername(username);
        if(account.getUsername().equals(username)){
            return jwtService.getJwtToken(username);
        }
        return "";
    }
}

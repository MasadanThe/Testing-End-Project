package com.example.testingendproject.service;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public boolean authenticate(String username)
    {
        return false;
    }
}

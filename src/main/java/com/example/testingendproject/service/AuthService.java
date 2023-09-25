package com.example.testingendproject.service;


import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public boolean authenticate(String username);
}

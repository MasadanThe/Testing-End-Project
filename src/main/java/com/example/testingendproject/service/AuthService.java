package com.example.testingendproject.service;


import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean authenticate(String username);
}

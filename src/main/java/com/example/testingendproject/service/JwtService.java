package com.example.testingendproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

@Service
public class JwtService {
    @Autowired(required = false)
    AuthService authService;

    public String getJwtToken(String username){
        return null;
    }
}

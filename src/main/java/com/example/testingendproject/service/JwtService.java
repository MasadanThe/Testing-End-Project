package com.example.testingendproject.service;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

@Service
public class JwtService {

    public String getJwtToken(String username){
        String token = JWT.create()
                .withClaim("username", username)
                .sign(Algorithm.HMAC256("totallySecret"));
        return token;
    }

    public boolean verifyJwtToken(String jwtToken){
        return false;
    }
}

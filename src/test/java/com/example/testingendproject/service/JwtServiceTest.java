package com.example.testingendproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtServiceTest {
    @Autowired
    JwtService jwtService;

    @Test
    void verifyGetJwtToken() {

        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJpc3MiOiJ3ZUFyZUhlcmUifQ.VfCUCsAzsbTrmlOy7gVsusTkj031k6W-HQ788OJp8AE",jwtService.getJwtToken("test"));
    }

}
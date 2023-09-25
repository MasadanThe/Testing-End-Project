package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthService authService = new AuthServiceImpl();

    @Mock
    AccountService accountService;
    @Test
    void verifyAuthentication() {
        assertEquals(false, "MrCool");
    }
}
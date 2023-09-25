package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
import com.example.testingendproject.repository.RouteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthService authService = new AuthServiceImpl();

    @Mock
    AccountService accountService;
    @Mock
    JwtService jwtService;
    @Test
    void verifyAuthentication() {
        var account1 = Account.builder()
                .username("Troll")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();
        when(accountService.findByUsername("Troll")).thenReturn(account1);
        authService.authenticate("Troll");
        verify(accountService, times(2)).findByUsername(any());
    }
}
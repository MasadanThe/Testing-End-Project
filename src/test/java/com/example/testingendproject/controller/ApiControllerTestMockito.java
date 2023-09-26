package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.FastBooking;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ApiControllerTestMockito {

    @InjectMocks
    private AccountService accountService  = new AccountServiceImpl();

    @Mock
    AccountRepository accountRepository;

    @Mock PaymentExternal paymentExternal;

    @Mock
    MockMvc mockMvc;



    @Test
    void addBooking() throws Exception {
        FastBooking fastBooking = new FastBooking("34", "Test24");
        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/add_booking").
                        content(asJsonString(fastBooking))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

        Account account = accountService.findByUsername("Test24");
        assertEquals(5, account.getActiveBookings());
    }
}
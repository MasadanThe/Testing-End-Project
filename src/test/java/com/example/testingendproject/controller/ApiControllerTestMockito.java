package com.example.testingendproject.controller;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.FastBooking;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
import com.example.testingendproject.repository.RouteRepository;
import com.example.testingendproject.service.AccountService;
import com.example.testingendproject.service.AccountServiceImpl;
import com.example.testingendproject.service.PaymentExternal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ApiControllerTestMockito {

    @InjectMocks
    @Autowired
    AccountService accountService  = new AccountServiceImpl();


    @Mock
    PaymentExternal paymentExternal;

    @Autowired
    MockMvc mockMvc;
   @BeforeAll
    static void addAccount(@Autowired AccountService accountService) {
        boolean secondAccount = accountService.createAccount(new Account("Test24", "435534534", "432243342", "2", "5,3", "User"));
        assertEquals(true, secondAccount);
    }



    @Test
    void addBooking() throws Exception {
        when(paymentExternal.checkPayment(any())).thenReturn("6457");
        FastBooking fastBooking = new FastBooking("5", "Test24");
        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/add_booking").
                        content(asJsonString(fastBooking))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());

        Account account = accountService.findByUsername("Test24");
        assertEquals("35252", account.getActiveBookings());
    }

    /*
    OBS DENNA METOD NAMNGED 'asJsonString' ÄR DIREKT KOPIERAD FRÅN
    https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
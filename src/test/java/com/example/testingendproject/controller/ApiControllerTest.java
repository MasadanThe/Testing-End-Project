package com.example.testingendproject.controller;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.repository.AccountRepository;
import com.example.testingendproject.service.AccountService;
import com.example.testingendproject.service.AccountServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.*;
import java.util.List;
@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @BeforeAll
    static void addAccount(@Autowired AccountService accountService) {
        accountService.createAccount(new Account("Test", "435534534", "432243342", "2", "3", "User"));
    }
    @Test
    void testEndToEndCreateAccount() throws Exception {
        mockMvc.perform(post("/create_account").
                content(asJsonString(new Account("Test", "435534534", "432243342", "2", "3", "User"))))
                .andExpect(status().isOk());
    }

    @Test
    void verifyAccountCount() throws Exception {
        mockMvc.perform(get("/get_accounts"))
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType("application/json"));
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


    @Test
    void updateAccount() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void addBooking() {
    }

    @Test
    void deleteBooking() {
    }

    @Test
    void updateSale() {
    }

    @Test
    void getRoutes() {
    }

    @Test
    void createBookingSupplier() {
    }
}
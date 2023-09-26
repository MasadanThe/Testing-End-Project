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
import org.springframework.test.web.servlet.MvcResult;

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
        boolean firstAccount = accountService.createAccount(new Account("Test56", "435534534", "432243342", "2", "3", "User"));
        boolean secondAccount = accountService.createAccount(new Account("Test24", "435534534", "432243342", "2", "3", "User"));
        assertEquals(true, firstAccount);
        assertEquals(true, secondAccount);
    }

    void testEndToEndCreateAccount() throws Exception {
        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/create_account").
                content(asJsonString(new Account("Test", "86778876", "7688678", "2", "3", "User")))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        assertEquals(3, accountService.getAccounts().size());

        //Tries to add the same user and expects it to not have been added
        mockMvc.perform(post("/create_account").
                        content(asJsonString(new Account("Test", "86778876", "7688678", "2", "3", "User")))
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
        assertEquals(3, accountService.getAccounts().size());
    }

   /* @Test
    void verifyAccountCount() throws Exception {
        MvcResult result = mockMvc.perform(get("/get_accounts"))
                .andExpect(content()
                .contentType("application/json"))
                .andExpect(status()
                        .isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("hej", content);

    }*/


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


    void testEndToEndDeleteAccount() throws Exception {
        //Deletes an account
        mockMvc.perform(post("/delete_account").
                        content(asJsonString(new Account("Test56", "86778876", "7688678", "2", "3", "User")))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        assertEquals(2, accountService.getAccounts().size());
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

    @Test
    void runAll(){
        try {
            testEndToEndCreateAccount();
            testEndToEndDeleteAccount();
        }
        catch (Exception exception)
        {

        }
    }
}
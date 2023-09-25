package com.example.testingendproject.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testEndToEndCreateAccount() {
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
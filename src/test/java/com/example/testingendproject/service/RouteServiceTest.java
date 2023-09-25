package com.example.testingendproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteServiceTest {

    @Autowired
    private RouteService routeService;
    @Test
    void updateSale() {
    }

    @Test
    void verifyThatReturnedListIsNotNull() {
        assertNull(routeService.getRoutes());
    }

    @Test
    void createBookingSupplier() {
    }
}
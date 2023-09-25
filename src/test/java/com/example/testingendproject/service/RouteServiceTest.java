package com.example.testingendproject.service;

import com.example.testingendproject.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private RouteService routeService;

    @Mock
    RouteRepository routeRepository;


    @Test
    void updateSale() {
    }

    @Test
    void verifyThatReturnedListIsNotNull() {
        assertNotNull(routeService.getRoutes());
    }

    @Test
    void createBookingSupplier() {
    }
}
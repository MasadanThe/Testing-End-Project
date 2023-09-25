package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
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
class RouteServiceTest {

    @InjectMocks
    private RouteService routeService = new RouteServiceImpl();

    @Mock
    RouteRepository routeRepository;


    @Test
    void updateSale() {
    }

    @Test
    void verifyThatReturnedListIsNotNull() {
        var route = Route.builder()
                .destinationEnd("Stockholm")
                .destinationStart("Karlstad")
                .price(500)
                .salePrice(450)
                .estimatedArrival("12:00")
                .estimatedDeparture("10:00")
                .contractor("MKD")
                .transportType("Train").build();

    }

    @Test
    void createBookingSupplier() {
    }
}
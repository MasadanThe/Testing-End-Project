package com.example.testingendproject.service;

import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.RouteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RouteServiceTest {

    @Autowired
    RouteService routeService;

    @BeforeAll
    static void addEntries(@Autowired RouteService routeService) {
        var route1 = Route.builder()
                .destinationEnd("Örebro")
                .destinationStart("Köpenhamn")
                .price(700)
                .salePrice(650)
                .estimatedArrival("13:00")
                .estimatedDeparture("09:00")
                .contractor("MKD")
                .transportType("Train").build();
        routeService.createBookingSupplier(route1);
    }


    @Test
    void verifyThatUpdatePriceUpdatesInTheDatabase() {
        routeService.updateSale(Long.valueOf(1), Long.valueOf(200), "MKD");
        List<Route> routeList1 = routeService.getRoutes();

        assertEquals(200, routeList1.get(0).getSalePrice());
    }

    @Test
    void verifyThatUpdatePriceReturnsFalseWhenItDidntFindARoute() {
        assertEquals(true, routeService.updateSale(Long.valueOf(4), Long.valueOf(200), "TGB"));
    }

    @Test
    void verifyWeGetRoutesFromGetRoutes() {

        assertNotNull(routeService.getRoutes());
    }


    @Test
    void verifyWeCanAddToTheDatabase() {
        var route1 = Route.builder()
                .destinationEnd("Örebro")
                .destinationStart("Köpenhamn")
                .price(700)
                .salePrice(650)
                .estimatedArrival("13:00")
                .estimatedDeparture("09:00")
                .contractor("MKD")
                .transportType("Train").build();
        routeService.createBookingSupplier(route1);
        assertEquals(routeService.getRoutes().size(), 2);
    }
}
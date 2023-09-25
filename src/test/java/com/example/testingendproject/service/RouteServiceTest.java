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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @InjectMocks
    private RouteService routeService = new RouteServiceImpl();

    @Mock
    RouteRepository routeRepository;

    @Mock RouteExternal routeExternal;


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
        List<Route> routeList = new ArrayList<Route>();
        routeList.add(route);

        when(routeExternal.getRoutesFromSupplier1()).thenReturn(routeList);

        routeService.getRoutes();

        verify(routeExternal, times(1)).getRoutesFromSupplier1();
    }

    @Test
    void createBookingSupplier() {
    }
}
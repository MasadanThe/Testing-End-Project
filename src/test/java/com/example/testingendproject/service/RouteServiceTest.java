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
    void verifyThatReturnedGetRoutesFromSupplierIsUsedWhenGetRoutes() {
        var route1 = Route.builder()
                .destinationEnd("Stockholm")
                .destinationStart("Karlstad")
                .price(500)
                .salePrice(450)
                .estimatedArrival("12:00")
                .estimatedDeparture("10:00")
                .contractor("MKD")
                .transportType("Train").build();
        List<Route> routeList1 = new ArrayList<Route>();
        routeList1.add(route1);

        when(routeExternal.getRoutesFromSupplier1()).thenReturn(routeList1);

        var route2 = Route.builder()
                .destinationEnd("Karlskrona")
                .destinationStart("Örebro")
                .price(850)
                .salePrice(800)
                .estimatedArrival("17:00")
                .estimatedDeparture("11:00")
                .contractor("TGY")
                .transportType("Bus").build();
        List<Route> routeList2 = new ArrayList<Route>();
        routeList2.add(route2);

        when(routeExternal.getRoutesFromSupplier2()).thenReturn(routeList2);

        var route3 = Route.builder()
                .destinationEnd("Farstad")
                .destinationStart("Eskilstuna")
                .price(200)
                .salePrice(200)
                .estimatedArrival("00:00")
                .estimatedDeparture("16:00")
                .contractor("G95")
                .transportType("Car").build();
        List<Route> routeList3 = new ArrayList<Route>();
        routeList3.add(route3);

        when(routeExternal.getRoutesFromSupplier3()).thenReturn(routeList3);

        routeService.setRoutes();

        verify(routeExternal, times(1)).getRoutesFromSupplier1();
        verify(routeExternal, times(1)).getRoutesFromSupplier2();
        verify(routeExternal, times(1)).getRoutesFromSupplier3();
    }

    @Test
    void createBookingSupplier() {
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

        assertNotNull(routeRepository.find);
    }
}
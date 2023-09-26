package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
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

    @Autowired
    AccountService accountService;

    @BeforeAll
    static void addEntries(@Autowired RouteService routeService, @Autowired AccountService accountService) {
        var account1 = Account.builder()
                .username("MKD")
                .accountType("CONTRACTOR")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();
        accountService.createAccount(account1);

        var account2 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("")
                .paymentHistory("342432,7675,322")
                .activeBookings("").build();
        accountService.createAccount(account2);

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
        assertEquals(false, routeService.updateSale(Long.valueOf(4), Long.valueOf(200), "TGB"));
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

    @Test
    void verifyWeCantAddToTheDatabaseAsNonContractor() {
        var route1 = Route.builder()
                .destinationEnd("Örebro")
                .destinationStart("Köpenhamn")
                .price(700)
                .salePrice(650)
                .estimatedArrival("13:00")
                .estimatedDeparture("09:00")
                .contractor("Mr.Cool")
                .transportType("Train").build();
        assertEquals(false, routeService.createBookingSupplier(route1));

    }

    @Test
    void verifyWeCantAddToTheDatabaseWithNoName() {
        var route1 = Route.builder()
                .destinationEnd("Örebro")
                .destinationStart("Köpenhamn")
                .price(700)
                .salePrice(650)
                .estimatedArrival("13:00")
                .estimatedDeparture("09:00")
                .contractor("")
                .transportType("Train").build();
        assertEquals(false, routeService.createBookingSupplier(route1));

    }

    @Test
    void verifyWeCantAddNullToTheDatabase() {

        assertEquals(false, routeService.createBookingSupplier(null));
    }
}
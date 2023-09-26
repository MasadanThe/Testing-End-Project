package com.example.testingendproject.controller;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.FastBooking;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
import com.example.testingendproject.service.AccountService;
import com.example.testingendproject.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    RouteService routeService;
    @Autowired
    AccountRepository accountRepository;

    @BeforeAll
    static void addEntities(@Autowired AccountService accountService, @Autowired RouteService routeService) {
        boolean firstAccount = accountService.createAccount(new Account("Test56", "435534534", "432243342", "2", "3", "User"));
        boolean secondAccount = accountService.createAccount(new Account("Test24", "435534534", "432243342", "2", "5,3", "User"));
        assertEquals(true, firstAccount);
        assertEquals(true, secondAccount);

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



    void testEndToEndUpdateAccount() throws Exception {
        Account newAccount = new Account("Test24", "86778876", "7688678", "2", "5,3", "User");
        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/update_account").
                        content(asJsonString(newAccount))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        Account account = accountService.findByUsername("Test24");
        assertEquals(newAccount.getUsername(), account.getUsername());
        assertEquals(newAccount.getContactInformation(), account.getContactInformation());
        assertEquals(newAccount.getPaymentInformation(), account.getPaymentInformation());
        assertEquals(newAccount.getPaymentHistory(), account.getPaymentHistory());
        assertEquals(newAccount.getActiveBookings(), account.getActiveBookings());
        assertEquals(newAccount.getAccountType(), account.getAccountType());

    }


    void testEndToEndDeleteAccount() throws Exception {
        //Deletes an account
        mockMvc.perform(post("/delete_account").
                        content(asJsonString(new Account("Test56", "86778876", "7688678", "2", "3", "User")))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        assertEquals(2, accountService.getAccounts().size());
    }


    void deleteBooking() throws Exception {
        FastBooking fastBooking = new FastBooking("3", "Test24");
        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/delete_booking").
                        content(asJsonString(fastBooking))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        Account account = accountService.findByUsername("Test24");
        assertEquals("5", account.getActiveBookings());
    }

    @Test
    void updateSale() throws Exception {

        //Adds a new user and expects a user to be added
        mockMvc.perform(post("/update_sale").
                        content(asJsonString(obj))
                        .contentType("application/json"))
                .andExpect(status().isOk());

        List<Route> routeList = getRoutes();
        Route foundRoute = new Route();
        for (Route route: routeList) {
            if (route.getId() == obj.getid() && route.getContractor().equals(username)){
                route.setSalePrice(salePrice);
                foundRoute = route;
            }

        }
        assertEquals(3, routeService.));
    }

    @Test
    void getRoutes() {
    }

    @Test
    void createBookingSupplier() {
    }

    //One method that runs the tests in a special order so they don't get wrong data.
    //Like testEndToEndCreateAccount creates an account but testEndToEndDeleteAccount just deleted it
    //And the asserts gets wrong
    @Test
    void runAll(){
        try {
            testEndToEndCreateAccount();
            testEndToEndDeleteAccount();
            testEndToEndUpdateAccount();
            deleteBooking();
        }
        catch (Exception exception)
        {

        }
    }
}
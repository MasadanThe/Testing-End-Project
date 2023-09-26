package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
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
@ExtendWith(MockitoExtension.class)
class AccountServiceTestMockito {

    @InjectMocks
    @Autowired
    AccountService accountService  = new AccountServiceImpl();

    @Mock PaymentExternal paymentExternal;


    @BeforeAll
    static void addEntries(@Autowired AccountService accountService) {
        var account1 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();
        accountService.createAccount(account1);
        var account2 = Account.builder()
                .username("Mr.Kind")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("")
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();
        accountService.createAccount(account2);
    }

    @Test
    void verifyThatPaymentGoesThrough() {

        when(paymentExternal.checkPayment(any())).thenReturn("6457");


        accountService.addBooking("7","Mr.Cool");

        verify(paymentExternal, times(1)).checkPayment(any());
    }

    @Test
    void verifyThatYouCanAddABooking(){
        when(paymentExternal.checkPayment(any())).thenReturn("6457");
        accountService.addBooking("8","Mr.Cool");

        assertEquals("1,5,4,3,7,8", accountService.findByUsername("Mr.Cool").getActiveBookings());
    }

    @Test
    void verifyThatYouCantAddABookingWithoutUsername(){
        assertEquals(false, accountService.addBooking("8",""));
    }

    @Test
    void verifyThatYouCantAddABookingWithoutPaymentInformation(){
        assertEquals(false, accountService.addBooking("8","Mr.Kind"));
    }
}
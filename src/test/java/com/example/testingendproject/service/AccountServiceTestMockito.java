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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTestMockito {

    @InjectMocks
    private AccountService accountService  = new AccountServiceImpl();

    @Mock
    AccountRepository accountRepository;

    @Mock PaymentExternal paymentExternal;



    @Test
    void verifyThatPaymentGoesThrough() {
        var account1 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();

        when(paymentExternal.checkPayment()).thenReturn("6457");
        when(accountRepository.findByUsername("Mr.Cool")).thenReturn(account1);


        accountService.addBooking("1", account1.getUsername());

        verify(paymentExternal, times(1)).checkPayment();
        verify(accountRepository, times(1)).findByUsername(any());
    }
}
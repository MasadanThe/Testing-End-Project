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
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @BeforeAll
    static void addEntries(@Autowired AccountService accountService) {
        var account1 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();
        accountService.createAccount(account1);
    }

    @Test
    void verifyWeGetAccountsFromGetAccounts() {

        assertNotNull(accountService.getAccounts());
    }

    @Test
    void verifyWeCanAddToTheDatabase() {
        var account1 = Account.builder()
                .username("Mr.Cool2")
                .accountType("ADMIN")
                .contactInformation("456")
                .paymentInformation("23232")
                .paymentHistory("")
                .activeBookings("").build();
        accountService.createAccount(account1);
        assertEquals(accountService.getAccounts().size(), 2);
    }

}
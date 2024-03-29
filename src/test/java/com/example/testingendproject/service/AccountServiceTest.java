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
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();
        accountService.createAccount(account1);
        var account2 = Account.builder()
                .username("Mr.Gostra")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("")
                .paymentHistory("342432,7675,322")
                .activeBookings("").build();
        accountService.createAccount(account2);
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
        assertEquals(3, accountService.getAccounts().size());
    }

    @Test
    void verifyWeCantAddAccountWithSameUsernameToTheDatabase() {
        var account1 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("456")
                .paymentInformation("23232")
                .paymentHistory("")
                .activeBookings("").build();

        assertEquals(false, accountService.createAccount(account1));
    }

    @Test
    void verifyThatUpdateAccountUpdatesInTheDatabase() {
        var account1 = Account.builder()
                .username("Mr.Cool")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();

        accountService.updateAccount(account1);
        List<Account> accountList1 = accountService.getAccounts();

        assertEquals("Mr.Cool", accountList1.get(0).getUsername());

    }

    @Test
    void verifyThatWeCantUpdateAnAccountThatDoesntExist() {
        var account1 = Account.builder()
                .username("Mr.Trojan")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("342432,7675,322")
                .activeBookings("1,5,4,3").build();



        assertEquals(false, accountService.updateAccount(account1));

    }

    @Test
    void verifyThatAccountIsDeleted() {
        var account1 = Account.builder()
                .username("Mr.Cool2")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();

        accountService.deleteAccount(account1.getUsername());
        List<Account> accountList1 = accountService.getAccounts();

        assertEquals(2, accountList1.size());

    }

    @Test
    void verifyThatWeCantDeleteAccountThatDoesntExist() {
        var account1 = Account.builder()
                .username("Mr.NotExist")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();

        assertEquals(false, accountService.deleteAccount(account1.getUsername()));

    }
    @Test
    void verifyThatWeCantDeleteAccountThatHasNoName() {
        var account1 = Account.builder()
                .username("")
                .accountType("ADMIN")
                .contactInformation("8973045653")
                .paymentInformation("435252432")
                .paymentHistory("")
                .activeBookings("").build();

        assertEquals(false, accountService.deleteAccount(account1.getUsername()));

    }

    @Test
    void verifyThatBookingIsDeletedFromAccount() {

        accountService.deleteBooking("1", "Mr.Cool");

        Account account = accountService.findByUsername("Mr.Cool");

        assertEquals("5,4,3", account.getActiveBookings());

    }

    @Test
    void verifyWeCantDeleteABookingFromAUserThatDoesNotExist() {

        assertEquals(false, accountService.deleteBooking("1", "Mr.NotExist"));

    }

    @Test
    void verifyWeCantDeleteABookingThatDoesNotExist() {

        assertEquals(false, accountService.deleteBooking("54", "Mr.Cool"));

    }

    @Test
    void verifyWeCantDeleteABookingFromAnAccountThatHasNoBookings() {

        assertEquals(false, accountService.deleteBooking("54", "Mr.Gostra"));

    }

    @Test
    void verifyWeCantAddABookingForAUserThatDoesNotExist() {

        assertEquals(false, accountService.addBooking("55", "Mr.NotExist"));

    }
    @Test
    void verifyWeCantAddABookingForAUserWithoutPaymentInformation() {

        assertEquals(false, accountService.addBooking("55", "Mr.Gostra"));

    }



}
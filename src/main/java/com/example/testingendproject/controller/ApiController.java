package com.example.testingendproject.controller;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.service.AccountService;
import com.example.testingendproject.service.AuthService;
import com.example.testingendproject.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    AuthService authService;
    @Autowired
    AccountService accountService;

    @PostMapping("create_account")
    public ResponseEntity<String> createAccount(@RequestBody Account account){
        if(accountService.createAccount(account))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("get_accounts")
    public ResponseEntity<List<Account>> getAccounts(){
        List<Account> accountList = accountService.getAccounts();
        if (accountList.size() > 0){
            return ResponseEntity.ok(accountList);
        }
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("update_account")
    public ResponseEntity<String> updateAccount(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("delete_account")
    public ResponseEntity<String> deleteAccount(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("add_booking")
    public ResponseEntity<String> addBooking(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("delete_booking")
    public ResponseEntity<String> deleteBooking(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("update_sale")
    public ResponseEntity<String> updateSale(){
        return ResponseEntity.ok("ok");
    }

    @GetMapping("get_routes")
    public ResponseEntity<String> getRoutes(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("create_booking_supplier")
    public ResponseEntity<String> createBookingSupplier(){
        return ResponseEntity.ok("ok");
    }
}

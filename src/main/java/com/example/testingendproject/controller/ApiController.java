package com.example.testingendproject.controller;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.FastBooking;
import com.example.testingendproject.model.UpdateSale;
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
    @Autowired(required = false)
    AccountService accountService;

    @Autowired(required = false)
    RouteService routeService;

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
    public ResponseEntity<String> updateAccount(@RequestBody Account account){
        if(accountService.updateAccount(account))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("delete_account")
    public ResponseEntity<String> deleteAccount(@RequestBody Account account){
        if(accountService.deleteAccount(account.getUsername()))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("add_booking")
    public ResponseEntity<String> addBooking(@RequestBody FastBooking fastBooking){
        if(accountService.addBooking(fastBooking.getId(), fastBooking.getUsername()))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("delete_booking")
    public ResponseEntity<String> deleteBooking(@RequestBody FastBooking fastBooking){
        if(accountService.deleteBooking(fastBooking.getId(), fastBooking.getUsername()))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("update_sale")
    public ResponseEntity<String> updateSale(@RequestBody UpdateSale updateSale){
        if(routeService.updateSale(updateSale.getId(), updateSale.getSalePrice(), updateSale.getUsername()))
        {
            return ResponseEntity.ok("ok");
        }
        return ResponseEntity.badRequest().build();
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

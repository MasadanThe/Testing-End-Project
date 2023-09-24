package com.example.testingendproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @PostMapping("create_account")
    public ResponseEntity<String> createAccount(){
        return ResponseEntity.ok("ok");
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
}

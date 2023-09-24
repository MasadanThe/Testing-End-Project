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
}

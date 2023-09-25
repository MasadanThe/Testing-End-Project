package com.example.testingendproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public interface AccountService {
    public void createAccount();
    public void updateAccount();

    public void deleteAccount();

    public void addBooking();

    public void deleteBooking();
}

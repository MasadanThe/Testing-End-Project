package com.example.testingendproject.service;

import org.springframework.stereotype.Service;

@Service
public interface PaymentExternal {
    public String checkPayment(String paymentInformation);
}

package com.example.testingendproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    private long id;
    private String username;
    private String contactInformation;
    private String paymentInformation;
    private String paymentHistory;
    private String activeBookings;
    private String accountType;

    public Account(String username, String contactInformation, String paymentInformation, String paymentHistory, String activeBookings, String accountType){
        this.username = username;
        this.contactInformation = contactInformation;
        this.paymentInformation = paymentInformation;
        this.paymentHistory = paymentHistory;
        this.activeBookings = activeBookings;
        this.accountType = accountType;

    }
}

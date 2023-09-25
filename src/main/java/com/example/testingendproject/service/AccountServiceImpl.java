package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Autowired(required = false)
    PaymentExternal paymentExternal;
    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public void updateAccount(Account account){
        Account foundAccount = accountRepository.findByUsername(account.getUsername());
        //Updates the information
        foundAccount.setUsername(account.getUsername());
        foundAccount.setActiveBookings(account.getActiveBookings());
        foundAccount.setContactInformation(account.getContactInformation());
        foundAccount.setPaymentHistory(account.getPaymentHistory());
        foundAccount.setPaymentInformation(account.getPaymentInformation());
        createAccount(foundAccount);
    }

    public void deleteAccount(String username){
        Account account = accountRepository.findByUsername(username);
        accountRepository.delete(account);
    }

    public void addBooking(String id, String username){
        Account foundAccount = accountRepository.findByUsername(username);
        String paymentCode = paymentExternal.checkPayment();
        if(!paymentCode.equals("0")){
            //Separates payments and active bookings with ','
            foundAccount.setPaymentHistory(foundAccount.getPaymentHistory() + paymentCode + ",");
            foundAccount.setActiveBookings(foundAccount.getActiveBookings() + id + "," );

        }
        createAccount(foundAccount);
    }

    public void deleteBooking(){

    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}

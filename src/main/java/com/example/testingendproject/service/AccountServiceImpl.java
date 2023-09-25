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
    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public void updateAccount(Long id, Account account){
        //Gets all accounts and find the one by id
        //Had problems finding by id in JPA that's why it is done manually
        List<Account> accountList = getAccounts();
        Account foundAccount = new Account();
        for (Account account1: accountList) {
            if (account1.getId() == id){
                foundAccount = account1;
            }

        }
        //Updates the information
        foundAccount.setUsername(account.getUsername());
        foundAccount.setActiveBookings(account.getActiveBookings());
        foundAccount.setContactInformation(account.getContactInformation());
        foundAccount.setPaymentHistory(account.getPaymentHistory());
        foundAccount.setPaymentInformation(account.getPaymentInformation());
        createAccount(foundAccount);
    }

    public void deleteAccount(){

    }

    public void addBooking(){

    }

    public void deleteBooking(){

    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}

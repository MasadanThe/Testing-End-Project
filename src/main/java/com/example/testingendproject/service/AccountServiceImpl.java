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
    public boolean createAccount(Account account){
        Account foundAccount = findByUsername(account.getUsername());
        try
        {
            foundAccount.getUsername();
            if(!foundAccount.getUsername().equals(account.getUsername()))
            {
                accountRepository.save(account);
                return true;
            }
            return false;

        }
        catch (Exception exception)
        {
            accountRepository.save(account);
            return true;
        }

    }

    public void updateAccount(Account account){
        Account foundAccount = findByUsername(account.getUsername());
        //Updates the information
        foundAccount.setUsername(account.getUsername());
        foundAccount.setActiveBookings(account.getActiveBookings());
        foundAccount.setContactInformation(account.getContactInformation());
        foundAccount.setPaymentHistory(account.getPaymentHistory());
        foundAccount.setPaymentInformation(account.getPaymentInformation());
        createAccount(foundAccount);
    }

    public void deleteAccount(String username){
        Account account = findByUsername(username);
        accountRepository.delete(account);
    }

    public void addBooking(String id, String username){
        Account foundAccount = findByUsername(username);
        String paymentCode = paymentExternal.checkPayment();
        if(!paymentCode.equals("0")){
            //Separates payments and active bookings with ','
            foundAccount.setPaymentHistory(foundAccount.getPaymentHistory() + paymentCode + ",");
            foundAccount.setActiveBookings(foundAccount.getActiveBookings() + id + "," );

        }
        createAccount(foundAccount);
    }

    public void deleteBooking(String id, String username){
        Account foundAccount = findByUsername(username);
        //Splits the bookings on ','
        String[] bookings = foundAccount.getActiveBookings().split(",");
        String newBookingInformation = "";

        //Loops through bookings and saves all id's of the bookings that should not be removed
        for(int i = 0; i < bookings.length; i++){
            if(!bookings[i].equals(id)){
                newBookingInformation += bookings[i] + ",";
            }
        }
        //If there is more bookings, remove the last ','
        if(!newBookingInformation.isEmpty())
        {
           newBookingInformation = newBookingInformation.substring(0, newBookingInformation.length() - 1);
        }
        foundAccount.setActiveBookings(newBookingInformation);
        createAccount(foundAccount);
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}

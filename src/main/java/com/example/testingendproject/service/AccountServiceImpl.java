package com.example.testingendproject.service;

import com.example.testingendproject.model.Account;
import com.example.testingendproject.model.Route;
import com.example.testingendproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired(required = false)
    AccountRepository accountRepository;

    @Autowired(required = false)
    PaymentExternal paymentExternal;
    public boolean createAccount(Account account){
        Account foundAccount = accountRepository.findByUsername(account.getUsername());

            //Sometimes the found username is not null but not the same either
            //If it is not the same the account doesn't exist
            if(foundAccount == null || foundAccount.getUsername().isEmpty())
            {
                accountRepository.save(account);
                return true;
            }
        return false;


    }

    public boolean updateAccount(Account account){
        Account foundAccount = findByUsername(account.getUsername());

        if(foundAccount == null || foundAccount.getUsername().isEmpty() || account.getUsername().equals(""))
        {
            return false;
        }
        //Updates the information
        foundAccount.setUsername(account.getUsername());
        foundAccount.setActiveBookings(account.getActiveBookings());
        foundAccount.setContactInformation(account.getContactInformation());
        foundAccount.setPaymentHistory(account.getPaymentHistory());
        foundAccount.setPaymentInformation(account.getPaymentInformation());
        accountRepository.save(foundAccount);
        return true;
    }

    public boolean deleteAccount(String username){
        Account foundAccount = findByUsername(username);
        if(foundAccount == null || foundAccount.getUsername().isEmpty())
        {
            return false;
        }
        accountRepository.delete(foundAccount);
        return true;
    }

    public boolean addBooking(String id, String username){
        Account foundAccount = findByUsername(username);

        //Checks if the user exist
        if(foundAccount == null || foundAccount.getUsername().isEmpty())
        {
            return false;
        }

        //If no paymentInformation exist
        if(foundAccount.getPaymentInformation().isEmpty()){
            return false;
        }

        String paymentCode = paymentExternal.checkPayment(foundAccount.getPaymentInformation());
        //This will always be true since check(Payment) doesn't return any text
        if(!paymentCode.equals("0")){
            //Separates payments and active bookings with ','
            foundAccount.setPaymentHistory(foundAccount.getPaymentHistory() + paymentCode + ",");
            //If you don't have any bookings
            if(foundAccount.getActiveBookings().isEmpty())
            {
                foundAccount.setActiveBookings(id);
            }
            else
            {
                foundAccount.setActiveBookings(foundAccount.getActiveBookings()  + "," + id);
            }


            updateAccount(foundAccount);

            return true;

        }
        return false;

    }

    public boolean deleteBooking(String id, String username){
        Account foundAccount = findByUsername(username);

        //Checks if the user exist
        if(foundAccount == null || foundAccount.getUsername().isEmpty())
        {
            return false;
        }

        //Splits the bookings on ','
        String bookings = foundAccount.getActiveBookings();
        String[] bookingsSplited = bookings.split(",");
        String newBookingInformation = "";

        //If account has no bookings
        if(bookings.isEmpty())
        {
            return false;
        }


        //Loops through bookings and saves all id's of the bookings that should not be removed
        for(int i = 0; i < bookingsSplited.length; i++){
            if(!bookingsSplited[i].equals(id)){
                newBookingInformation += bookingsSplited[i] + ",";
            }
        }

        //If there is more bookings, remove the last ','
        if(!newBookingInformation.isEmpty())
        {
           newBookingInformation = newBookingInformation.substring(0, newBookingInformation.length() - 1);
        }


        //If the bookings have changed, meaning we managed to delete one
        if(!newBookingInformation.equals(bookings))
        {
            foundAccount.setActiveBookings(newBookingInformation);
            updateAccount(foundAccount);
            return true;
        }
        return false;
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }
}

package com.ironhack.CRM.service;

import com.ironhack.CRM.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    private double findMeanEmployeeCount(){
        return accountRepository.findMeanEmployeeCount();
    }
}

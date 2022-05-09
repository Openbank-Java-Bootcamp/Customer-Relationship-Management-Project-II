package com.ironhack.CRM.repositories;

import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private List<Account> accounts;


    @BeforeEach
    void setUp() {
        accounts = accountRepository.saveAll(List.of(
                new Account(Industry.PRODUCE, 420, "Bilbao", "Spain"),
                new Account(Industry.MANUFACTURING, 245, "Valencia", "Spain"),
                new Account(Industry.ECOMMERCE, 60, "Salamanca", "Spain"),
                new Account(Industry.MEDICAL, 125, "Malaga", "Spain"),
                new Account(Industry.OTHER, 35, "Barcelona", "Spain")
        ));

    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    public void findMeanEmployeeCount() {
        assertEquals(177, accountRepository.findMeanEmployeeCount());
    }

    @Test
    public void findMedianEmployeeCount() {
        assertEquals(125, accountRepository.findMedianEmployeeCount());
    }

    @Test
    public void findMaxEmployeeCount() {
        assertEquals(420, accountRepository.findMaxEmployeeCount());
    }

    @Test
    public void findMinEmployeeCount() {
        assertEquals(35, accountRepository.findMinEmployeeCount());
    }
}
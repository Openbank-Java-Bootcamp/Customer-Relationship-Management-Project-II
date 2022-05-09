package com.ironhack.CRM.repositories;

import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.models.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    @Autowired
    private AccountRepository testAccountRepository;

    private Account account1;
    private Account account2;
    private Account account3;
    private Account account4;
    private Account account5;
    private Account account6;


    @BeforeEach
    void setUp() {
        account1 = new Account(Industry.PRODUCE, 420, "Bilbao", "Spain");
        account2 = new Account(Industry.MANUFACTURING, 245, "Valencia", "Spain");
        account3 = new Account(Industry.ECOMMERCE, 60, "Salamanca", "Spain");
        account4 = new Account(Industry.MEDICAL, 125, "Malaga", "Spain");
        account5 = new Account(Industry.OTHER, 35, "Barcelona", "Spain");
        account4 = new Account(Industry.MANUFACTURING, 315, "Madrid", "Spain");
    }

    @AfterEach
    void tearDown() {
        testAccountRepository.deleteAll();
    }

    @Test
    public void testFindMeanEmployeeCount() {
        assertEquals(240.0, testAccountRepository.findMeanEmployeeCount());
    }
}
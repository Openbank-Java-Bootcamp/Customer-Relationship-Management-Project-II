package com.ironhack.CRM.repositories;

import com.ironhack.CRM.CrmApplication;
import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.models.Account;
import com.ironhack.CRM.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private List<Account> accounts;


    @BeforeEach
    void setUp() {
//        accounts = accountRepository.saveAll(List.of(
//                new Account(Industry.PRODUCE, 420, "Bilbao", "Spain"),
//                new Account(Industry.MANUFACTURING, 245, "Valencia", "Spain"),
//                new Account(Industry.ECOMMERCE, 60, "Salamanca", "Spain"),
//                new Account(Industry.MEDICAL, 125, "Malaga", "Spain"),
//                new Account(Industry.OTHER, 35, "Barcelona", "Spain")
//        ));
        Account account1 = new Account(Industry.PRODUCE, 420, "Bilbao", "Spain");
        Account account2 = new Account(Industry.MANUFACTURING, 245, "Valencia", "Spain");
        Account account3 = new Account(Industry.ECOMMERCE, 60, "Salamanca", "Spain");
        Account account4 = new Account(Industry.MEDICAL, 125, "Malaga", "Spain");
        Account account5 = new Account(Industry.OTHER, 35, "Barcelona", "Spain");
        accountRepository.saveAll(List.of(account1, account2, account3, account4, account5));

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
    public void findEmployeeCounts() {
        assertEquals(5, accountRepository.findEmployeeCounts().size());
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
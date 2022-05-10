package com.ironhack.CRM.repositories;

import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpportunityRepositoryTest {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private AccountRepository accountRepository;


    private List<Lead> leads;
    private List<Contact> contacts;
    private List<SalesRep> salesReps;
    private List<Opportunity> opportunities;
    private List<Account> accounts;

    @BeforeEach
    void setUp() {

        salesReps = salesRepRepository.saveAll(List.of(
                new SalesRep("Laura"),
                new SalesRep("Edita"),
                new SalesRep("Lina")
        ));

        leads = leadRepository.saveAll(List.of(
                new Lead("Cris", 121212121, "lll@lll.lt", "Jasons", salesReps.get(1)),
                new Lead("Jon", 111111111, "aaa@aaa.aa", "Jacks", salesReps.get(1)),
                new Lead("Sandra", 222222222, "sss@sss.ss", "Thompson", salesReps.get(2)),
                new Lead("Tom", 333333333, "ddd@ddd.dd", "Son", salesReps.get(1)),
                new Lead("Thomas", 444444444, "fff@fff.ff", "Sons", salesReps.get(3)),
                new Lead("Cristina", 555555555, "ggg@ggg.gg", "Jasons", salesReps.get(1)),
                new Lead("Pedro", 666666666, "hhh@hhh.hh", "Pros", salesReps.get(2)),
                new Lead("Cristina", 777777777, "jjj@jjj.jj", "Pross", salesReps.get(2)),
                new Lead("Chris", 888888888, "eee@eee.ee", "Cross", salesReps.get(1)),
                new Lead("Germis", 999999999, "rrr@rrr.rr", "CrossCountry", salesReps.get(3))
        ));

        contacts = contactRepository.saveAll(List.of(
                new Contact("Cris", 121212121, "lll@lll.com"),
                new Contact("Jon", 111111111, "aaa@aaa.aa"),
                new Contact("Sandra", 222222222, "sss@sss.ss"),
                new Contact("Tom", 333333333, "ddd@ddd.dd"),
                new Contact("Thomas", 444444444, "fff@fff.ff"),
                new Contact("Cristina", 555555555, "ggg@ggg.gg"),
                new Contact("Pedro", 666666666, "hhh@hhh.hh"),
                new Contact("Cristina", 777777777, "jjj@jjj.jj"),
                new Contact("Chris", 888888888, "eee@eee.ee"),
                new Contact("Germis", 999999999, "rrr@rrr.rr")
        ));

        opportunities = opportunityRepository.saveAll(List.of(
                new Opportunity(Product.BOX, 10, contacts.get(1), salesReps.get(1)),
                new Opportunity(Product.FLATBED, 1, contacts.get(2), salesReps.get(1)),
                new Opportunity(Product.BOX, 15, contacts.get(3), salesReps.get(2)),
                new Opportunity(Product.HYBRID, 2, contacts.get(4), salesReps.get(2)),
                new Opportunity(Product.BOX, 5, contacts.get(5), salesReps.get(1)),
                new Opportunity(Product.BOX, 50, contacts.get(6), salesReps.get(1)),
                new Opportunity(Product.BOX, 4, contacts.get(7), salesReps.get(2)),
                new Opportunity(Product.BOX, 4, contacts.get(8), salesReps.get(1)),
                new Opportunity(Product.BOX, 4, contacts.get(9), salesReps.get(2)),
                new Opportunity(Product.BOX, 4, contacts.get(10), salesReps.get(3))
        ));

        accounts = accountRepository.saveAll(List.of(
                new Account(Industry.ECOMMERCE, 150, "Valencia", "Spain"),
                new Account(Industry.MEDICAL, 15, "Madrid", "Spain"),
                new Account(Industry.MANUFACTURING, 1, "London", "Uk"),
                new Account(Industry.MANUFACTURING, 10, "Madrid", "Spain"),
                new Account(Industry.MEDICAL, 25, "Madrid", "Spain"),
                new Account(Industry.OTHER, 20, "London", "Uk"),
                new Account(Industry.OTHER, 10, "London", "Uk"),
                new Account(Industry.MEDICAL, 21, "Madrid", "Spain"),
                new Account(Industry.ECOMMERCE, 10, "Barcelona", "Spain"),
                new Account(Industry.MANUFACTURING, 5, "London", "Uk")
        ));
    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();;
        leadRepository.deleteAll();
        contactRepository.deleteAll();
        opportunityRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
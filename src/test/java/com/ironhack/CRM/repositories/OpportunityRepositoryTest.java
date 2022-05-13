package com.ironhack.CRM.repositories;

import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;
import com.ironhack.CRM.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
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
                new Lead("Cris", 121212121, "lll@lll.lt", "Jasons", salesReps.get(0)),
                new Lead("Jon", 111111111, "aaa@aaa.aa", "Jacks", salesReps.get(0)),
                new Lead("Sandra", 222222222, "sss@sss.ss", "Thompson", salesReps.get(1)),
                new Lead("Tom", 333333333, "ddd@ddd.dd", "Son", salesReps.get(0)),
                new Lead("Thomas", 444444444, "fff@fff.ff", "Sons", salesReps.get(2)),
                new Lead("Cristina", 555555555, "ggg@ggg.gg", "Jasons", salesReps.get(0)),
                new Lead("Pedro", 666666666, "hhh@hhh.hh", "Pros", salesReps.get(1)),
                new Lead("Cristina", 777777777, "jjj@jjj.jj", "Pross", salesReps.get(1)),
                new Lead("Chris", 888888888, "eee@eee.ee", "Cross", salesReps.get(0)),
                new Lead("Germis", 999999999, "rrr@rrr.rr", "CrossCountry", salesReps.get(2))
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

        opportunities = opportunityRepository.saveAll(List.of(

                new Opportunity(Product.BOX, 10, contacts.get(0), salesReps.get(0),accounts.get(0)),
                new Opportunity(Product.FLATBED, 1, contacts.get(1), salesReps.get(0),accounts.get(2)),
                new Opportunity(Product.BOX, 15, contacts.get(2), salesReps.get(2),accounts.get(5)),
                new Opportunity(Product.HYBRID, 2, contacts.get(3), salesReps.get(1),accounts.get(3)),
                new Opportunity(Product.BOX, 5, contacts.get(4), salesReps.get(0),accounts.get(4)),
                new Opportunity(Product.BOX, 50, contacts.get(5), salesReps.get(0),accounts.get(2)),
                new Opportunity(Product.BOX, 4, contacts.get(6), salesReps.get(1),accounts.get(1)),
                new Opportunity(Product.BOX, 4, contacts.get(7), salesReps.get(0),accounts.get(2)),
                new Opportunity(Product.BOX, 4, contacts.get(8), salesReps.get(1),accounts.get(3)),
                new Opportunity(Product.BOX, 4, contacts.get(9), salesReps.get(2),accounts.get(6))

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

    @Test
    public void findCountGroupByProduct() {
        assertEquals(3, opportunityRepository.findCountGroupByProduct().size());
        assertEquals(BigInteger.valueOf(8), opportunityRepository.findCountGroupByProduct().get(0)[1]);//# of BOX
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountGroupByProduct().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountGroupByProduct().get(2)[1]);
    }

    @Test
    public void findCountWithStatusWonGroupByProduct() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity3 = opportunities.get(2);
        opportunity1.setStatus(Status.CLOSED_WON);
        opportunity2.setStatus(Status.CLOSED_WON);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));
        assertEquals(2, opportunityRepository.findCountWithStatusWonGroupByProduct().size());//only two types of products have lost-won status
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountWithStatusWonGroupByProduct().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupByProduct().get(1)[1]);
        //assertEquals(0, opportunityRepository.findCountWithStatusWonGroupByProduct().get(2)[1]);
    }

    @Test
    public void findCountWithStatusLostGroupByProduct() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity4 = opportunities.get(3);
        opportunity1.setStatus(Status.CLOSED_LOST);
        opportunity2.setStatus(Status.CLOSED_LOST);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity4));
        assertEquals(3, opportunityRepository.findCountWithStatusLostGroupByProduct().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusLostGroupByProduct().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusLostGroupByProduct().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusLostGroupByProduct().get(2)[1]);
    }

    @Test
    public void findCountWithStatusOpenGroupByProduct() {
        Opportunity opportunity10 = opportunities.get(9);
        Opportunity opportunity9 = opportunities.get(8);
        opportunity10.setStatus(Status.CLOSED_LOST);
        opportunity9.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity10, opportunity9));
        assertEquals(3, opportunityRepository.findCountWithStatusOpenGroupByProduct().size());
        assertEquals(BigInteger.valueOf(6), opportunityRepository.findCountWithStatusOpenGroupByProduct().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusOpenGroupByProduct().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusOpenGroupByProduct().get(2)[1]);
    }


    @Test
    public void findCountGroupByCity(){
        assertEquals(4, opportunityRepository.findCountGroupByCity().size());
    }

    @Test
    public void findCountWithStatusWonGroupByCity(){

        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity3 = opportunities.get(2);
        opportunity1.setStatus(Status.CLOSED_WON);
        opportunity2.setStatus(Status.CLOSED_WON);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));

        assertEquals(3, opportunityRepository.findCountWithStatusWonGroupByCity().size());
    }

    @Test
    public void findCountWithStatusLostGroupByCity(){
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity3 = opportunities.get(2);
        Opportunity opportunity4 = opportunities.get(4);
        opportunity1.setStatus(Status.CLOSED_LOST);
        opportunity2.setStatus(Status.CLOSED_LOST);
        opportunity3.setStatus(Status.CLOSED_LOST);
        opportunity4.setStatus(Status.CLOSED_WON);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3, opportunity4));
        assertEquals(3, opportunityRepository.findCountWithStatusLostGroupByCity().size());
    }

    @Test
    public void findCountWithStatusOpenGroupByCity(){
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity3 = opportunities.get(2);
        Opportunity opportunity4 = opportunities.get(4);
        opportunity1.setStatus(Status.OPEN);
        opportunity2.setStatus(Status.OPEN);
        opportunity3.setStatus(Status.OPEN);
        opportunity4.setStatus(Status.OPEN);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3, opportunity4));
        assertEquals(3, opportunityRepository.findCountWithStatusOpenGroupByCity().size());
    }

    @Test
    public void countMeanOfOpportunitiesAssociatedToAccount(){
        assertEquals((double) 3.33, opportunityRepository.countMeanOfOpportunitiesAssociatedToAccount());
    }

    @Test
    public void maxOpportunitiesAssociatedToAccount(){
        assertEquals(5, opportunityRepository.maxOpportunitiesAssociatedToAccount());
    }

    @Test
    public void minOpportunitiesAssociatedToAccount(){
        assertEquals(2, opportunityRepository.minOpportunitiesAssociatedToAccount());
    }


    @Test
    public void findCountGroupBySalesRep() {
        assertEquals(3, opportunityRepository.findCountGroupBySalesRep().size());
        assertEquals(BigInteger.valueOf(5), opportunityRepository.findCountGroupBySalesRep().get(0)[1]);
        assertEquals(BigInteger.valueOf(3), opportunityRepository.findCountGroupBySalesRep().get(1)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountGroupBySalesRep().get(2)[1]);
    }
    @Test
    public void findCountWithStatusOpenGroupByBySalesRep() {
        Opportunity opportunity10 = opportunities.get(9);
        Opportunity opportunity9 = opportunities.get(8);
        opportunity10.setStatus(Status.CLOSED_WON);
        opportunity9.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity10, opportunity9));
        assertEquals(3, opportunityRepository.findCountWithStatusOpenGroupBySalesRep().size());
        assertEquals(BigInteger.valueOf(5), opportunityRepository.findCountWithStatusOpenGroupBySalesRep().get(0)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountWithStatusOpenGroupBySalesRep().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusOpenGroupBySalesRep().get(2)[1]);
    }

    @Test
    public void findCountWithStatusWonGroupBySalesRep() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(2);
        Opportunity opportunity3 = opportunities.get(3);
        opportunity1.setStatus(Status.CLOSED_WON);
        opportunity2.setStatus(Status.CLOSED_WON);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));
        assertEquals(3, opportunityRepository.findCountWithStatusWonGroupBySalesRep().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupBySalesRep().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupBySalesRep().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupBySalesRep().get(2)[1]);
    }
    @Test
    public void findCountWithStatusLostGroupBySalesRep() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity4 = opportunities.get(3);
        opportunity1.setStatus(Status.CLOSED_LOST);
        opportunity2.setStatus(Status.CLOSED_LOST);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity4));
        assertEquals(2, opportunityRepository.findCountWithStatusLostGroupBySalesRep().size());
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountWithStatusLostGroupBySalesRep().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusLostGroupBySalesRep().get(1)[1]);
    }

    @Test
    public void findCountGroupByIndustry() {
        assertEquals(4, opportunityRepository.findCountGroupByIndustry().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountGroupByIndustry().get(0)[1]);
        assertEquals(BigInteger.valueOf(5), opportunityRepository.findCountGroupByIndustry().get(1)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountGroupByIndustry().get(2)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountGroupByIndustry().get(3)[1]);
    }
    @Test
    public void findCountWithStatusOpenGroupByByIndustry() {
        Opportunity opportunity10 = opportunities.get(9);
        Opportunity opportunity9 = opportunities.get(8);
        opportunity10.setStatus(Status.CLOSED_WON);
        opportunity9.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity10, opportunity9));
        assertEquals(4, opportunityRepository.findCountWithStatusOpenGroupByIndustry().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusOpenGroupByIndustry().get(0)[1]);
        assertEquals(BigInteger.valueOf(4), opportunityRepository.findCountWithStatusOpenGroupByIndustry().get(1)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountWithStatusOpenGroupByIndustry().get(2)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusOpenGroupByIndustry().get(3)[1]);
    }

    @Test
    public void findCountWithStatusWonGroupByIndustry() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(2);
        Opportunity opportunity3 = opportunities.get(3);
        opportunity1.setStatus(Status.CLOSED_WON);
        opportunity2.setStatus(Status.CLOSED_WON);
        opportunity3.setStatus(Status.CLOSED_WON);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity3));
        assertEquals(3, opportunityRepository.findCountWithStatusWonGroupByIndustry().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupByIndustry().get(0)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupByIndustry().get(1)[1]);
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusWonGroupByIndustry().get(2)[1]);
    }
    @Test
    public void findCountWithStatusLostGroupByIndustry() {
        Opportunity opportunity1 = opportunities.get(0);
        Opportunity opportunity2 = opportunities.get(1);
        Opportunity opportunity4 = opportunities.get(3);
        opportunity1.setStatus(Status.CLOSED_LOST);
        opportunity2.setStatus(Status.CLOSED_LOST);
        opportunity4.setStatus(Status.CLOSED_LOST);
        opportunityRepository.saveAll(List.of(opportunity1, opportunity2, opportunity4));
        assertEquals(2, opportunityRepository.findCountWithStatusLostGroupByIndustry().size());
        assertEquals(BigInteger.valueOf(1), opportunityRepository.findCountWithStatusLostGroupByIndustry().get(0)[1]);
        assertEquals(BigInteger.valueOf(2), opportunityRepository.findCountWithStatusLostGroupByIndustry().get(1)[1]);
    }





}
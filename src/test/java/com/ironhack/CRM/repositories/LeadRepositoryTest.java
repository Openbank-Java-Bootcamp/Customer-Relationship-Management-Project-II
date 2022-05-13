package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Lead;
import com.ironhack.CRM.models.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadRepositoryTest {

    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    private List<Lead> leads;
    private List<SalesRep> salesReps;

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
    }

    @AfterEach
    void tearDown() {
        salesRepRepository.deleteAll();;
        leadRepository.deleteAll();
    }

    @Test
    public void findCountGroupBySalesRep() {
        assertEquals(3, leadRepository.findCountGroupBySalesRep().size());
        assertEquals(BigInteger.valueOf(5), leadRepository.findCountGroupBySalesRep().get(0)[1]);
        assertEquals(BigInteger.valueOf(3), leadRepository.findCountGroupBySalesRep().get(1)[1]);
        assertEquals(BigInteger.valueOf(2), leadRepository.findCountGroupBySalesRep().get(2)[1]);
    }
}
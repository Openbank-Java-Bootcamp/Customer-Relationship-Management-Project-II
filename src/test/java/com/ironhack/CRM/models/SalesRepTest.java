package com.ironhack.CRM.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesRepTest {
    private SalesRep salesRep1;
    private Lead lead1;

    @BeforeEach
    void setUp() {
        salesRep1 = new SalesRep("Tomas Serrano");
        lead1 = new Lead("Rebecca Jones", 346286325, "rj@nomail.com","Ironhack", salesRep1);
    }

    @Test
    void addLeadToList_ValidLead_Works() {
        salesRep1.addLeadToList(lead1);
        assertEquals(1, salesRep1.getLeadList().size());
    }

    @Test
    void addLeadToList_NullLead_Throws() {
        Lead lead2 = null;
        assertThrows(IllegalArgumentException.class, ()-> salesRep1.addLeadToList(lead2));
    }
}
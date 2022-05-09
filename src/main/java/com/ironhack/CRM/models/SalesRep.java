package com.ironhack.CRM.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "sales_rep")
public class SalesRep {
    @Id
    private String id;

    private String name;

    private static AtomicInteger salesRepIdCounter = new AtomicInteger();

    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Lead> leadList;


    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Opportunity> opportunityList;


    //CONSTRUCTORS

    public SalesRep() {
    }

    public SalesRep(String name) {
        this.id = createID();
        this.name = name;
        this.leadList = new ArrayList<>();
        this.opportunityList = new ArrayList<>();
    }


    //SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setLeadList(List<Lead> leadList) {
        this.leadList = leadList;
    }

    public void setOpportunityList(List<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    //GETTERS


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Lead> getLeadList() {
        return leadList;
    }

    public List<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    //METHODS
    public static String createID() {
        return String.valueOf(salesRepIdCounter.getAndIncrement() + 1);
    }

    public void addLeadToList(Lead lead) throws IllegalArgumentException{
        if (lead == null) {
            throw new IllegalArgumentException("Invalid Lead"); }
        this.leadList.add(lead);
    }

    //EQUALS, HASHCODE & ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesRep salesRep = (SalesRep) o;

        if (id != null ? !id.equals(salesRep.id) : salesRep.id != null) return false;
        if (name != null ? !name.equals(salesRep.name) : salesRep.name != null) return false;
        if (leadList != null ? !leadList.equals(salesRep.leadList) : salesRep.leadList != null) return false;
        return opportunityList != null ? opportunityList.equals(salesRep.opportunityList) : salesRep.opportunityList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (leadList != null ? leadList.hashCode() : 0);
        result = 31 * result + (opportunityList != null ? opportunityList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nSalesRep " + id +
                "\nName:  " + name
               // "\nLeads:  " + leadList +
                //"\nOpportunities:  " + opportunityList
                ;
    }
}

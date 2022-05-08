package com.ironhack.CRM.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sales_rep")
public class SalesRep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Lead> leadList;


    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
    private List<Opportunity> opportunityList;


    //CONSTRUCTORS

    public SalesRep() {
    }

    public SalesRep(Integer id, String name, List<Lead> leadList, List<Opportunity> opportunityList) {
        this.id = id;
        this.name = name;
        this.leadList = leadList;
        this.opportunityList = opportunityList;
    }



    //SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

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


    public Integer getId() {
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
        return "SalesRep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leadList=" + leadList +
                ", opportunityList=" + opportunityList +
                '}';
    }
}

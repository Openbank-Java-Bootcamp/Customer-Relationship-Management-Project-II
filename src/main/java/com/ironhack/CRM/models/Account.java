package com.ironhack.CRM.models;

import com.ironhack.CRM.enums.Industry;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name= "account")
public class Account {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Industry industry;
    @Column(name= "employee_count")
    private int employeeCount;
    private String city;
    private String country;


    @OneToMany(mappedBy ="account", cascade = CascadeType.ALL)
    private Map<String, Contact> contactList;


    @OneToMany(mappedBy ="account", cascade = CascadeType.ALL)
    private Map<String, Opportunity> opportunityList;
    private static AtomicInteger accountIdCounter = new AtomicInteger();
    // Should Account have Lead's company name and be added to constructor?



    //CONSTRUCTORS

    public Account() {
    }

    public Account(Industry industry, int employeeCount, String city, String country) {
        this.id = createID();
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = new HashMap<>();
        this.opportunityList = new HashMap<>();
    }

    public Account(Industry industry, int employeeCount, String city, String country, Map<String, Contact> contactList, Map<String, Opportunity> opportunityList) {
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
        this.opportunityList = opportunityList;
    }

    //SETTERS

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setContactList(Map<String, Contact> contactList) {
        this.contactList = contactList;
    }

    public void setOpportunityList(Map<String, Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    //GETTERS


    public String getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Map<String, Contact> getContactList() {
        return contactList;
    }

    public Map<String, Opportunity> getOpportunityList() {
        return opportunityList;
    }



    //METHODS
    public static String createID() {
        return String.valueOf(accountIdCounter.getAndIncrement() + 1);
    }



    //EQUALS, HASHCODE & ToString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (employeeCount != account.employeeCount) return false;
        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (industry != account.industry) return false;
        if (city != null ? !city.equals(account.city) : account.city != null) return false;
        if (country != null ? !country.equals(account.country) : account.country != null) return false;
        if (contactList != null ? !contactList.equals(account.contactList) : account.contactList != null) return false;
        return opportunityList != null ? opportunityList.equals(account.opportunityList) : account.opportunityList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + employeeCount;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (contactList != null ? contactList.hashCode() : 0);
        result = 31 * result + (opportunityList != null ? opportunityList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", industry=" + industry +
                ", employeeCount=" + employeeCount +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contactList=" + contactList +
                ", opportunityList=" + opportunityList +
                '}';
    }
}

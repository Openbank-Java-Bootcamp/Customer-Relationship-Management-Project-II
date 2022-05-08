package com.ironhack.CRM.models;

import com.ironhack.CRM.enums.Industry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name= "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Industry industry;
    @Column(name= "employee_count")
    private int employeeCount;
    private String city;
    private String country;

    //PK one account has many contacts
    @OneToMany(mappedBy ="account", cascade = CascadeType.ALL)
    private List<Contact> contactList;

    //PK one account has many opportunities
    @OneToMany(mappedBy ="account", cascade = CascadeType.ALL)
    private List<Opportunity> opportunityList;
    private static AtomicInteger accountIdCounter = new AtomicInteger();
    // Should Account have Lead's company name and be added to constructor?




    //CONSTRUCTORS


    public Account() {
    }

    public Account(Integer id, Industry industry, int employeeCount, String city, String country, List<Contact> contactList) {
        this.id = id;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contactList = contactList;
    }

    //SETTERS


    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    //GETTERS
    public Integer getId() {
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

    public List<Contact> getContactList() {
        return contactList;
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
                "id=" + id +
                ", industry=" + industry +
                ", employeeCount=" + employeeCount +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contactList=" + contactList +
                ", opportunityList=" + opportunityList +
                '}';
    }
}

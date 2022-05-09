package com.ironhack.CRM.models;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name = "lead")
public class Lead {
    @Id
    private String id;
    private String name;
    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;
    @Column(name = "company_name")
    private String companyName;
    private static AtomicInteger leadIdCounter = new AtomicInteger();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;


    //CONSTRUCTORS


    public Lead(String name, int phoneNumber, String email, String companyName, SalesRep salesRep) {
        this.id = createID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRep = salesRep;

    }

    // only used to create a null Lead for testing purposes
    public Lead(String leadsName, String leadsPhoneNumberAsInt, String leadsEmail, String leadsCompany) {}

    public Lead() {

    }


    //SETTERS


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }


    //GETTERS

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    //METHODS

    private static String createID() {
        return String.valueOf(leadIdCounter.getAndIncrement() + 1);
    }

    public void setIdToNull() {
        this.id = null;
    }

    //EQUALS, HASHCODE & ToString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lead lead = (Lead) o;

        if (phoneNumber != lead.phoneNumber) return false;
        if (id != null ? !id.equals(lead.id) : lead.id != null) return false;
        if (name != null ? !name.equals(lead.name) : lead.name != null) return false;
        if (email != null ? !email.equals(lead.email) : lead.email != null) return false;
        if (companyName != null ? !companyName.equals(lead.companyName) : lead.companyName != null) return false;
        return salesRep != null ? salesRep.equals(lead.salesRep) : lead.salesRep == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + phoneNumber;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (salesRep != null ? salesRep.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lead " + id +
                "\nname:  " + name +
                "\nphoneNumber:  " + phoneNumber +
                "\nemail:  " + email +
                "\ncompanyName:  " + companyName +
                "\n\nsalesRep:  " + salesRep
                ;
    }
}

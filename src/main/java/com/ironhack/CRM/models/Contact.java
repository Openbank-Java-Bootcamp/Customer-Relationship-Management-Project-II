package com.ironhack.CRM.models;

import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;
    private static AtomicInteger contactIdCounter = new AtomicInteger();

    //FK one account has many contacts
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    //FK one opportunity has one contact
    @OneToOne(mappedBy = "decisionMaker", fetch = FetchType.LAZY)
    private Opportunity opportunity;



    //CONSTRUCTORS

    public Contact() {
    }

    public Contact(Integer id, String name, int phoneNumber, String email, Account account, Opportunity opportunity) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.account = account;
        this.opportunity = opportunity;
    }

    //GETTERS

    public Integer getId() {
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

    public Account getAccount() {
        return account;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }


    //SETTERS


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    //METHODS
    public static String createID() {
        return String.valueOf(contactIdCounter.getAndIncrement() + 1);
    }



    //EQUALS, HASHCODE & ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (phoneNumber != contact.phoneNumber) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (account != null ? !account.equals(contact.account) : contact.account != null) return false;
        return opportunity != null ? opportunity.equals(contact.opportunity) : contact.opportunity == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + phoneNumber;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (opportunity != null ? opportunity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", account=" + account +
                ", opportunity=" + opportunity +
                '}';
    }
}


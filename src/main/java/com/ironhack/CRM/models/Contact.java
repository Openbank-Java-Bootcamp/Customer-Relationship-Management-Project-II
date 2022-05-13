package com.ironhack.CRM.models;

import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    private String id;
    private String name;
    @Column(name = "phone_number")
    private int phoneNumber;
    private String email;
    private static AtomicInteger contactIdCounter = new AtomicInteger();

    //FK many contacts to one account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    //FK One contact To Many accounts
//    @OneToMany(mappedBy = "decisionMaker", cascade = CascadeType.ALL)
//    private List<Opportunity> opportunity;



    //CONSTRUCTORS

    public Contact() {
    }

    public Contact(String name, int phoneNumber, String email) {
        this.id = createID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

        if (phoneNumber != contact.phoneNumber) return false;
        if (id != null ? !id.equals(contact.id) : contact.id != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        return email != null ? email.equals(contact.email) : contact.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + phoneNumber;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nContact " + id + ':' +
                "\nname:  " + name +
                "\nphoneNumber:  " + phoneNumber +
                "\nemail:  " + email
                ;
    }
}


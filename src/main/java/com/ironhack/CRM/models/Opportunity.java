package com.ironhack.CRM.models;


import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name = "opportunity")
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;

    //PK one opportunity has one contact
    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact decisionMaker;
    @Enumerated(EnumType.STRING)
    private Status status;
    private static AtomicInteger opportunityIdCounter = new AtomicInteger();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;

    //FK many opportunities have one account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;



    //CONSTRUCTOR


    public Opportunity() {
    }

    public Opportunity(Integer id, Product product, int quantity, Contact decisionMaker, Status status, Account account) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.account = account;
    }

    //SETTERS


    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSalesRep(SalesRep salesRep) {
        this.salesRep = salesRep;
    }

    //GETTERS


    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public Account getAccount() {
        return account;
    }

    public SalesRep getSalesRep() {
        return salesRep;
    }

    //METHODS
    public static String createID() {
        return String.valueOf(opportunityIdCounter.getAndIncrement() + 1);
    }


    //EQUALS, HASHCODE & ToString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Opportunity that = (Opportunity) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (product != that.product) return false;
        if (decisionMaker != null ? !decisionMaker.equals(that.decisionMaker) : that.decisionMaker != null)
            return false;
        if (status != that.status) return false;
        if (salesRep != null ? !salesRep.equals(that.salesRep) : that.salesRep != null) return false;
        return account != null ? account.equals(that.account) : that.account == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (decisionMaker != null ? decisionMaker.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (salesRep != null ? salesRep.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", decisionMaker=" + decisionMaker +
                ", status=" + status +
                ", salesRep=" + salesRep +
                ", account=" + account +
                '}';
    }
}

package com.ironhack.CRM.models;


import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
@Entity
@Table(name = "opportunity")
public class Opportunity {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private Product product;
    private int quantity;

    //Many opportunities To One contact
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Opportunity(Product product, int quantity, Contact decisionMaker, SalesRep salesRep) {
        this.id = createID();
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.setStatus(Status.OPEN);
        this.salesRep = salesRep;
    }


    //SETTERS

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


    //GETTERS


    public String getId() {
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

        if (quantity != that.quantity) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != that.product) return false;
        if (decisionMaker != null ? !decisionMaker.equals(that.decisionMaker) : that.decisionMaker != null)
            return false;
        if (status != that.status) return false;
        return salesRep != null ? salesRep.equals(that.salesRep) : that.salesRep == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (decisionMaker != null ? decisionMaker.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (salesRep != null ? salesRep.hashCode() : 0);
        return result;
    }


}

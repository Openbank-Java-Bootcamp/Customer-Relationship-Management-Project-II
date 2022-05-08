package com.ironhack.CRM.CRM;


import com.ironhack.CRM.ConsoleColors.ConsoleColors;
import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;
import com.ironhack.CRM.models.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CRM {



    public Map<String, Lead> leadList = new HashMap<>();

    private Map<String, Contact> contactList = new HashMap<>();
    private Map<String, Opportunity> opportunityList = new HashMap<>();
    private Map<String, Account> accountList = new HashMap<>();


    private static String menuOptions = "\n\nEnter " + ConsoleColors.BLUE + "NEW LEAD" + ConsoleColors.RESET + " to create a new Lead.\n" +
            "Enter " + ConsoleColors.BLUE + "SHOW LEADS" + ConsoleColors.RESET + " to see all Leads.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP LEAD <id>" + ConsoleColors.RESET + " to see a particular Lead.\n" +
            "Enter " + ConsoleColors.BLUE + "CONVERT <id>" + ConsoleColors.RESET + " to convert a Lead to an Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "SHOW OPPORTUNITIES" + ConsoleColors.RESET + " to see all Opportunities.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP OPPORTUNITY <id>" + ConsoleColors.RESET + " to see a particular Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP CONTACT <id>" + ConsoleColors.RESET + " to see a particular Contact.\n" +
            //"Enter LOOKUP ACCOUNT along with the Account ID to see a particular Account.\n" +
            "Enter " + ConsoleColors.BLUE + "CLOSE-WON <id>" + ConsoleColors.RESET + " to close an won Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "CLOSE-LOST <id>" + ConsoleColors.RESET + " to close a lost Opportunity.\n" +
            "Enter " + ConsoleColors.RED + "EXIT" + ConsoleColors.RESET + " to exit.\n";

    public CRM() {
    }

    public void verifyName(String name) {
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Only letters and spaces allowed");
        }
    }

    public void verifyPhone(String phone) {
        String regx = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Phone number must be 9 digits.");
        }
    }

    public void verifyEmail(String email) {
        String regx = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Not a valid email address.");
        }
    }

    public void verifyCompany(String company) {
        String regx = ".*\\S.*";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(company);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    public void createLead(Scanner scanner) {
        System.out.println("\nPlease provide the following information to create a Lead:");
        String leadName = null;
        String leadPhone = null;
        String leadEmail = null;
        String leadCompany = null;
        while (leadName == null) {
            try {
                System.out.print("Name: ");
                leadName = scanner.nextLine();
                verifyName(leadName);
            } catch (IllegalArgumentException e) {
                leadName = null;
                System.err.println("Only letters and spaces allowed");
            }
        }
        while (leadPhone == null) {
            try {
                System.out.print("Phone number: ");
                leadPhone = scanner.nextLine();
                verifyPhone(leadPhone);
            } catch (IllegalArgumentException e) {
                leadPhone = null;
                System.err.println("Phone number must be 9 digits.");
            }
        }
        int leadPhoneAsInt = Integer.parseInt(leadPhone);
        while (leadEmail == null) {
            try {
                System.out.print("Email: ");
                leadEmail = scanner.nextLine();
                verifyEmail(leadEmail);
            } catch (IllegalArgumentException e) {
                leadEmail = null;
                System.err.println("Not a valid email address.");
            }
        }
        while (leadCompany == null) {
            try {
                System.out.print("Company name: ");
                leadCompany = scanner.nextLine();
                verifyCompany(leadCompany);
            } catch (IllegalArgumentException e) {
                leadCompany = null;
                System.err.println("Invalid input");
            }
        }
        //SalesRep part
        Lead newLead = new Lead(leadName, leadPhoneAsInt, leadEmail, leadCompany, salesRep);
        leadList.put(newLead.getId(), newLead);
        System.out.println("\n\nLead created: ");
        System.out.println(newLead.toString());
    }

    public void showLeads() {
        try { for (Map.Entry<String, Lead> entry : leadList.entrySet()) {
            System.out.println(entry.getValue());
        }
        } catch (Exception e) {
            System.out.println("\n\nNo Leads to show\n\n");
        }
    }

    public Lead lookupLead(String userChoice) {
        String leadId = null;
        try {
            leadId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            System.out.println(leadList.get(leadId).toString());
        } catch (NullPointerException e) {
            System.err.println("Not a valid Lead Id.");
        }
        return leadList.get(leadId);
    }

    public Contact createContact(Lead lead){
        String contactName = lead.getName();
        int contactNumber = lead.getPhoneNumber();
        String contactEmail = lead.getEmail();
        Contact newContact = new Contact(contactName, contactNumber, contactEmail);
        contactList.put(newContact.getId(), newContact);
        leadList.remove(lead.getId());
        return newContact;
    }

    public void menu(Scanner scanner) {
        System.out.println(menuOptions);
        String userChoice = scanner.nextLine().toUpperCase();

        while (!userChoice.equals("QUIT")) {
            if (userChoice.contains("NEW LEAD")) {  //works
                createLead(scanner);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW LEADS")) {  //works
                showLeads();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP LEAD")) {  //works
                lookupLead(userChoice);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CONVERT")) {
                String leadId = userChoice.split(" ")[1];
                convertLead(scanner, leadId);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW OPPORTUNITIES")) {
                showOpportunities();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP OPPORTUNITY")) {
                lookupOpportunity(userChoice);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP CONTACT")) {
                lookupContact(userChoice);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-WON")) {
                String closeWonId = userChoice.split(" ")[1];
                try {
                    closeOpportunity(closeWonId, Status.CLOSED_WON);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid Opportunity ID");
                }
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("CLOSE-LOST")) {
                String closeLostId = userChoice.split(" ")[1];
                try {
                    closeOpportunity(closeLostId, Status.CLOSED_LOST);
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid Opportunity ID");
                }
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.equals("EXIT")) {
                break;
            } else {
                System.err.println("Invalid Entry\n");
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            }
        }
    }


    public void convertLead(Scanner scanner, String leadId) {
        try {
            Contact contact = typeOfContact(scanner, leadList.get(leadId));
            Product productType = typeOfProduct(scanner);
            int productQuantity = quantityOfProduct(scanner);
            //falta preguntar que SalesRep asignar a Opportunity
            Opportunity newOpportunity = createOpportunity(productType, productQuantity, contact, salesRep);
            System.out.println("New Opportunity created:\n" + newOpportunity.toString());
            Account account = typeOfAccount(scanner, contact, newOpportunity);
        } catch (Exception e) {
            System.err.println("Invalid lead, choose other command");
        }
    }

    public Contact typeOfContact(Scanner scanner, Lead lead) {
        System.out.println("Would you like to create a New Contact?" +
                "\nType (Y) - for yes" +
                "\nType (N) - for no"
        );
        Contact contact;
        String contactChoice = scanner.nextLine().toUpperCase();
        while (!contactChoice.equals("Y") || !contactChoice.equals("N")) {
            System.err.println("Invalid Entry");
            System.out.println("Type (Y) - for yes" +
                    "\nType (N) - for no");
            contactChoice = scanner.nextLine().toUpperCase();
        }
        if (contactChoice.equals("Y")) {
            return contact = createContact(lead);
        } else {
            System.out.println("Please type \"look up\" and contact's id which you want to assign");
            String userChoice = scanner.nextLine();
            while (!userChoice.contains("LOOKUP CONTACT")) {
                System.err.println("Invalid Entry");
                System.out.println("Please type \"look up\" and contact's id which you want to assign");
                userChoice = scanner.nextLine().toUpperCase();
            }
            return contact = lookupContact(userChoice);
        }
    }

    public Account typeOfAccount(Scanner scanner, Contact contact, Opportunity opportunity) {
        System.out.println("Would you like to create a New Account?" +
                "\nType (Y) - for yes" +
                "\nType (N) - for no"
        );
        Account account;
        String accountChoice = scanner.nextLine().toUpperCase();
        while (!accountChoice.equals("Y") || !accountChoice.equals("N")) {
            System.err.println("Invalid Entry");
            System.out.println("Type (Y) - for yes" +
                    "\nType (N) - for no");
            accountChoice = scanner.nextLine().toUpperCase();
        }
        if (accountChoice.equals("Y")) {
            return createAccount(scanner, contact, opportunity);
        } else {
            System.out.println("Please type \"look up\" and account's id which you want to assign");
            String userChoice = scanner.nextLine();
            while (!userChoice.contains("LOOKUP ACCOUNT")) {
                System.err.println("Invalid Entry");
                System.out.println("Please type \"look up\" and account's id which you want to assign");
                userChoice = scanner.nextLine().toUpperCase();
            }
            return account = lookupAccount(userChoice);
        }
    }


    public Product typeOfProduct(Scanner scanner){
        System.out.println(
                "What type of product:" +
                        "\nEnter (1) for the Hybrid" +
                        "\nEnter (2) for Flatbed" +
                        "\nEnter (3) for Box"
        );
        int productChoice = CRM.verifyIntInput(scanner, 1, 3);
        switch(productChoice){
            case 1:
                return Product.HYBRID;
            case 2:
                return Product.FLATBED;
            default:
                return Product.BOX;
        }
    }

    public int quantityOfProduct(Scanner scanner) {
        System.out.print("Product quantity: ");
        int quantity = verifyIntInput(scanner, 1, Integer.MAX_VALUE);
        return quantity;
    }
    public Industry typeOfIndustry(Scanner scanner){
        System.out.println(
                "\nIndustry type: " +
                        "\nEnter (1) for Produce" +
                        "\nEnter (2) for Ecommerce" +
                        "\nEnter (3) for Manufacturing"+
                        "\nEnter (4) for Medical"+
                        "\nEnter (5) for Other"
        );
        int productChoice = CRM.verifyIntInput(scanner, 1, 5);
        switch(productChoice){
            case 1:
                return Industry.PRODUCE;
            case 2:
                return Industry.ECOMMERCE;
            case 3:
                return Industry.MANUFACTURING;
            case 4:
                return Industry.MEDICAL;
            default:
                return Industry.OTHER;
        }
    }

    public Opportunity createOpportunity(Product productType, int productQuantity, Contact newContact, SalesRep salesRep){
        Opportunity newOpportunity = new Opportunity(productType, productQuantity, newContact, salesRep);
        opportunityList.put(newOpportunity.getId(), newOpportunity);
        return newOpportunity;
    }

    public Account createAccount(Scanner scanner, Contact newContact, Opportunity newOpportunity) throws IllegalArgumentException {
        if(!contactList.containsKey(newContact.getId())){
            throw new IllegalArgumentException("The Contact is not on the contacts list");
        } else if (!opportunityList.containsKey(newOpportunity.getId())){
            throw new IllegalArgumentException("The Opportunity is not on the opportunities list");
        }
        Industry industryType = typeOfIndustry(scanner);
        System.out.println("Please type the number of employees");
        int employeeCount = CRM.verifyIntInput(scanner, 1, Integer.MAX_VALUE);
        String city = null;
        while (city == null) {
            try {
                System.out.println("Please type Account's city");
                city = scanner.nextLine();
                verifyCityOrCountry(city);
            } catch (IllegalArgumentException e) {
                city = null;
                System.err.println("Only letters and spaces allowed");
            }
        }
        String country = null;
        while (country == null) {
            try {
                System.out.println("Please type Account's country");
                country = scanner.nextLine();
                verifyCityOrCountry(country);
            } catch (IllegalArgumentException e) {
                country = null;
                System.err.println("Only letters and spaces allowed");
            }
        }
        HashMap<String, Contact> newContactList = new HashMap<>();
        newContactList.put(newContact.getId(), newContact);
        HashMap<String, Opportunity> newOpportunityList = new HashMap<>();
        newOpportunityList.put(newOpportunity.getId(), newOpportunity);
        Account newAccount = new Account(industryType, employeeCount, city, country, newContactList, newOpportunityList);
        accountList.put(newAccount.getId(),newAccount);
        return newAccount;
    }

    public void verifyCityOrCountry(String name) {
        String regx = "[a-zA-Z]+\\.?";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Only letters and spaces allowed");
        }
    }


    public void showOpportunities() {
        try { for (Map.Entry<String, Opportunity> entry : opportunityList.entrySet()) {
            System.out.println(entry.getValue());
        }
        } catch (Exception e) {
            System.out.println("\n\nNo Opportunities to show\n\n");
        }
    }

    public Opportunity lookupOpportunity(String userChoice) {
        String opportunityId = null;
        try {
            opportunityId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            System.out.println(opportunityList.get(opportunityId).toString());
        } catch (NullPointerException e) {
            System.err.println("Not a valid Opportunity Id.");
        }
        return opportunityList.get(opportunityId);
    }

    public void showContacts() {
        try { for (Map.Entry<String, Contact> entry : contactList.entrySet()) {
            System.out.println(entry.getValue());
        }
        } catch (Exception e) {
            System.out.println("\n\nNo Contacts to show\n\n");
        }
    }

    public Contact lookupContact(String userChoice) {
        String contactId = null;
        try {
            contactId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            System.out.println(contactList.get(contactId).toString());
        } catch (NullPointerException e) {
            System.err.println("Not a valid Contact Id.");
        }
        return contactList.get(contactId);
    }

    public Account lookupAccount(String userChoice) {
        String accountId = null;
        try {
            accountId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        try {
            System.out.println(accountList.get(accountId).toString());
        } catch (NullPointerException e) {
            System.err.println("Not a valid Account Id.");
        }
        return accountList.get(accountId);
    }

    public void showAccounts() {
        try { for (Map.Entry<String, Account> entry : accountList.entrySet()) {
            System.out.println(entry.getValue());
        }
        } catch (Exception e) {
            System.out.println("\n\nNo Accounts to show\n\n");
        }
    }

    public void closeOpportunity(String id, Status status){
        if (opportunityList.get(id).getProduct() == null) {
            throw new IllegalArgumentException("Not a valid Opportunity ID");
        }
        opportunityList.get(id).setStatus(status);
        System.out.println("Status updated\n");
        System.out.println(opportunityList.get(id));
    }

    public static int verifyIntInput(Scanner scanner, int min, int max) {
        boolean flag;
        int num = -1;
        String input;
        do { input = scanner.next();
            try {
                Integer.parseInt(input);
                flag = false;
            } catch (NumberFormatException e) {
                System.err.println("Enter a number " + min + "-" + max);
                flag = true;
            }
            if (!flag) {
                num = Integer.parseInt(input);
                if (num > max || num < min) {
                    System.err.println("Enter a number " + min + "-" + max);
                    flag = true;
                }
            }
        } while (flag);
        return num;
    }


}
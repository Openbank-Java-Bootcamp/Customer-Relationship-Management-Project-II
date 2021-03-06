package com.ironhack.CRM.CRM;

import com.ironhack.CRM.ConsoleColors.ConsoleColors;
import com.ironhack.CRM.CrmApplication;
import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;
import com.ironhack.CRM.models.*;
import com.ironhack.CRM.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CRM {


    @Autowired
    private LeadRepository leadRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private ContactRepository contactRepository;

    public Map<String, Lead> leadList = new HashMap<>();

    private Map<String, Contact> contactList = new HashMap<>();
    private Map<String, Opportunity> opportunityList = new HashMap<>();
    private Map<String, Account> accountList = new HashMap<>();
    public Map<String, SalesRep> salesRepList = new HashMap<>();


    private static String menuOptions = "\n\nEnter " + ConsoleColors.BLUE + "NEW SALESREP" + ConsoleColors.RESET + " to create a new Sales Rep.\n" +
            "Enter " + ConsoleColors.BLUE + "SHOW SALESREPS" + ConsoleColors.RESET + " to see all Sales Reps.\n" +
            "Enter " + ConsoleColors.BLUE + "NEW LEAD" + ConsoleColors.RESET + " to create a new Lead.\n" +
            "Enter " + ConsoleColors.BLUE + "SHOW LEADS" + ConsoleColors.RESET + " to see all Leads.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP LEAD <id>" + ConsoleColors.RESET + " to see a particular Lead.\n" +
            "Enter " + ConsoleColors.BLUE + "CONVERT <id>" + ConsoleColors.RESET + " to convert a Lead to an Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "SHOW OPPORTUNITIES" + ConsoleColors.RESET + " to see all Opportunities.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP OPPORTUNITY <id>" + ConsoleColors.RESET + " to see a particular Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "LOOKUP CONTACT <id>" + ConsoleColors.RESET + " to see a particular Contact.\n" +
            //"Enter LOOKUP ACCOUNT along with the Account ID to see a particular Account.\n" +
            "Enter " + ConsoleColors.BLUE + "CLOSE-WON <id>" + ConsoleColors.RESET + " to close an won Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "CLOSE-LOST <id>" + ConsoleColors.RESET + " to close a lost Opportunity.\n" +
            "Enter " + ConsoleColors.BLUE + "REPORTS" + ConsoleColors.RESET + " to get to Reports menu.\n" +
            "Enter " + ConsoleColors.RED + "EXIT" + ConsoleColors.RESET + " to exit.\n";


    private List<String> bySalesRep = List.of("(1) Report Lead by SalesRep", "(2) Report Opportunity by SalesRep", "(3) Report CLOSED-WON by SalesRep", "(4) Report CLOSED-LOST by SalesRep", "(5) Report OPEN by SalesRep");
    private List<String> byProduct = List.of("(1) Report Opportunity by the product", "(2) Report CLOSED-WON by the product", "(3) Report CLOSED-LOST by the product", "(4) Report OPEN by the product");
    private List<String> byCountry = List.of("(1) Report Opportunity by Country", "(2) Report CLOSED-WON by Country", "(3) Report CLOSED-LOST by Country", "(4) Report OPEN by Country");
    private List<String> byCity = List.of("(1) Report Opportunity by City", "(2) Report CLOSED-WON by City", "(3) Report CLOSED-LOST by City", "(4) Report OPEN by City");
    private List<String> byIndustry = List.of("(1) Report Opportunity by Industry", "(2) Report CLOSED-WON by Industry", "(3) Report CLOSED-LOST by Industry", "(4) Report OPEN by Industry");
    private List<String> byEmployeeCountStates = List.of("(1) Mean EmployeeCount", "(2) Median EmployeeCount", "(3) Max EmployeeCount", "(4) Min EmployeeCount");
    private List<String> byQuantityStates = List.of("(1) Mean Quantity", "(2) Median Quantity", "(3) Max Quantity", "(4) Min Quantity");
    private List<String> byOpportunityStates = List.of("(1) Mean Opps per Account", "(2) Median Opps per Account", "(3) Max Opps per Account", "(4) Min Opps per Account");

    private String reports = "Choose a report by next options: " +
            "\n (1) BY SALES REP " +
            "\n (2) BY PRODUCT" +
            "\n (3) BY COUNTRY" +
            "\n (4) BY CITY" +
            "\n (5) BY INDUSTRY" +
            "\n (6) BY EMPLOYEE COUNT STATES" +
            "\n (7) BY QUANTITY STATES" +
            "\n (8) BY OPPORTUNITY STATES" +
            "\n (9) BACK";


    public CRM() {
    }

    public void verifyIdIsInt(String input) {
        String regx = "^[0-9]*$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Id is digits only.");
        }
    }
    public void verifyName(String name) {
        //String regx = "^[a-zA-Z]+\\.?$";
        ///^[a-z ,.'-]+$/i
        String regx = "^[a-zA-Z][a-z ,.'-]+$";
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
    public void verifySalesRep(String salesId) {
        String regx = "[0-9]+"; //Only numbers?
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(salesId);
        if(!matcher.find()) {
            throw new IllegalArgumentException("Enter a valid number");
        }
    }

    public void createLead(Scanner scanner) {
        System.out.println("\nPlease provide the following information to create a Lead:");
        String leadName = null;
        String leadPhone = null;
        String leadEmail = null;
        String leadCompany = null;
        SalesRep salesRep = null;

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
        while (salesRep == null) {
            try {
                salesRep = chooseSalesRep(scanner);
            } catch (Exception e) {
                System.err.print("Invalid Sales Rep id.");
            }
        }
        Lead newLead = new Lead(leadName, leadPhoneAsInt, leadEmail, leadCompany, salesRep);
        salesRep.addLeadToList(newLead);
        leadList.put(newLead.getId(), newLead);
        leadRepository.save(newLead);
        System.out.println("\n\nLead created: ");
        System.out.println(newLead.toString());
    }


    public SalesRep chooseSalesRep(Scanner scanner) throws NoSuchElementException {
        System.out.print("Sales Rep id:  ");
        String salesRepId = scanner.nextLine();
        if (!salesRepList.containsKey(salesRepId)) {
            throw new NoSuchElementException("Invalid Sales Rep Id. Please enter a valid Id: ");
        }
        SalesRep salesRep = salesRepList.get(salesRepId);
        return salesRep;
    }

    public void showSalesReps() {
        List<SalesRep> salesReps = new ArrayList<>();
        try {
            salesReps =  salesRepRepository.findAll();
        } catch (Exception e) {
            System.out.println("\n\nNo SalesReps to show\n\n");
        }
        if (salesReps == null || salesReps.size() == 0) {
            System.out.println("\n\nNo SalesReps to show\n\n");
        } else {
            for (SalesRep salesRep : salesReps) {
                System.out.println(salesRep.toString());
            }
        }
    }

    public void showLeads() {
        List<Lead> leads = new ArrayList<>();
        try {
            leads = leadRepository.findAll();
        } catch (Exception e) {
            System.out.println("\n\nNo Leads to show\n\n");
        }
        if (leads == null || leads.size() == 0) {
            System.out.println("\n\nNo Leads to show\n\n");
        } else {
            for (Lead lead : leads) {
                System.out.println(lead.toString());
            }
        }
    }

    public Lead lookupLead(String userChoice) {
        String leadId = null;
        try {
            leadId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Not a valid Lead Id.");
        }
        Lead foundLead = null;
        try {
            foundLead = leadRepository.findById(leadId).get();
            if (foundLead == null) {
                System.out.println(foundLead.toString());
            } else {
                System.err.println("Not a valid Lead Id.");
            }
        } catch (Exception e) {
            System.err.println("Not a valid Lead Id.");
        }
        return foundLead;
    }

    public Contact createContact(Lead lead){
        String contactName = lead.getName();
        int contactNumber = lead.getPhoneNumber();
        String contactEmail = lead.getEmail();
        Contact newContact = new Contact(contactName, contactNumber, contactEmail);
        contactList.put(newContact.getId(), newContact);
        contactRepository.save(newContact);
        leadList.remove(lead.getId());
        return newContact;
    }

    public SalesRep createSalesRep(Scanner scanner) {
        String name = null;
        while (name == null) {
            try {
                System.out.print("\nSales Rep's name:  ");
                name = scanner.nextLine();
                verifyName(name);
                SalesRep newSalesRep = new SalesRep(name);
                salesRepList.put(newSalesRep.getId(), newSalesRep);
                salesRepRepository.save(newSalesRep);
                System.out.println("\n\nSalesRep created:\n" + newSalesRep.toString());
                return newSalesRep;
            } catch (IllegalArgumentException e) {
                name = null;
                System.err.println("Invalid name.");
            }
        }
        return null;
    }

    public void menu(Scanner scanner) {
        System.out.println(menuOptions);
        String userChoice = scanner.nextLine().toUpperCase();

        while (!userChoice.equals("QUIT")) {
            if (userChoice.contains("NEW SALESREP")) {
                createSalesRep(scanner);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW SALESREPS")) {
                showSalesReps();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            }
            else if (userChoice.contains("NEW LEAD")) {
                createLead(scanner);
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("SHOW LEADS")) {
                showLeads();
                System.out.println(menuOptions);
                userChoice = scanner.nextLine().toUpperCase();
            } else if (userChoice.contains("LOOKUP LEAD")) {
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
            }else if (userChoice.equals("REPORTS")){
                chooseReportList(scanner);
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
            Lead lead= leadList.get(leadId);
            Contact contact = typeOfContact(scanner, leadList.get(leadId));
            Product productType = typeOfProduct(scanner);
            int productQuantity = quantityOfProduct(scanner);
            SalesRep salesRep = chooseSalesRep(scanner);
            Opportunity newOpportunity = createOpportunity(productType, productQuantity, contact, salesRep);
            System.out.println("New Opportunity created:\n" + newOpportunity.toString());
            Account account = typeOfAccount(scanner, contact, newOpportunity);
            accountRepository.save(account);
            newOpportunity.setAccount(account);
            opportunityRepository.save(newOpportunity);
            leadRepository.delete(lead);
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
        while (!contactChoice.equals("Y") && !contactChoice.equals("N")) {
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
        while (!accountChoice.equals("Y") && !accountChoice.equals("N")) {
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
        int productChoice = verifyIntInput(scanner, 1, 3);
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
        int productChoice = verifyIntInput(scanner, 1, 5);
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
        int employeeCount = verifyIntInput(scanner, 1, Integer.MAX_VALUE);
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
//        try { for (Map.Entry<String, Opportunity> entry : opportunityList.entrySet()) {
//            System.out.println(entry.getValue());
//        }
//        } catch (Exception e) {
//            System.out.println("\n\nNo Opportunities to show\n\n");
//        }

        List<Opportunity> opportunities = new ArrayList<>();
        try {
            opportunities =  opportunityRepository.findAll();
        } catch (Exception e) {
            System.out.println("\n\nNo Opportunities to show\n\n");
        }
        if (opportunities == null || opportunities.size() == 0) {
            System.out.println("\n\nNo Opportunities to show\n\n");
        } else {
            for (Opportunity opportunity : opportunities) {
                System.out.println(opportunity.toString());
            }
        }
    }

    public Opportunity lookupOpportunity(String userChoice) {
        String opportunityId = null;
        try {
            opportunityId = userChoice.split(" ")[2];
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        Opportunity foundOpportunity = opportunityRepository.findById(opportunityId).get();
        try {
            System.out.println(foundOpportunity.toString());
        } catch (Exception e) {
            System.err.println("Not a valid Opportunity Id.");
        }
        return foundOpportunity;

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
        String contactId = userChoice.split(" ")[2];
        Contact foundContact = null;
        if (contactId.equals(null)) {
            System.err.println("Need a Contact ID");
            return null;
        } else {
            try {
                verifyIdIsInt(contactId);
                foundContact = contactRepository.findById(contactId).get();
                if (foundContact.getName() != null) {
                    System.out.println(foundContact.toString());
                } else {
                    System.err.println("Not a valid Contact Id.");
                }
            } catch (Exception e) {
                System.err.println("Not a valid Contact Id.");
            }
            return foundContact;
        }
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
        if (opportunityRepository.findById(id).get() == null) {
            throw new IllegalArgumentException("Not a valid Opportunity ID");
        }
        Opportunity opportunity = opportunityRepository.findById(id).get();
        opportunity.setStatus(status);
        opportunityRepository.save(opportunity);
        System.out.println("Status updated\n");
        System.out.println(opportunity);
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

    public void chooseReportList(Scanner scanner) {
        List<Object[]> result = new ArrayList<>();
        System.out.println(reports);
        int scannerNum;
        int reportChoice = verifyIntInput(scanner, 1, 9);
        switch (reportChoice) {
            case 1:
                for (String each : bySalesRep) {
                    System.out.println(each);}
                scannerNum = verifyIntInput(scanner, 1, 5);
                switch (scannerNum){
                    case 1:
                        result = leadRepository.findCountGroupBySalesRep();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 2:
                        result = opportunityRepository.findCountGroupBySalesRep();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 3:
                        result = opportunityRepository.findCountWithStatusWonGroupBySalesRep();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 4:
                        result = opportunityRepository.findCountWithStatusLostGroupBySalesRep();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 5:
                        result = opportunityRepository.findCountWithStatusOpenGroupBySalesRep();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                }
                break;
            case 2:
                for (String each : byProduct) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        result = opportunityRepository.findCountGroupByProduct();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 2:
                        result = opportunityRepository.findCountWithStatusWonGroupByProduct();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 3:
                        result = opportunityRepository.findCountWithStatusLostGroupByProduct();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 4:
                        result = opportunityRepository.findCountWithStatusOpenGroupByProduct();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;

                }
                break;
            case 3:
                for (String each : byCountry) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        result = opportunityRepository.findCountGroupByCountry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 2:
                        result = opportunityRepository.findCountWithStatusWonGroupByCountry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 3:
                        result = opportunityRepository.findCountWithStatusLostGroupByCountry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        System.out.println(opportunityRepository.findCountWithStatusLostGroupByCountry().toString());
                        break;
                    case 4:
                        result = opportunityRepository.findCountWithStatusOpenGroupByCountry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                }
                break;
            case 4:
                for (String each : byCity) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        result = opportunityRepository.findCountGroupByCity();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 2:
                        result = opportunityRepository.findCountWithStatusWonGroupByCity();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 3:
                        result = opportunityRepository.findCountWithStatusLostGroupByCity();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 4:
                        result = opportunityRepository.findCountWithStatusOpenGroupByCity();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                }
                break;
            case 5:
                for (String each : byIndustry) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        result = opportunityRepository.findCountGroupByIndustry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 2:
                        result = opportunityRepository.findCountWithStatusWonGroupByIndustry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 3:
                        result = opportunityRepository.findCountWithStatusLostGroupByIndustry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                    case 4:
                        result = opportunityRepository.findCountWithStatusOpenGroupByIndustry();
                        for (int i=0; i<result.size(); i++) {
                            System.out.println(Arrays.toString(result.get(i)));
                        }
                        break;
                }
                break;
            case 6:
                for (String each : byEmployeeCountStates) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        System.err.println("No Accounts");
                        break;
                    case 2:
                        List<Integer> employeeCounts = accountRepository.findEmployeeCounts();
                        Collections.sort(employeeCounts);
                        if (employeeCounts.size() % 2 == 1)
                            System.out.println(employeeCounts.get((employeeCounts.size() + 1) / 2 - 1));
                        else {
                            double lower = employeeCounts.get(employeeCounts.size() / 2 - 1);
                            double upper = employeeCounts.get(employeeCounts.size() / 2);
                            System.out.println((lower + upper) / 2.0);
                        }
                        break;
                    case 3:
                        System.out.println(accountRepository.findMaxEmployeeCount());
                        break;
                    case 4:
                        System.out.println(accountRepository.findMinEmployeeCount());
                        break;
                }
                break;
            case 7:
                for (String each : byQuantityStates) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                switch (scannerNum){
                    case 1:
                        System.out.println(opportunityRepository.findMeanProductCount());
                        break;
                    case 2:
                        System.out.println(opportunityRepository.findMedianProductCount());
                        break;
                    case 3:
                        System.out.println(opportunityRepository.findMaxEmployeeCount());
                        break;
                    case 4:
                        System.out.println(opportunityRepository.findMinProductCount());

                        break;
                }
                break;
            case 8:
                for (String each : byOpportunityStates) {
                    System.out.println(each);
                }
                scannerNum = verifyIntInput(scanner, 1, 4);
                List<Integer> list = new ArrayList<>();
                List<Object[]> objectArray = opportunityRepository.countMeanOfOpportunitiesAssociatedToAccount();
                for(int i = 0; i < opportunityRepository.countMeanOfOpportunitiesAssociatedToAccount().size(); i++){
                    list.add((Integer) objectArray.get(i)[1]);
                }
                switch (scannerNum){
                    case 1:
                        Integer sum = 0;
                        for(int i = 0; i < list.size(); i++){
                            sum += list.get(i);
                        }
                        Integer mean = sum / list.size();
                        System.out.println(mean);
                        break;
                    case 2:
                        Collections.sort(list);
                        if (list.size() % 2 == 1)
                            System.out.println(list.get((list.size() + 1) / 2 - 1));
                        else {
                            double lower = list.get(list.size() / 2 - 1);
                            double upper = list.get(list.size() / 2);
                            System.out.println((lower + upper) / 2.0);
                        }
                        break;
                    case 3:
                        Collections.sort(list);
                        System.out.println(list.get(list.size()-1));
                        break;
                    case 4:
                        Collections.sort(list);
                        System.out.println(list.get(0));
                        break;
                }
                break;
        }
    }
}

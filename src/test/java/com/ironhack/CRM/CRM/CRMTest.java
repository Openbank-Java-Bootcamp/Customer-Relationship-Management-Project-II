//package com.ironhack.CRM.CRM;
//
//import com.ironhack.CRM.enums.Industry;
//import com.ironhack.CRM.enums.Product;
//import com.ironhack.CRM.enums.Status;
//import com.ironhack.CRM.models.Contact;
//import com.ironhack.CRM.models.Lead;
//import com.ironhack.CRM.models.Opportunity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.StringReader;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CRMTest {
//    private Lead lead1;
//    private Lead emptyLead;
//    private CRM testCrm;
//    private Contact contact1;
//    private Opportunity opportunity1;
//
//
//    @BeforeEach
//    public void setUp() {
//        testCrm = new CRM();
//        lead1 = new Lead("John Smith", 123456789, "jsmith@example.com", "Company A");
//        testCrm.leadList.put(lead1.getId(), lead1);
//        emptyLead = new Lead();
//    }
//
//    @Test
//    void lookupLead_ValidId_Works() {
//        assertEquals(lead1, testCrm.lookupLead("Lookup lead 1"));
//    }
//    @Test
//    public void lookupLead_invalidID_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead 999"));
//        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead -78"));
//        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead word"));
//    }
//    @Test
//    public void lookupLead_NoId_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupLead("lookup lead"));
//    }
//    @Test
//    void lookupContact_ValidId_Works() {
//        contact1 = testCrm.createContact(lead1);
//        assertEquals(contact1, testCrm.lookupContact("Lookup Contact 1"));
//    }
//
//    @Test
//    public void lookupContact_invalidID_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupContact("lookup contact 999"));
//        assertDoesNotThrow(() -> testCrm.lookupContact("lookup contact -78"));
//        assertDoesNotThrow(() -> testCrm.lookupContact("lookup contact word"));
//    }
//
//    @Test
//    public void lookupContact_NoId_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupContact("lookup contact"));
//    }
//
//    @Test
//    void lookupOpportunity_ValidId_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        assertEquals(opportunity1, testCrm.lookupOpportunity("Lookup opportunity 1"));
//    }
//
//    @Test
//    public void lookupOpportunity_invalidID_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupOpportunity("lookup Opportunity 999"));
//        assertDoesNotThrow(() -> testCrm.lookupOpportunity("lookup opportunity -78"));
//        assertDoesNotThrow(() -> testCrm.lookupOpportunity("lookup opportunity word"));
//    }
//
//    @Test
//    public void lookupOpportunity_NoId_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupOpportunity("lookup Opportunity"));
//    }
//
//    @Test
//    public void lookupAccount_invalidID_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupAccount("lookup account 999"));
//        assertDoesNotThrow(() -> testCrm.lookupAccount("lookup Account -78"));
//        assertDoesNotThrow(() -> testCrm.lookupAccount("lookup account word"));
//    }
//
//    @Test
//    public void lookupAccount_NoId_NoThrow() {
//        assertDoesNotThrow(() -> testCrm.lookupAccount("lookup account"));
//    }
//
//
//    @Test
//    public void createContact_ValidLead_Works() {
//        String lead1ID = lead1.getId();
//        contact1 = testCrm.createContact(lead1);
//        //info matches
//        assertEquals("John Smith", contact1.getName());
//        assertEquals(123456789, contact1.getPhoneNumber());
//        assertEquals("jsmith@example.com", contact1.getEmail());
//        //lead deleted
//        assertEquals(null, testCrm.lookupLead(lead1ID));
//    }
//
//    @Test
//    public void createContact_InvalidLead_IllegalArgumentException() {
//        assertDoesNotThrow(() -> testCrm.createContact(emptyLead));
//    }
//
//    @Test
//    public void typeOfProduct_goodData_Works(){
//        Scanner testScan = new Scanner(new StringReader("1"));
//        assertEquals(Product.HYBRID, testCrm.typeOfProduct(testScan));
//        testScan.close();
//    }
//
//    @Test
//    public void typeOfProduct_emptyProductChoice_IllegalArgumentException(){
//        Scanner testScan1 = new Scanner(new StringReader(" "));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan1));
//        testScan1.close();
//    }
//
//    @Test
//    public void typeOfProduct_invalidProductChoice_IllegalArgumentException(){
//        Scanner testScan1 = new Scanner(new StringReader("5"));
//        Scanner testScan2 = new Scanner(new StringReader("-5"));
//        Scanner testScan3 = new Scanner(new StringReader("0"));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan1));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan2));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfProduct(testScan3));
//        testScan1.close();
//        testScan2.close();
//        testScan3.close();
//    }
//
//    @Test
//    public void typeOfIndustry_goodData_Works(){
//        Scanner testScan = new Scanner(new StringReader("3"));
//        assertEquals(Industry.MANUFACTURING, testCrm.typeOfIndustry(testScan));
//        testScan.close();
//
//    }
//
//    @Test
//    public void typeOfIndustry_emptyProductChoice_IllegalArgumentException(){
//        Scanner testScan1 = new Scanner(new StringReader(" "));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan1));
//        testScan1.close();
//
//    }
//
//    @Test
//    public void typeOfIndustry_invalidProductChoice_IllegalArgumentException(){
//        Scanner testScan1 = new Scanner(new StringReader("10"));
//        Scanner testScan2 = new Scanner(new StringReader("-10"));
//        Scanner testScan3 = new Scanner(new StringReader("0"));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan1));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan2));
//        assertThrows(java.util.NoSuchElementException.class, () -> testCrm.typeOfIndustry(testScan3));
//        testScan1.close();
//        testScan2.close();
//        testScan3.close();
//    }
//
//    @Test
//    public void createOpportunity_productAndContactPassed_Works(){
//        Contact tryContact = new Contact("Ana", 123456789, "aaa@aaa.aa");
//        Product tryProduct = Product.HYBRID;
//        int tryQuantity = 46;
//        Opportunity testOpportunity = testCrm.createOpportunity(tryProduct, tryQuantity, tryContact);
//        assertEquals(Status.OPEN, testOpportunity.getStatus());
//    }
//
//    @Test
//    public void verifyName_Strings_NoThrow() {
//        assertDoesNotThrow(()-> testCrm.verifyName("A Very Long Name With-Hyphen"));
//    }
//
//    @Test
//    public void verifyName_InvalidInput_Throws() {
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName(""));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName(" "));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyName("4"));
//    }
//
//    @Test
//    public void verifyPhone_ValidNumber_NoThrow() {
//        assertDoesNotThrow(()-> testCrm.verifyPhone("123456789"));
//        assertDoesNotThrow(()-> testCrm.verifyPhone("999999999"));
//    }
//
//    @Test
//    public void verifyPhone_InvalidInput_Throws() {
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone(""));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("9"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("12345678"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyPhone("12345678910"));
//    }
//
//    @Test
//    public void verifyEmail_ValidEmail_Works() {
//        assertDoesNotThrow(()-> testCrm.verifyEmail("fake@mail.com"));
//        assertDoesNotThrow(()-> testCrm.verifyEmail("another.dummy.address@yahoo.com"));
//        assertDoesNotThrow(()-> testCrm.verifyEmail("h328h48h4@school.edu"));
//    }
//
//    @Test
//    public void verifyEmail_InvalidInput_Throws() {
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("@gmail.com"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("email"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("email@"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail("4hwkej32"));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyEmail(""));
//    }
//
//    @Test
//    public void verifyCompany_ValidInput_NoThrow() {
//        assertDoesNotThrow(()-> testCrm.verifyCompany("Random Company"));
//        assertDoesNotThrow(()-> testCrm.verifyCompany("3M"));
//        assertDoesNotThrow(()-> testCrm.verifyCompany("Yahoo!"));
//        assertDoesNotThrow(()-> testCrm.verifyCompany("SK-II"));
//    }
//
//    @Test
//    public void verifyCompany_Null_Throws() {
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyCompany(""));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.verifyCompany(" "));
//    }
//
//    @Test
//    public void closeOpportunity_ValidIdWon_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        String id = opportunity1.getId();
//        testCrm.closeOpportunity(id, Status.CLOSED_WON);
//        assertEquals(Status.CLOSED_WON, opportunity1.getStatus());
//    }
//
//    @Test
//    public void closeOpportunity_ValidIdLost_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        String id = opportunity1.getId();
//        testCrm.closeOpportunity(id, Status.CLOSED_LOST);
//        assertEquals(Status.CLOSED_LOST, opportunity1.getStatus());
//    }
//
//    @Test
//    public void closeOpportunity_InvalidId_Throws() {
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.closeOpportunity("0", Status.CLOSED_WON));
//        assertThrows(IllegalArgumentException.class, ()-> testCrm.closeOpportunity("", Status.CLOSED_LOST));
//    }
//
//    @Test
//    public void showOpportunities_NonEmptyList_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        assertDoesNotThrow(()->testCrm.showOpportunities());
//    }
//
//    @Test
//    public void showOpportunities_EmptyList_NoThrow() {
//        assertDoesNotThrow(()->testCrm.showOpportunities());
//    }
//
//    @Test
//    public void showLeads_NonEmptyList_Works() {
//        assertDoesNotThrow(()->testCrm.showLeads());
//    }
//
//    @Test
//    public void showLeads_EmptyList_NoThrow() {
//        testCrm.leadList.remove("1");
//        assertDoesNotThrow(()->testCrm.showLeads());
//    }
//
//    @Test
//    public void showContacts_NonEmptyList_Works() {
//        contact1 = testCrm.createContact(lead1);
//        assertDoesNotThrow(()->testCrm.showContacts());
//    }
//
//    @Test
//    public void showContacts_EmptyList_NoThrow() {
//        assertDoesNotThrow(()->testCrm.showContacts());
//    }
//
//    @Test
//    public void showAccounts_NonEmptyList_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        assertDoesNotThrow(()->testCrm.showAccounts());
//    }
//
//    @Test
//    public void showAccounts_EmptyList_NoThrow() {
//        assertDoesNotThrow(()->testCrm.showContacts());
//    }
//
//    @Test
//    public void menu_LookupLead_Works() {
//        Scanner testScan = new Scanner(new StringReader("Lookup Lead 1"));
//        //prompts for input of lead info
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_NewLead_Works() {
//        Scanner testScan = new Scanner(new StringReader("new lead"));
//        //displays lead info, then asks for next input, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_ShowLeads_Works() {
//        Scanner testScan = new Scanner(new StringReader("show leads"));
//        //displays leads, then asks for next input, exception due to needing input for next menu option
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_ConvertLead_Works() {
//        Scanner testScan = new Scanner(new StringReader("convert 1"));
//        //starts method to make opportunity, then asks for next input, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_ShowOpportunities_Works() {
//        Scanner testScan = new Scanner(new StringReader("show opportunities"));
//        //displays opportunities, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_LookupContact_Works() {
//        contact1 = testCrm.createContact(lead1);
//        Scanner testScan = new Scanner(new StringReader("lookup contact"));
//        //displays opportunities, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_CloseWon_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        Scanner testScan = new Scanner(new StringReader("close-won 1"));
//        //displays opportunities, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_CloseLost_Works() {
//        contact1 = testCrm.createContact(lead1);
//        opportunity1 = testCrm.createOpportunity(Product.BOX, 23, contact1);
//        Scanner testScan = new Scanner(new StringReader("close-lost 1"));
//        //displays opportunities, exception due to needing input for next step
//        assertThrows(NoSuchElementException.class, ()->testCrm.menu(testScan));
//    }
//
//    @Test
//    public void menu_Exit_Works() {
//        Scanner testScan = new Scanner(new StringReader("exit"));
//        assertDoesNotThrow(()->testCrm.menu(testScan));
//    }
//
//}
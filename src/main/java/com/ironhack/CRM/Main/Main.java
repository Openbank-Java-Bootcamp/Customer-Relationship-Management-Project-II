package com.ironhack.CRM.Main;

import com.ironhack.CRM.CRM.CRM;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        CRM crm = new CRM();
        crm.menu(scanner);

        scanner.close();



    }

}

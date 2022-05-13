package com.ironhack.CRM.Main;

import com.ironhack.CRM.CRM.CRM2;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        CRM2 crm = new CRM2();
        crm.menu(scanner);

        scanner.close();



    }

}

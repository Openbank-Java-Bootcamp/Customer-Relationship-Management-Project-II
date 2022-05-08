package com.ironhack.CRM.Main;

import com.ironhack.CRM.CRM.CRM;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        CRM crm = new CRM();
        crm.menu(scanner);

        scanner.close();


    }

}

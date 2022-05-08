package com.ironhack.CRM;

import com.ironhack.CRM.CRM.CRM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrmApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		CRM crm = new CRM();
		crm.menu(scanner);

		scanner.close();
	}

}

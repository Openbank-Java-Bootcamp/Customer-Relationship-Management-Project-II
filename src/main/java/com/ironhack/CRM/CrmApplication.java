package com.ironhack.CRM;


import com.ironhack.CRM.CRM.CRM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.*;

@SpringBootApplication
public class CrmApplication implements CommandLineRunner{

	@Autowired
	private Environment environment;
	@Autowired
	CRM crm;

	public static void main(String[] args) {

		SpringApplication.run(CrmApplication.class, args);
	}

	@Override
	public void run(String... args) {

		if (!Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			Scanner scanner = new Scanner(System.in);

			crm.menu(scanner);


			scanner.close();
		}
	}






}

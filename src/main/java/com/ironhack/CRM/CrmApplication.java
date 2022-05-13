package com.ironhack.CRM;


import com.ironhack.CRM.CRM.CRM2;
import com.ironhack.CRM.ConsoleColors.ConsoleColors;
import com.ironhack.CRM.enums.Industry;
import com.ironhack.CRM.enums.Product;
import com.ironhack.CRM.enums.Status;
import com.ironhack.CRM.models.*;
import com.ironhack.CRM.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class CrmApplication implements CommandLineRunner{

	@Autowired
	private Environment environment;
	@Autowired
	CRM2 crm;

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

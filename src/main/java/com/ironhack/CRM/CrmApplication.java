package com.ironhack.CRM;

import com.ironhack.CRM.CRM.CRM;
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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
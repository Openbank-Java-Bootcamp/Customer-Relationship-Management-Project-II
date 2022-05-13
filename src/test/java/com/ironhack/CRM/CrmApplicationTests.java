package com.ironhack.CRM;

import com.ironhack.CRM.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
/*@ComponentScan("com.ironhack.CRM.repositories")*/
class CrmApplicationTests {

/*	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	private OpportunityRepository opportunityRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private SalesRepRepository salesRepRepository;
	@Autowired
	private ContactRepository contactRepository;*/

	@Test
	void contextLoads() {
	}

}

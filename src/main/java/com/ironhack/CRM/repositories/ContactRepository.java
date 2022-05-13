package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface ContactRepository extends JpaRepository<Contact, String> {

}

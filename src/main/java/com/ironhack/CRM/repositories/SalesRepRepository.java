package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SalesRepRepository extends JpaRepository<SalesRep, String> {



}

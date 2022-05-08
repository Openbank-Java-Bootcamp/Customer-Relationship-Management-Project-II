package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, String> {
}

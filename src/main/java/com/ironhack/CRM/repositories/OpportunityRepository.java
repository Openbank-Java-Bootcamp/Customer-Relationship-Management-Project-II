package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, String> {
}

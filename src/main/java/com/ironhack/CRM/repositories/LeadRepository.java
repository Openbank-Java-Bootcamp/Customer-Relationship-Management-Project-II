package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, String> {

    @Query(value = "SELECT sales_rep_id, COUNT(*) FROM Lead GROUP BY sales_rep_id", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();
}

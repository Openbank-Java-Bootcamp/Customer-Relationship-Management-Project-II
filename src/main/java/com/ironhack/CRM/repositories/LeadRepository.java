package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, String> {

    @Query(value = "SELECT salesRep, COUNT(*) FROM _lead GROUP BY SalesRep", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();
}

package com.ironhack.CRM.repositories;


import com.ironhack.CRM.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface LeadRepository extends JpaRepository<Lead, String> {


    @Query(value = "SELECT sales_rep.name, COUNT(*) FROM _lead JOIN sales_rep ON _lead.sales_rep_id = sales_rep.id GROUP BY sales_rep.name", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();
}

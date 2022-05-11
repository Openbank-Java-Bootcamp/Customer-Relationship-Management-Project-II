package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, String> {

    //By Product:
    //“Report Opportunity by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountGroupByProduct();

    //“Report CLOSED-WON by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusWonGroupByProduct();

    // “Report CLOSED-LOST by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusLostGroupByProduct();

    //“Report OPEN by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusOpenGroupByProduct();

    //By country:
    //Report Opportunity by country
    @Query(value = "SELECT COUNT(*) FROM opportunity GROUP BY country", nativeQuery = true)
    List<Opportunity> findCountGroupByCountry();

    //Report CLOSED-WON by the country
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY country", nativeQuery = true)
    List<Opportunity> findCountWithStatusWonGroupByCountry();

    //Report CLOSED-LOST by the country
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY country", nativeQuery = true)
    List<Opportunity> findCountWithStatusLostGroupByCountry();

    //Report OPEN by country

    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY country", nativeQuery = true)
    List<Opportunity> findCountWithStatusOpenGroupByCountry();



    //By Quantity:

    // Mean quantity of products


    // Median quantity of products

    // Maximum quantity of products

    // Minimum quantity of products

}

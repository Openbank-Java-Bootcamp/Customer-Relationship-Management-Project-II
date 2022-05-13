package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, String> {

    //By SalesRep:
    //“Report Opportunity by SalesRep”
    @Query(value = "SELECT sales_rep_id, COUNT(*) FROM opportunity GROUP BY sales_rep_id", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();

    //“Report CLOSED-WON by SalesRep”
    @Query(value = "SELECT sales_rep_id, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY sales_rep_id", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupBySalesRep();

    //“Report CLOSED-LOST by SalesRep”
    @Query(value = "SELECT sales_rep_id, COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY sales_rep_id", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupBySalesRep();

    //"Report OPEN by SalesRep"
    @Query(value = "SELECT sales_rep_id, COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY sales_rep_id", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupBySalesRep();




    //By Product:
    //“Report Opportunity by the product”
    @Query(value = "SELECT product, COUNT(*) FROM opportunity GROUP BY product", nativeQuery = true)
    List<Object[]> findCountGroupByProduct();

    //“Report CLOSED-WON by the product”
    @Query(value = "SELECT product, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY product", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByProduct();

    // “Report CLOSED-LOST by the product”
    @Query(value = "SELECT product, COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY product", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByProduct();

    //“Report OPEN by the product”
    @Query(value = "SELECT product, COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY product", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByProduct();

    //By country:
    //Report Opportunity by country

    @Query(value = "SELECT account.country, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id GROUP BY account.country ORDER BY account.country", nativeQuery = true)
    List<Object[]> findCountGroupByCountry();

    //Report CLOSED-WON by the country

    @Query(value = "SELECT account.country, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_WON' GROUP BY account.country ORDER BY account.country", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByCountry();

    //Report CLOSED-LOST by the country

    @Query(value = "SELECT account.country, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_LOST' GROUP BY account.country ORDER BY account.country", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByCountry();

    //Report OPEN by country

    @Query(value = "SELECT account.country, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'OPEN' GROUP BY account.country ORDER BY country", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByCountry();


    //By Industry:
    //“Report Opportunity by Industry”
    @Query(value = "SELECT account.industry, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id GROUP BY account.industry ORDER BY account.industry", nativeQuery = true)
    List<Object[]> findCountGroupByIndustry();

    //"Report CLOSED-WON by Industry"
    @Query(value = "SELECT account.industry, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_WON' GROUP BY account.industry ORDER BY account.industry", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByIndustry();

    //"Report CLOSED-LOST by Industry"
    @Query(value = "SELECT account.industry, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_LOST' GROUP BY account.industry ORDER BY account.industry", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByIndustry();

    //"Report OPEN by Industry"
    @Query(value = "SELECT account.industry, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'OPEN' GROUP BY account.industry ORDER BY industry", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByIndustry();



    //By Quantity:

    // Mean quantity of products


    // Median quantity of products

    // Maximum quantity of products

    // Minimum quantity of products


    //CITY
    //“Report Opportunity by City”
    @Query(value = "SELECT city, COUNT(*) FROM opportunity GROUP BY city", nativeQuery = true)
    List<Object[]> findCountGroupByCity();

    //“Report CLOSED-WON by City”
    @Query(value = "SELECT city, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY city", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByCity();

    //“Report CLOSED-LOST by City”
    @Query(value = "SELECT city, COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY city", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByCity();

    //“Report OPEN by City”
    @Query(value = "SELECT city, COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY city", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByCity();



    //OPPORTUNITY
    //The mean number of Opportunities associated with an Account can be displayed by typing “Mean Opps per Account”
    @Query(value = "SELECT AVG(opportunities_per_account) FROM (SELECT COUNT (*) opportunities_per_account FROM opportunity GROUP BY account_id) sums", nativeQuery = true)
    Double countMeanOfOpportunitiesAssociatedToAccount();

    //The median number of Opportunities associated with an Account can be displayed by typing “Median Opps per Account”



    //The maximum number of Opportunities associated with an Account can be displayed by typing “Max Opps per Account”
    @Query(value = "SELECT MAX(opportunities_per_account) FROM (SELECT COUNT (*) opportunities_per_account FROM opportunity GROUP BY account_id) sums", nativeQuery = true)
    Integer maxOpportunitiesAssociatedToAccount();

    //The minimum number of Opportunities associated with an Account can be displayed by typing “Min Opps per Account”
    @Query(value = "SELECT MIN(opportunities_per_account) FROM (SELECT COUNT (*) opportunities_per_account FROM opportunity GROUP BY account_id) sums", nativeQuery = true)
    Integer minOpportunitiesAssociatedToAccount();

}

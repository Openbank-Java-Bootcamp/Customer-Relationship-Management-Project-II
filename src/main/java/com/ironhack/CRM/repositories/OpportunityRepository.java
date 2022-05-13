package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@Component
public interface OpportunityRepository extends JpaRepository<Opportunity, String> {

//By SalesRep:
//“Report Opportunity by SalesRep”

    @Query(value = "SELECT sales_rep.name, COUNT(*) FROM opportunity JOIN sales_rep ON opportunity.sales_rep_id = sales_rep.id GROUP BY sales_rep.name", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();

//“Report CLOSED-WON by SalesRep”
@Query(value = "SELECT sales_rep.name, COUNT(*) FROM opportunity JOIN sales_rep ON opportunity.sales_rep_id = sales_rep.id WHERE status = 'CLOSED_WON' GROUP BY sales_rep.name", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupBySalesRep();

//“Report CLOSED-LOST by SalesRep”
@Query(value = "SELECT sales_rep.name, COUNT(*) FROM opportunity JOIN sales_rep ON opportunity.sales_rep_id = sales_rep.id WHERE status = 'CLOSED_LOST' GROUP BY sales_rep.name", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupBySalesRep();

//"Report OPEN by SalesRep"
@Query(value = "SELECT sales_rep.name, COUNT(*) FROM opportunity JOIN sales_rep ON opportunity.sales_rep_id = sales_rep.id WHERE status = 'OPEN' GROUP BY sales_rep.name", nativeQuery = true)
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

    //By Quantity:

    // Mean quantity of products
    @Query(value = "SELECT AVG(product) FROM opportunity", nativeQuery = true)
    double findMeanProductCount();

    // Median quantity of products
    @Query(value = "SELECT AVG(aa.product) as median" +
            " FROM (" +
            "SELECT a.product, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum" +
            " FROM opportunity a, (SELECT @rownum:=0) r" +
            " WHERE a.product is NOT NULL" +
            " ORDER BY a.product) as aa" +
            " WHERE aa.row_number IN (FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2));", nativeQuery = true)
    int findMedianProductCount();
    // Maximum quantity of products
    @Query(value = "SELECT MAX(product) FROM opportunity", nativeQuery = true)
    int findMaxEmployeeCount();
    // Minimum quantity of products
    @Query(value = "SELECT MIN(product) FROM opportunity", nativeQuery = true)
    int findMinProductCount();


    //CITY
    //“Report Opportunity by City”

    @Query(value = "SELECT account.city, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id GROUP BY account.city ORDER BY account.city", nativeQuery = true)
    List<Object[]> findCountGroupByCity();

    //“Report CLOSED-WON by City”
    @Query(value = "SELECT account.city, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_WON' GROUP BY account.city", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByCity();

    //“Report CLOSED-LOST by City”
    @Query(value = "SELECT account.city, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'CLOSED_LOST' GROUP BY account.city", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByCity();

    //“Report OPEN by City”
    @Query(value = "SELECT account.city, COUNT(*) FROM opportunity, account WHERE opportunity.account_id = account.id AND status = 'OPEN' GROUP BY account.city", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByCity();

    //OPPORTUNITY
    @Query(value = "SELECT account.id, count(opportunity.id) FROM opportunity join account on opportunity.account_id = account.id GROUP BY account.id", nativeQuery = true)
    List<Object[]> countMeanOfOpportunitiesAssociatedToAccount();


}

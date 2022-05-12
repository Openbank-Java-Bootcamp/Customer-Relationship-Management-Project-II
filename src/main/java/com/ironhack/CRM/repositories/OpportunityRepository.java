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
    @Query(value = "SELECT salesRep, COUNT(*) FROM opportunity GROUP BY salesRep", nativeQuery = true)
    List<Object[]> findCountGroupBySalesRep();

    //“Report CLOSED-WON by SalesRep”
    @Query(value = "SELECT salesRep, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY salesRep", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupBySalesRep();

    //“Report CLOSED-LOST by SalesRep”
    @Query(value = "SELECT salesRep, COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY salesRep", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupBySalesRep();

    //"Report OPEN by SalesRep"
    @Query(value = "SELECT salesRep, COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY salesRep", nativeQuery = true)
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



    //By Industry:
    //“Report Opportunity by Industry”
    @Query(value = "SELECT industry, COUNT(*) FROM opportunity GROUP BY industry", nativeQuery = true)
    List<Object[]> findCountGroupByIndustry();

    //"Report CLOSED-WON by Industry"
    @Query(value = "SELECT industry, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY industry", nativeQuery = true)
    List<Object[]> findCountWithStatusWonGroupByIndustry();

    //"Report CLOSED-LOST by Industry"
    @Query(value = "SELECT industry, COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY industry", nativeQuery = true)
    List<Object[]> findCountWithStatusLostGroupByIndustry();

    //"Report OPEN by Industry"
    @Query(value = "SELECT industry, COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY industry", nativeQuery = true)
    List<Object[]> findCountWithStatusOpenGroupByIndustry();



    //By Quantity:

    // The mean quantity of products order can be displayed by typing “Mean Quantity”
    @Query(value = "SELECT AVG(quantity_of_product) FROM opportunity", nativeQuery = true)
    double findMeanEmployeeCount();

    // The median quantity of products order can be displayed by typing “Median Quantity”
    @Query(value = "SELECT AVG(aa.quantity_of_product) as median" +
            " FROM (" +
            "SELECT a.quantity_of_product, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum" +
            " FROM account a, (SELECT @rownum:=0) r" +
            " WHERE a.quantity_of_product is NOT NULL" +
            " ORDER BY a.quantity_of_product) as aa" +
            " WHERE aa.row_number IN (FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2));", nativeQuery = true)
    int findMedianQuantityProduct();
    // The maximum quantity of products order can be displayed by typing “Max Quantity”
    @Query(value = "SELECT MAX(quantity_of_product) FROM opportunity", nativeQuery = true)
    int MaxQuantityOfProduct();
    // The minimum quantity of products order can be displayed by typing “Min Quantity”
    @Query(value = "SELECT MIN(quantity_of_product) FROM opportunity", nativeQuery = true)
    int MinQuantityOfProduct();


    //“Report Opportunity by City”
    @Query(value = "SELECT city, COUNT (*) FROM opportunity WHERE city = :city GROUP BY city", nativeQuery = true)
    Integer countOpportunitiesByCity(String city);

    //“Report CLOSED-WON by City”
    @Query(value = "SELECT COUNT (*) FROM opportunity WHERE city = :city AND status = 'CLOSED-WON' GROUP BY city", nativeQuery = true)
    Integer countOpportunitiesByCityWithStatusCloseWon(String city);

    //“Report CLOSED-LOST by City”
    @Query(value = "SELECT COUNT (*) FROM opportunity where city = :city AND status = 'CLOSE-LOST' GROUP BY city", nativeQuery = true)
    Integer countOpportunitiesByCityWithStatusCloseLost(String city);

    //“Report OPEN by City”
    @Query(value = "SELECT COUNT (*) FROM opportunity where city = :city AND status = 'open' GROUP BY city", nativeQuery = true)
    Integer countOpportunitiesByCityWithStatusOpen(String city);

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

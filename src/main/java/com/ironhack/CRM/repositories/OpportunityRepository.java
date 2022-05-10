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
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_LOST' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusLostGroupByProduct();

    // “Report CLOSED-LOST by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'CLOSED_WON' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusWonGroupByProduct();

    //“Report OPEN by the product”
    @Query(value = "SELECT COUNT(*) FROM opportunity WHERE status = 'OPEN' GROUP BY product", nativeQuery = true)
    List<Opportunity> findCountWithStatusOpenGroupByProduct();



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

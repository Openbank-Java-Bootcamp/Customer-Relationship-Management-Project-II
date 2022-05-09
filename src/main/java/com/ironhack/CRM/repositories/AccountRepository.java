package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    //By EmployeeCount States:
    //“Mean EmployeeCount”
    double findMeanEmployeeCount();

    //“Median EmployeeCount”
    int findMedianEmployeeCount();

    //“Max EmployeeCount”
    int findMaxEmployeeCount();

    //“Min EmployeeCount”
    int findMinEmployeeCount();


}

package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    //By EmployeeCount States:
    //“Mean EmployeeCount”
    @Query(value = "SELECT AVG(employee_count) FROM employee", nativeQuery = true)
    double findMeanEmployeeCount();

    //“Median EmployeeCount”
    @Query(value = "SELECT PERCENTILE_CONT(0.5) FROM employee", nativeQuery = true)
    int findMedianEmployeeCount();

    //“Max EmployeeCount”
    @Query(value = "SELECT MAX(employee_count) FROM employee", nativeQuery = true)
    int findMaxEmployeeCount();

    //“Min EmployeeCount”
    @Query(value = "SELECT MIN(employee_count) FROM employee", nativeQuery = true)
    int findMinEmployeeCount();


}

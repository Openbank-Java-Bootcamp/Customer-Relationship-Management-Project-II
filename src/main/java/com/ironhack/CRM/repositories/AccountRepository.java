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
    @Query(value = "SELECT AVG(employee_count) FROM account", nativeQuery = true)
    double findMeanEmployeeCount();

    //“Median EmployeeCount”
    @Query(value = "SELECT AVG(aa.employee_count) as median" +
            " FROM (" +
            "SELECT a.employee_count, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum" +
            " FROM account a, (SELECT @rownum:=0) r" +
            " WHERE a.employee_count is NOT NULL" +
            " ORDER BY a.employee_count) as aa" +
            " WHERE aa.row_number IN (FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2));", nativeQuery = true)
    int findMedianEmployeeCount();

    //“Max EmployeeCount”
    @Query(value = "SELECT MAX(employee_count) FROM account", nativeQuery = true)
    int findMaxEmployeeCount();

    //“Min EmployeeCount”
    @Query(value = "SELECT MIN(employee_count) FROM account", nativeQuery = true)
    int findMinEmployeeCount();


}

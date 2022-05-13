package com.ironhack.CRM.repositories;

import com.ironhack.CRM.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface AccountRepository extends JpaRepository<Account, String> {

    //By EmployeeCount States:
    //“Mean EmployeeCount”
    @Query(value = "SELECT AVG(employee_count) FROM account", nativeQuery = true)
    double findMeanEmployeeCount();

    @Query(value = "SELECT employee_count FROM account", nativeQuery = true)
    List<Integer> findEmployeeCounts();

    @Query(value = "SELECT MAX(employee_count) FROM account", nativeQuery = true)
    int findMaxEmployeeCount();

    //“Min EmployeeCount”
    @Query(value = "SELECT MIN(employee_count) FROM account", nativeQuery = true)
    int findMinEmployeeCount();


}

package com.javatraining.jpaexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javatraining.jpaexample.jpa.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {


}

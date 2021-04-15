package com.javatraining.jpaexample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javatraining.jpaexample.dao.EmployeeDao;
import com.javatraining.jpaexample.jpa.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDao.findById(id)
                .get();
    }

    public void deleteEmployee(Integer id) {

        employeeDao.deleteById(id);

    }

    public Employee createEmployee(Employee employee) {

        return employeeDao.save(employee);

    }



}

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

    public Employee updateEmployee(Employee employee) {

        Employee e1 = employeeDao.findById(employee.getEmpid())
                .get();
        e1.setEmail(employee.getEmail());
        e1.setName(employee.getName());
        e1.setPhone(employee.getPhone());
        e1.setCreated_on(employee.getCreated_on());
        e1.setCreated_by(employee.getCreated_by());
        return employeeDao.save(e1);

    }

}

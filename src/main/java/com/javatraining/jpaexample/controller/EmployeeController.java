package com.javatraining.jpaexample.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.service.EmployeeService;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/getEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeByID")
    public ResponseEntity<Employee> getEmployee(@RequestParam("id") Integer id) {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/deleteEmployee")
    public void deleteEmployee(@RequestParam("id") Integer id) {
        new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/saveEmployee")
    public void saveEmployee(@RequestParam("id") Integer id) {
        new ResponseEntity(HttpStatus.OK);
    }

}

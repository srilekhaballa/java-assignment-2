package com.javatraining.jpaexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @DeleteMapping("/deleteEmployee")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PostMapping("/createEmployee")
    public void createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id, @Valid @RequestBody Employee employeeDetails) {
        Employee employee = employeeService.getEmployeeById(id);
        employee.setEmail(employeeDetails.getEmail());
        employee.setName(employeeDetails.getName());
        employee.setPhone(employeeDetails.getPhone());
        final Employee updatedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}

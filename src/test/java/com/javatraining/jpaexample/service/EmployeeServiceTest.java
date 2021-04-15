package com.javatraining.jpaexample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import com.javatraining.jpaexample.dao.EmployeeDao;
import com.javatraining.jpaexample.jpa.entity.Employee;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    EmployeeDao employeeDao;

    @InjectMocks
    EmployeeService employeeService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }


    @Test
    public void testGetAllEmployees() {
        Employee e1 = new Employee();
        e1.setEmpid(1);
        Employee e2 = new Employee();
        e2.setEmpid(2);
        ArrayList<Employee> l = new ArrayList<Employee>();
        l.add(e1);
        l.add(e2);
        when(employeeDao.findAll()).thenReturn(l);

        assertNotNull(employeeService.getAllEmployees());


    }

    @Test
    public void testGetById() {
        Employee e1 = new Employee();
        e1.setEmpid(1);

        Optional<Employee> o = Optional.of(e1);

        when(employeeDao.findById(1)).thenReturn(o);

        assertNotNull(employeeService.getEmployeeById(1));
    }


    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(employee);
        restTemplate.delete(getRootUrl() + "/employees/" + id);
        try {
            employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmail("admin@gmail.com");
        employee.setName("admin");

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", employee, Employee.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        employee.setName("admin1");
        restTemplate.put(getRootUrl() + "/employees/" + id, employee);
        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

}

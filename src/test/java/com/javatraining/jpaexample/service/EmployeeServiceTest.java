package com.javatraining.jpaexample.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.javatraining.jpaexample.JpaExampleApplicationTests;
import com.javatraining.jpaexample.dao.EmployeeDao;
import com.javatraining.jpaexample.jpa.entity.Employee;

public class EmployeeServiceTest extends JpaExampleApplicationTests {



    @Mock
    EmployeeDao employeeDao;

    @InjectMocks
    EmployeeService employeeService;



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
    public void testdeleteEmployee() {

        employeeService.deleteEmployee(1);
        verify(employeeDao, times(1)).deleteById(1);

    }

    @Test
    public void testcreateEmployee() {

        Employee e1 = new Employee();
        e1.setEmpid(1);

        employeeService.createEmployee(e1);
        verify(employeeDao, times(1)).save(e1);

    }

    @Test
    public void testupdateEmployee() {

        Employee e1 = new Employee();
        e1.setEmpid(1);

        employeeService.updateEmployee(e1);
        verify(employeeDao, times(1)).save(e1);

    }


}

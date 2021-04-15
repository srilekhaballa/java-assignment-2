package com.javatraining.jpaexample.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatraining.jpaexample.JpaExampleApplication;
import com.javatraining.jpaexample.jpa.entity.Employee;
import com.javatraining.jpaexample.service.EmployeeService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {JpaExampleApplication.class})
@AutoConfigureMockMvc
public class EmployeeControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;


    @Test
    public void testGetAllEmployees() throws Exception {
        Employee e1 = new Employee();
        e1.setEmpid(1);
        Employee e2 = new Employee();
        e2.setEmpid(2);
        ArrayList<Employee> l = new ArrayList<Employee>();
        l.add(e1);
        l.add(e2);

        when(employeeService.getAllEmployees()).thenReturn(l);

        MvcResult res = mockMvc.perform(get("/getEmployees").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Employee> list = new ObjectMapper().readValue(res.getResponse()
                .getContentAsString(), List.class);
        assertEquals(list.size(), 2);


    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee e1 = new Employee();
        e1.setEmpid(1);


        when(employeeService.getEmployeeById(1)).thenReturn(e1);

        MvcResult res = mockMvc.perform(get("/getEmployeeByID?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Employee e = new ObjectMapper().readValue(res.getResponse()
                .getContentAsString(), Employee.class);
        assertEquals(e.getEmpid(), 1);


    }

    @Test
    public void testdeleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEmployee/1"))
                .andExpect(status().isOk());
    }

}

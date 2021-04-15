package com.javatraining.jpaexample.jpa.entity;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // auto-increment
    private Integer empid;
    private String name;
    private String email;
    private String phone;
    private String created_by;
    private Date created_on;

    public Employee(int i, String string, String string2, String string3, String string4, Date date) {
        this.empid = i;
        this.name = string;
        this.email = string2;
        this.phone = string3;
        this.created_by = string4;
        this.created_on = date;
    }

    public Employee() {
        // TODO Auto-generated constructor stub
    }


    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    @Override
    public String toString() {
        return "Employee [empid=" + empid + ", name=" + name + ", email=" + email + ", phone=" + phone + ", created_by=" + created_by + ", created_on=" + created_on + "]";
    }



}


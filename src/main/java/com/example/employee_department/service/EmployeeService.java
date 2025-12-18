package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, double salary, int department);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    List<Employee> getAll();
}
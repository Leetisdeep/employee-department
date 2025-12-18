package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    
    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
    
    @Override
    public Employee add(String firstName, String lastName, double salary, int department) {
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new IllegalArgumentException("Сотрудник уже существует");
        }
        Employee newEmployee = new Employee(firstName, lastName, salary, department);
        employees.put(key, newEmployee);
        return newEmployee;
    }
    
    @Override
    public Employee remove(String firstName, String lastName) {
        return employees.remove(getKey(firstName, lastName));
    }
    
    @Override
    public Employee find(String firstName, String lastName) {
        return employees.get(getKey(firstName, lastName));
    }
    
    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees.values());
    }
}
package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    
    // Хранилище сотрудников
    private final List<Employee> employees = new ArrayList<>();
    
    public DepartmentService() {
        initializeEmployees();
    }
    
    private void initializeEmployees() {
        employees.add(new Employee("Иван", "Иванов", 50000, 1));
        employees.add(new Employee("Петр", "Петров", 60000, 1));
        employees.add(new Employee("Сергей", "Сергеев", 45000, 2));
        employees.add(new Employee("Анна", "Аннова", 55000, 2));
        employees.add(new Employee("Мария", "Марьина", 70000, 3));
        employees.add(new Employee("Дмитрий", "Дмитриев", 40000, 3));
        employees.add(new Employee("Елена", "Еленова", 65000, 1));
        employees.add(new Employee("Алексей", "Алексеев", 48000, 2));
        employees.add(new Employee("Ольга", "Ольгова", 52000, 3));
        employees.add(new Employee("Николай", "Николаев", 58000, 1));
    }
    
    // 1. Найти сотрудника с максимальной зарплатой в отделе (Stream API)
    public Optional<Employee> findMaxSalaryEmployee(int departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary));
    }
    
    // 2. Найти сотрудника с минимальной зарплатой в отделе (Stream API)
    public Optional<Employee> findMinSalaryEmployee(int departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary));
    }
    
    // 3. Найти всех сотрудников отдела (Stream API)
    public List<Employee> findEmployeesByDepartment(int departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
    
    // 4. Найти всех сотрудников, сгруппированных по отделам (Stream API)
    public Map<Integer, List<Employee>> findAllEmployeesGroupedByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    
    // 5. Альтернатива: просто все сотрудники по отделам
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        Map<Integer, List<Employee>> result = new HashMap<>();
        
        employees.forEach(employee -> {
            int dept = employee.getDepartment();
            result.computeIfAbsent(dept, k -> new ArrayList<>()).add(employee);
        });
        
        return result;
    }
    
    // 6. Получить сумму зарплат по отделу (Stream API)
    public double getDepartmentSalarySum(int departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    
    // 7. Получить среднюю зарплату по отделу (Stream API)
    public OptionalDouble getDepartmentAverageSalary(int departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .average();
    }
    
    // 8. Получить всех сотрудников (для тестов)
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}
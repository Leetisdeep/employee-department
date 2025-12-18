package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    // Внедряем зависимость на EmployeeService
    private final EmployeeService employeeService;
    
    // Внедрение через конструктор
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    // 1. Список сотрудников по отделу (GET /department/{id}/employees)
    @Override
    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }
    
    // 2. Сумма зарплат по отделу (GET /department/{id}/salary/sum)
    @Override
    public double getTotalSalaryByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }
    
    // 3. Максимальная зарплата по отделу (GET /department/{id}/salary/max)
    @Override
    public double getMaxSalaryByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0.0); // Если отдел пустой
    }
    
    // 4. Минимальная зарплата по отделу (GET /department/{id}/salary/min)
    @Override
    public double getMinSalaryByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0.0);
    }
    
    // 5. Все сотрудники, сгруппированные по отделам (GET /department/employees)
    @Override
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
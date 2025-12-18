package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    // 1. Список сотрудников по отделу
    List<Employee> getEmployeesByDepartment(int departmentId);
    
    // 2. Сумма зарплат по отделу
    double getTotalSalaryByDepartment(int departmentId);
    
    // 3. Максимальная зарплата по отделу
    double getMaxSalaryByDepartment(int departmentId);
    
    // 4. Минимальная зарплата по отделу
    double getMinSalaryByDepartment(int departmentId);
    
    // 5. Все сотрудники, сгруппированные по отделам
    Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment();
}
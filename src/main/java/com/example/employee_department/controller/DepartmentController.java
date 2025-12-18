package com.example.employee_department.controller;

import com.example.employee_department.model.Employee;
import com.example.employee_department.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    
    // Внедрение зависимости через конструктор
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    // 1. GET /department/{id}/employees
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }
    
    // 2. GET /department/{id}/salary/sum
    @GetMapping("/{id}/salary/sum")
    public double getTotalSalaryByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getTotalSalaryByDepartment(departmentId);
    }
    
    // 3. GET /department/{id}/salary/max
    @GetMapping("/{id}/salary/max")
    public double getMaxSalaryByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getMaxSalaryByDepartment(departmentId);
    }
    
    // 4. GET /department/{id}/salary/min
    @GetMapping("/{id}/salary/min")
    public double getMinSalaryByDepartment(@PathVariable("id") int departmentId) {
        return departmentService.getMinSalaryByDepartment(departmentId);
    }
    
    // 5. GET /department/employees
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.getAllEmployeesGroupedByDepartment();
    }
}
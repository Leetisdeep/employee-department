package com.example.employee_department.controller;

import com.example.employee_department.model.Employee;
import com.example.employee_department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    
    private final DepartmentService departmentService;
    
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public ResponseEntity<?> getMaxSalaryEmployee(@RequestParam int departmentId) {
        Optional<Employee> employee = departmentService.findMaxSalaryEmployee(departmentId);
        
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("В отделе " + departmentId + " сотрудники не найдены");
        }
    }
    
    @GetMapping("/min-salary")
    public ResponseEntity<?> getMinSalaryEmployee(@RequestParam int departmentId) {
        Optional<Employee> employee = departmentService.findMinSalaryEmployee(departmentId);
        
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("В отделе " + departmentId + " сотрудники не найдены");
        }
    }
    
    @GetMapping(value = "/all", params = "departmentId")
    public ResponseEntity<?> getEmployeesByDepartment(@RequestParam int departmentId) {
        List<Employee> employees = departmentService.findEmployeesByDepartment(departmentId);
        
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("В отделе " + departmentId + " сотрудники не найдены");
        }
        
        return ResponseEntity.ok(employees);
    }
    
    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllEmployeesGroupedByDepartment() {
        return departmentService.findAllEmployeesGroupedByDepartment();
    }
    
    @GetMapping("/sum")
    public ResponseEntity<String> getDepartmentSalarySum(@RequestParam int departmentId) {
        double sum = departmentService.getDepartmentSalarySum(departmentId);
        return ResponseEntity.ok("Сумма зарплат в отделе " + departmentId + ": " + sum);
    }
    
    @GetMapping("/average")
    public ResponseEntity<String> getDepartmentAverageSalary(@RequestParam int departmentId) {
        var averageOpt = departmentService.getDepartmentAverageSalary(departmentId);
        
        if (averageOpt.isPresent()) {
            return ResponseEntity.ok(String.format(
                "Средняя зарплата в отделе %d: %.2f", 
                departmentId, 
                averageOpt.getAsDouble()
            ));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("В отделе " + departmentId + " сотрудники не найдены");
    }
    
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return departmentService.getAllEmployees();
    }
}
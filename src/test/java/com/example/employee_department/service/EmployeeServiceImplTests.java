package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeService employeeService;
    
    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }
    
    @Test
    void addEmployee_ShouldSuccessfullyAddNewEmployee() {
        // Подготовка
        String firstName = "Иван";
        String lastName = "Иванов";
        
        // Действие
        Employee added = employeeService.add(firstName, lastName, 50000, 1);
        
        // Проверка
        assertNotNull(added);
        assertEquals(firstName, added.getFirstName());
        assertEquals(lastName, added.getLastName());
        assertEquals(1, added.getDepartment());
    }
    
    @Test
    void addEmployee_WhenEmployeeAlreadyExists_ShouldThrowException() {
        // Подготовка
        employeeService.add("Иван", "Иванов", 50000, 1);
        
        // Действие и проверка
        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.add("Иван", "Иванов", 60000, 2);
        });
    }
    
    @Test
    void findEmployee_WhenEmployeeExists_ShouldReturnEmployee() {
        // Подготовка
        Employee expected = employeeService.add("Иван", "Иванов", 50000, 1);
        
        // Действие
        Employee found = employeeService.find("Иван", "Иванов");
        
        // Проверка
        assertNotNull(found);
        assertEquals(expected.getFirstName(), found.getFirstName());
        assertEquals(expected.getLastName(), found.getLastName());
    }
    
    @Test
    void findEmployee_WhenEmployeeNotExists_ShouldReturnNull() {
        // Действие
        Employee found = employeeService.find("Несуществующий", "Сотрудник");
        
        // Проверка
        assertNull(found);
    }
    
    @Test
    void removeEmployee_WhenEmployeeExists_ShouldRemoveAndReturnEmployee() {
        // Подготовка
        Employee added = employeeService.add("Иван", "Иванов", 50000, 1);
        
        // Действие
        Employee removed = employeeService.remove("Иван", "Иванов");
        
        // Проверка
        assertNotNull(removed);
        assertEquals(added.getFirstName(), removed.getFirstName());
        
        // Проверяем, что сотрудник действительно удалён
        assertNull(employeeService.find("Иван", "Иванов"));
    }
    
    @Test
    void removeEmployee_WhenEmployeeNotExists_ShouldReturnNull() {
        // Действие
        Employee removed = employeeService.remove("Несуществующий", "Сотрудник");
        
        // Проверка
        assertNull(removed);
    }
    
    @Test
    void getAllEmployees_ShouldReturnAllAddedEmployees() {
        // Подготовка
        Employee emp1 = employeeService.add("Иван", "Иванов", 50000, 1);
        Employee emp2 = employeeService.add("Петр", "Петров", 60000, 2);
        
        // Действие
        List<Employee> allEmployees = employeeService.getAll();
        
        // Проверка
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
        assertTrue(allEmployees.contains(emp1));
        assertTrue(allEmployees.contains(emp2));
    }
}   
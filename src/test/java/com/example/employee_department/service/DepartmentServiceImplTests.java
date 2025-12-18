package com.example.employee_department.service;

import com.example.employee_department.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    
    @Mock
    private EmployeeService employeeServiceMock;
    
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    
    private Employee emp1; // Отдел 1, з/п 50000
    private Employee emp2; // Отдел 1, з/п 60000
    private Employee emp3; // Отдел 2, з/п 45000
    private List<Employee> allEmployees;
    
    @BeforeEach
    void setUp() {
        emp1 = new Employee("Иван", "Иванов", 50000, 1);
        emp2 = new Employee("Петр", "Петров", 60000, 1);
        emp3 = new Employee("Сергей", "Сергеев", 45000, 2);
        
        allEmployees = Arrays.asList(emp1, emp2, emp3);

        when(employeeServiceMock.getAll()).thenReturn(allEmployees);
    }
    
    // 1. Тест: Получить сотрудников по отделу (отдел существует)
    @Test
    void getEmployeesByDepartment_WhenDepartmentExists_ShouldReturnEmployees() {
        // Действие
        List<Employee> result = departmentService.getEmployeesByDepartment(1);
        
        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(emp1));
        assertTrue(result.contains(emp2));
    }
    
    // 2. Тест: Получить сотрудников по отделу (отдел не существует)
    @Test
    void getEmployeesByDepartment_WhenDepartmentNotExists_ShouldReturnEmptyList() {
        // Действие
        List<Employee> result = departmentService.getEmployeesByDepartment(999);
        
        // Проверка
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    // 3. Тест: Сумма зарплат по отделу
    @Test
    void getTotalSalaryByDepartment_ShouldReturnCorrectSum() {
        // Действие
        double sumDept1 = departmentService.getTotalSalaryByDepartment(1); // 50000 + 60000
        double sumDept2 = departmentService.getTotalSalaryByDepartment(2); // 45000
        
        // Проверка
        assertEquals(110000.0, sumDept1, 0.001); // 110000
        assertEquals(45000.0, sumDept2, 0.001);  // 45000
        assertEquals(0.0, departmentService.getTotalSalaryByDepartment(999)); // Несуществующий отдел
    }
    
    // 4. Тест: Максимальная зарплата по отделу
    @Test
    void getMaxSalaryByDepartment_ShouldReturnCorrectMax() {
        // Действие
        double maxDept1 = departmentService.getMaxSalaryByDepartment(1);
        double maxDept2 = departmentService.getMaxSalaryByDepartment(2);
        double maxDeptEmpty = departmentService.getMaxSalaryByDepartment(999);
        
        // Проверка
        assertEquals(60000.0, maxDept1, 0.001); // Макс в отделе 1
        assertEquals(45000.0, maxDept2, 0.001); // Макс в отделе 2
        assertEquals(0.0, maxDeptEmpty, 0.001); // Для пустого отдела
    }
    
    // 5. Тест: Минимальная зарплата по отделу
    @Test
    void getMinSalaryByDepartment_ShouldReturnCorrectMin() {
        // Действие
        double minDept1 = departmentService.getMinSalaryByDepartment(1);
        double minDept2 = departmentService.getMinSalaryByDepartment(2);
        double minDeptEmpty = departmentService.getMinSalaryByDepartment(999);
        
        // Проверка
        assertEquals(50000.0, minDept1, 0.001); // Мин в отделе 1
        assertEquals(45000.0, minDept2, 0.001); // Мин в отделе 2
        assertEquals(0.0, minDeptEmpty, 0.001); // Для пустого отдела
    }
    
    // 6. Тест: Все сотрудники, сгруппированные по отделам
    @Test
    void getAllEmployeesGroupedByDepartment_ShouldReturnCorrectMap() {
        // Действие
        Map<Integer, List<Employee>> result = departmentService.getAllEmployeesGroupedByDepartment();
        
        // Проверка
        assertNotNull(result);
        assertEquals(2, result.size()); // Два отдела
        
        // Проверяем отдел 1
        List<Employee> dept1Employees = result.get(1);
        assertNotNull(dept1Employees);
        assertEquals(2, dept1Employees.size());
        assertTrue(dept1Employees.contains(emp1));
        assertTrue(dept1Employees.contains(emp2));
        
        // Проверяем отдел 2
        List<Employee> dept2Employees = result.get(2);
        assertNotNull(dept2Employees);
        assertEquals(1, dept2Employees.size());
        assertTrue(dept2Employees.contains(emp3));
    }
}
package com.revworkforce.service;

import com.revworkforce.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService = new EmployeeService();

    @Test
    void testAddEmployeeValidData() {

        Employee emp = new Employee(
                0,
                "Test User",
                "testuser@gmail.com",
                "EMPLOYEE",
                50000,
                null,
                "123456"
        );

        assertDoesNotThrow(() -> employeeService.addEmployee(emp));
    }

    @Test
    void testInvalidEmail() {

        Employee emp = new Employee(
                0,
                "Test User",
                "invalidemail",
                "EMPLOYEE",
                50000,
                null,
                "123456"
        );

        Exception exception = assertThrows(RuntimeException.class,
                () -> employeeService.addEmployee(emp));

        assertTrue(exception.getMessage().contains("Invalid email"));
    }

    @Test
    void testInvalidSalary() {

        Employee emp = new Employee(
                0,
                "Test User",
                "testuser@gmail.com",
                "EMPLOYEE",
                -1000,
                null,
                "123456"
        );

        Exception exception = assertThrows(RuntimeException.class,
                () -> employeeService.addEmployee(emp));

        assertTrue(exception.getMessage().contains("Salary"));
    }
}

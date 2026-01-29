package com.revworkforce.service;

import com.revworkforce.exception.EmployeeException;
import com.revworkforce.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    @Test
    void testInvalidEmail() {
        Employee emp = new Employee(
                0,
                "John",
                "wrongemail",   // ❌ invalid email
                "EMPLOYEE",
                5000,
                null,
                "pass123",
                10,
                10,
                10
        );

        EmployeeService service = new EmployeeService();

        assertThrows(EmployeeException.class, () -> {
            service.addEmployee(emp);
        });
    }
}

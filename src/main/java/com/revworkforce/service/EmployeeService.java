package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.exception.EmployeeException;
import com.revworkforce.model.Employee;
import com.revworkforce.util.ValidationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeService {

    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void addEmployee(Employee emp) {

        if (!ValidationUtil.isValidEmail(emp.getEmail())) {
            logger.error("Invalid email: " + emp.getEmail());
            throw new EmployeeException("Invalid email format!");
        }

        if (!ValidationUtil.isValidRole(emp.getRole())) {
            logger.error("Invalid role: " + emp.getRole());
            throw new EmployeeException("Invalid role! Use EMPLOYEE / MANAGER / ADMIN");
        }

        if (!ValidationUtil.isValidSalary(emp.getSalary())) {
            logger.error("Invalid salary: " + emp.getSalary());
            throw new EmployeeException("Salary must be greater than 0!");
        }

        employeeDAO.insertEmployee(emp);
        logger.info("Employee added successfully: " + emp.getEmail());
    }

    public void viewAllEmployees() {
        employeeDAO.getAllEmployees();
        logger.info("Viewed all employees");
    }
}

package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.exception.EmployeeException;
import com.revworkforce.model.Employee;

public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void addEmployee(Employee emp) {
        employeeDAO.addEmployee(emp);
        System.out.println("✅ Employee added successfully!");
    }

    public void updateEmployee(Employee emp) {
        if (!employeeDAO.updateEmployee(emp)) {
            throw new EmployeeException("Employee not found!");
        }
        System.out.println("✅ Employee updated!");
    }

    public void deleteEmployee(int id) {
        if (!employeeDAO.deleteEmployee(id)) {
            throw new EmployeeException("Employee not found!");
        }
        System.out.println("✅ Employee deleted!");
    }

    public void viewAllEmployees() {
        employeeDAO.getAllEmployees();
    }

    public Employee viewProfile(int id) {
        Employee emp = employeeDAO.getEmployeeById(id);
        if (emp == null) throw new EmployeeException("Employee not found!");
        return emp;
    }

    public void updateProfile(int id, String phone, String address, String emergencyContact) {
        if (!employeeDAO.updateProfile(id, phone, address, emergencyContact)) {
            throw new EmployeeException("Profile update failed!");
        }
        System.out.println("✅ Profile updated!");
    }

    public void changePassword(int id, String oldPass, String newPass) {
        if (!employeeDAO.changePassword(id, oldPass, newPass)) {
            throw new EmployeeException("Old password incorrect!");
        }
        System.out.println("✅ Password changed!");
    }

    public void assignManagerToEmployee(int employeeId, int managerId) {
        employeeDAO.assignManager(employeeId, managerId);
        System.out.println("✅ Manager assigned!");
    }

    public void viewTeamMembers(int managerId) {
        employeeDAO.getEmployeesByManager(managerId);
    }

    // ✅ THIS IS THE METHOD YOUR ERROR IS ABOUT
    public void searchEmployee(String keyword) {
        employeeDAO.searchEmployee(keyword);
    }
}

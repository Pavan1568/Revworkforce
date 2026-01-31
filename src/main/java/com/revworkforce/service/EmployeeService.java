package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.model.Employee;

public class EmployeeService {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    // ================= ADD EMPLOYEE =================
    public void addEmployee(Employee emp) {
        employeeDAO.addEmployee(emp);
        System.out.println("✅ Employee added successfully!");
    }

    // ================= VIEW PROFILE =================
    public Employee viewProfile(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    // ================= VIEW ALL EMPLOYEES =================
    public void viewAllEmployees() {
        employeeDAO.getAllEmployees();
    }

    // ================= UPDATE EMPLOYEE =================
    public void updateEmployee(Employee emp) {
        employeeDAO.updateEmployee(emp);
        System.out.println("✅ Employee updated successfully!");
    }

    // ================= ASSIGN MANAGER =================
    public void assignManagerToEmployee(int employeeId, int managerId) {
        employeeDAO.assignManager(employeeId, managerId);
        System.out.println("✅ Manager assigned successfully!");
    }

    // ================= SEARCH EMPLOYEE =================
    public void searchEmployee(String keyword) {
        employeeDAO.searchEmployee(keyword);
    }

    // ================= VIEW LEAVE BALANCE (THIS WAS MISSING) =================
    public void viewLeaveBalance(int employeeId) {
        employeeDAO.viewLeaveBalance(employeeId);
    }

    // ================= DEACTIVATE EMPLOYEE =================
    public void deactivateEmployee(int employeeId) {
        employeeDAO.deactivateEmployee(employeeId);
        System.out.println("✅ Employee deactivated successfully!");
    }

    // ================= ACTIVATE EMPLOYEE =================
    public void activateEmployee(int employeeId) {
        employeeDAO.activateEmployee(employeeId);
        System.out.println("✅ Employee activated successfully!");
    }
}

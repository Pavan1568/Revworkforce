package com.revworkforce.controller;

import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

import java.util.Scanner;

public class EmployeeController {

    private Scanner scanner = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();

    public void showMenu(Employee loggedInEmployee) {

        while (true) {
            System.out.println("\n===== EMPLOYEE MENU =====");
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProfile(loggedInEmployee.getId());
                    break;
                case 2:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void viewProfile(int employeeId) {
        try {
            Employee emp = employeeService.viewProfile(employeeId);

            System.out.println("\n===== EMPLOYEE PROFILE =====");
            System.out.println("ID        : " + emp.getId());
            System.out.println("Name      : " + emp.getName());
            System.out.println("Email     : " + emp.getEmail());
            System.out.println("Role      : " + emp.getRole());
            System.out.println("Salary    : " + emp.getSalary());
            System.out.println("ManagerId : " + emp.getManagerId());
            System.out.println("Casual Leave : " + emp.getCasualLeave());
            System.out.println("Sick Leave   : " + emp.getSickLeave());
            System.out.println("Paid Leave   : " + emp.getPaidLeave());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

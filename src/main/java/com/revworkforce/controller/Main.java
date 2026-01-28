package com.revworkforce.controller;

import com.revworkforce.exception.EmployeeException;
import com.revworkforce.exception.LoginException;
import com.revworkforce.model.Employee;
import com.revworkforce.model.LeaveRequest;
import com.revworkforce.service.EmployeeService;
import com.revworkforce.service.LeaveService;
import com.revworkforce.service.LoginService;
import com.revworkforce.service.ManagerService;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LoginService loginService = new LoginService();
        EmployeeService employeeService = new EmployeeService();
        LeaveService leaveService = new LeaveService();
        ManagerService managerService = new ManagerService();

        Employee loggedInUser;

        try {
            System.out.println("===== REVWORKFORCE LOGIN =====");

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            loggedInUser = loginService.authenticate(email, password);

        } catch (LoginException e) {
            System.out.println("❌ " + e.getMessage());
            return;
        }

        System.out.println("✅ Login successful!");
        System.out.println("Welcome, " + loggedInUser.getName() + " (" + loggedInUser.getRole() + ")");

        String role = loggedInUser.getRole();

        // ================= ADMIN MENU =================
        if (role.equalsIgnoreCase("ADMIN")) {

            while (true) {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    try {
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Email: ");
                        String empEmail = sc.nextLine();

                        System.out.print("Role (EMPLOYEE / MANAGER / ADMIN): ");
                        String empRole = sc.nextLine();

                        System.out.print("Salary: ");
                        double salary = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Manager ID (0 if none): ");
                        int mid = sc.nextInt();
                        sc.nextLine();

                        Integer managerId = (mid == 0) ? null : mid;

                        System.out.print("Password: ");
                        String empPassword = sc.nextLine();

                        Employee emp = new Employee(0, name, empEmail, empRole, salary, managerId, empPassword);
                        employeeService.addEmployee(emp);

                    } catch (EmployeeException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }

                else if (choice == 2) {
                    employeeService.viewAllEmployees();
                }

                else if (choice == 3) {
                    break;
                }
            }
        }

        // ================= EMPLOYEE MENU =================
        else if (role.equalsIgnoreCase("EMPLOYEE")) {

            while (true) {
                System.out.println("\n===== EMPLOYEE MENU =====");
                System.out.println("1. Apply Leave");
                System.out.println("2. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Leave Type (SICK / CASUAL / ANNUAL): ");
                    String type = sc.nextLine();

                    System.out.print("Start Date (yyyy-mm-dd): ");
                    String start = sc.nextLine();

                    System.out.print("End Date (yyyy-mm-dd): ");
                    String end = sc.nextLine();

                    System.out.print("Reason: ");
                    String reason = sc.nextLine();

                    LeaveRequest leave = new LeaveRequest(
                            0,
                            loggedInUser.getId(),
                            type,
                            Date.valueOf(start),
                            Date.valueOf(end),
                            reason,
                            "PENDING"
                    );

                    leaveService.applyLeave(leave);
                }

                else if (choice == 2) {
                    break;
                }
            }
        }

        // ================= MANAGER MENU =================
        else if (role.equalsIgnoreCase("MANAGER")) {

            while (true) {
                System.out.println("\n===== MANAGER MENU =====");
                System.out.println("1. View My Team Employees");
                System.out.println("2. View Team Leave Requests");
                System.out.println("3. Approve / Reject Leave");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    managerService.viewTeamEmployees(loggedInUser.getId());
                }

                else if (choice == 2) {
                    managerService.viewTeamLeaves(loggedInUser.getId());
                }

                else if (choice == 3) {
                    System.out.print("Leave ID: ");
                    int leaveId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Status (APPROVED / REJECTED): ");
                    String status = sc.nextLine();

                    managerService.updateLeaveStatus(leaveId, status);
                }

                else if (choice == 4) {
                    break;
                }
            }
        }

        System.out.println("👋 Logged out. Goodbye!");
        sc.close();
    }
}

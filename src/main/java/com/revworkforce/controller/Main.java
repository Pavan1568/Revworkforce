package com.revworkforce.controller;

import com.revworkforce.model.Employee;
import com.revworkforce.model.LeaveRequest;
import com.revworkforce.service.EmployeeService;
import com.revworkforce.service.LeaveService;
import com.revworkforce.service.LoginService;
import com.revworkforce.service.ManagerService;
import com.revworkforce.service.NotificationService;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LoginService loginService = new LoginService();
        EmployeeService employeeService = new EmployeeService();
        LeaveService leaveService = new LeaveService();
        ManagerService managerService = new ManagerService();
        NotificationService notificationService = new NotificationService();

        System.out.println("===== REVWORKFORCE LOGIN =====");

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Employee loggedInUser;

        try {
            loggedInUser = loginService.authenticate(email, password);
        } catch (Exception e) {
            System.out.println("❌ Invalid email or password!");
            return;
        }

        System.out.println("✅ Login successful!");
        System.out.println("Welcome, " + loggedInUser.getName() + " (" + loggedInUser.getRole() + ")");

        String role = loggedInUser.getRole();

        // ================= EMPLOYEE MENU =================
        if (role.equalsIgnoreCase("EMPLOYEE")) {

            while (true) {
                System.out.println("\n===== EMPLOYEE MENU =====");
                System.out.println("1. View Profile");
                System.out.println("2. Edit Profile");
                System.out.println("3. Apply Leave");
                System.out.println("4. View My Leave History");
                System.out.println("5. Cancel Leave");
                System.out.println("6. View Notifications");
                System.out.println("7. Change Password");
                System.out.println("8. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    Employee emp = employeeService.viewProfile(loggedInUser.getId());

                    System.out.println("\n===== EMPLOYEE PROFILE =====");
                    System.out.println("ID      : " + emp.getId());
                    System.out.println("Name    : " + emp.getName());
                    System.out.println("Email   : " + emp.getEmail());
                    System.out.println("Role    : " + emp.getRole());
                    System.out.println("Salary  : " + emp.getSalary());
                    System.out.println("Manager : " + emp.getManagerId());
                    System.out.println("Phone   : " + emp.getPhone());
                    System.out.println("Address : " + emp.getAddress());
                    System.out.println("Emergency Contact : " + emp.getEmergencyContact());
                    System.out.println("Casual Leave: " + emp.getCasualLeave());
                    System.out.println("Sick Leave  : " + emp.getSickLeave());
                    System.out.println("Paid Leave  : " + emp.getPaidLeave());
                }

                else if (choice == 2) {
                    System.out.print("Enter new phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter new address: ");
                    String address = sc.nextLine();

                    System.out.print("Enter emergency contact: ");
                    String emergency = sc.nextLine();

                    employeeService.updateProfile(loggedInUser.getId(), phone, address, emergency);
                }

                else if (choice == 3) {
                    System.out.print("Leave Type (CASUAL / SICK / PAID): ");
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
                            "PENDING",
                            null
                    );

                    leaveService.applyLeave(leave);
                }

                else if (choice == 4) {
                    leaveService.viewMyLeaves(loggedInUser.getId());
                }

                else if (choice == 5) {
                    System.out.print("Enter Leave ID to cancel: ");
                    int leaveId = sc.nextInt();
                    sc.nextLine();

                    leaveService.cancelLeave(leaveId, loggedInUser.getId());
                }

                else if (choice == 6) {
                    notificationService.viewNotifications(loggedInUser.getId());
                }

                else if (choice == 7) {
                    System.out.print("Enter old password: ");
                    String oldPass = sc.nextLine();

                    System.out.print("Enter new password: ");
                    String newPass = sc.nextLine();

                    employeeService.changePassword(loggedInUser.getId(), oldPass, newPass);
                }

                else if (choice == 8) {
                    break;
                }

                else {
                    System.out.println("❌ Invalid choice!");
                }
            }
        }

        // ================= MANAGER MENU =================
        else if (role.equalsIgnoreCase("MANAGER")) {

            while (true) {
                System.out.println("\n===== MANAGER MENU =====");
                System.out.println("1. View Leave Requests");
                System.out.println("2. Approve / Reject Leave");
                System.out.println("3. View Team Members");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    managerService.viewAllLeaves();
                }

                else if (choice == 2) {
                    System.out.print("Enter Leave ID: ");
                    int leaveId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Status (APPROVED / REJECTED): ");
                    String status = sc.nextLine();

                    System.out.print("Comment: ");
                    String comment = sc.nextLine();

                    managerService.approveOrRejectLeave(leaveId, status, comment);
                }

                else if (choice == 3) {
                    managerService.viewTeamMembers(loggedInUser.getId());
                }

                else if (choice == 4) {
                    break;
                }

                else {
                    System.out.println("❌ Invalid choice!");
                }
            }
        }

        // ================= ADMIN MENU =================
        else if (role.equalsIgnoreCase("ADMIN")) {

            while (true) {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add Employee");
                System.out.println("2. View All Employees");
                System.out.println("3. Assign Manager");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Search Employee");
                System.out.println("7. Exit");
                System.out.print("Choose option: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {

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

                    int cl = 10, sl = 10, pl = 10;

                    Employee emp = new Employee(
                            0,
                            name,
                            empEmail,
                            empRole,
                            salary,
                            managerId,
                            empPassword,
                            cl,
                            sl,
                            pl
                    );

                    employeeService.addEmployee(emp);
                }

                else if (choice == 2) {
                    employeeService.viewAllEmployees();
                }

                else if (choice == 3) {
                    System.out.print("Enter Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Manager ID: ");
                    int managerId = sc.nextInt();
                    sc.nextLine();

                    employeeService.assignManagerToEmployee(empId, managerId);
                }

                else if (choice == 4) {
                    System.out.print("Enter Employee ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String name = sc.nextLine();

                    System.out.print("New Email: ");
                    String emailNew = sc.nextLine();

                    System.out.print("New Role (EMPLOYEE / MANAGER / ADMIN): ");
                    String roleNew = sc.nextLine();

                    System.out.print("New Salary: ");
                    double salaryNew = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("New Manager ID (0 if none): ");
                    int midNew = sc.nextInt();
                    sc.nextLine();

                    Integer managerIdNew = (midNew == 0) ? null : midNew;

                    Employee updatedEmp = new Employee(
                            id,
                            name,
                            emailNew,
                            roleNew,
                            salaryNew,
                            managerIdNew,
                            "dummy",
                            10,
                            10,
                            10
                    );

                    employeeService.updateEmployee(updatedEmp);
                }

                else if (choice == 5) {
                    System.out.print("Enter Employee ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    employeeService.deleteEmployee(id);
                }

                else if (choice == 6) {
                    System.out.print("Enter name/email/id to search: ");
                    String keyword = sc.nextLine();
                    employeeService.searchEmployee(keyword);
                }

                else if (choice == 7) {
                    break;
                }

                else {
                    System.out.println("❌ Invalid choice!");
                }
            }
        }

        else {
            System.out.println("❌ Unknown role: " + role);
        }

        System.out.println("👋 Logged out. Goodbye!");
        sc.close();
    }
}

package com.revworkforce.dao;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    // ================= ADD EMPLOYEE =================
    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employee " +
                "(name, email, role, salary, manager_id, password, casual_leave, sick_leave, paid_leave, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVE')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getRole());
            ps.setDouble(4, employee.getSalary());
            ps.setObject(5, employee.getManagerId());
            ps.setString(6, employee.getPassword());
            ps.setInt(7, employee.getCasualLeave());
            ps.setInt(8, employee.getSickLeave());
            ps.setInt(9, employee.getPaidLeave());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE EMPLOYEE =================
    public void updateEmployee(Employee employee) {

        String sql = "UPDATE employee SET name=?, email=?, role=?, salary=?, manager_id=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getRole());
            ps.setDouble(4, employee.getSalary());
            ps.setObject(5, employee.getManagerId());
            ps.setInt(6, employee.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= GET EMPLOYEE BY ID =================
    public Employee getEmployeeById(int id) {

        String sql = "SELECT * FROM employee WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getDouble("salary"),
                        rs.getObject("manager_id") != null ? rs.getInt("manager_id") : null,
                        rs.getString("password"),
                        rs.getInt("casual_leave"),
                        rs.getInt("sick_leave"),
                        rs.getInt("paid_leave")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= VIEW ALL EMPLOYEES =================
    public void getAllEmployees() {

        String sql = "SELECT id, name, email, role, status FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== ALL EMPLOYEES =====");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Email: " + rs.getString("email") +
                                " | Role: " + rs.getString("role") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SEARCH EMPLOYEE =================
    public void searchEmployee(String keyword) {

        String sql = "SELECT id, name, email, role, status FROM employee " +
                "WHERE name LIKE ? OR email LIKE ? OR id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try {
                ps.setInt(3, Integer.parseInt(keyword));
            } catch (Exception e) {
                ps.setInt(3, -1);
            }

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== SEARCH RESULTS =====");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Email: " + rs.getString("email") +
                                " | Role: " + rs.getString("role") +
                                " | Status: " + rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("❌ No employee found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW LEAVE BALANCE =================
    public void viewLeaveBalance(int employeeId) {

        String sql = "SELECT casual_leave, sick_leave, paid_leave FROM employee WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Casual Leave : " + rs.getInt("casual_leave"));
                System.out.println("Sick Leave   : " + rs.getInt("sick_leave"));
                System.out.println("Paid Leave   : " + rs.getInt("paid_leave"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DEDUCT LEAVE BALANCE (FIX FOR LeaveDAO) =================
    public boolean deductLeaveBalance(int employeeId, String leaveType, int days) {

        String column;

        switch (leaveType.toUpperCase()) {
            case "CASUAL":
                column = "casual_leave";
                break;
            case "SICK":
                column = "sick_leave";
                break;
            case "PAID":
                column = "paid_leave";
                break;
            default:
                return false;
        }

        String sql = "UPDATE employee SET " + column + " = " + column + " - ? WHERE id=? AND " + column + " >= ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, days);
            ps.setInt(2, employeeId);
            ps.setInt(3, days);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= ASSIGN MANAGER =================
    public void assignManager(int employeeId, int managerId) {

        String sql = "UPDATE employee SET manager_id=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ps.setInt(2, employeeId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= TEAM MEMBERS =================
    public void getEmployeesByManager(int managerId) {

        String sql = "SELECT id, name, email, role FROM employee " +
                "WHERE manager_id=? AND status='ACTIVE'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== TEAM MEMBERS =====");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Email: " + rs.getString("email") +
                                " | Role: " + rs.getString("role")
                );
            }

            if (!found) {
                System.out.println("❌ No team members found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ACTIVATE EMPLOYEE =================
    public void activateEmployee(int employeeId) {

        String sql = "UPDATE employee SET status='ACTIVE' WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DEACTIVATE EMPLOYEE =================
    public void deactivateEmployee(int employeeId) {

        String sql = "UPDATE employee SET status='INACTIVE' WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

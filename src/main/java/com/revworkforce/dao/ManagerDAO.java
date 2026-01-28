package com.revworkforce.dao;

import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManagerDAO {

    // 1️⃣ View Employees Under Manager
    public void viewEmployeesUnderManager(int managerId) {

        String sql = "SELECT id, name, email, role, salary FROM employee WHERE manager_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== EMPLOYEES UNDER MANAGER ID: " + managerId + " =====");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("role") + " | " +
                                rs.getDouble("salary")
                );
            }

            if (!found) {
                System.out.println("⚠️ No employees found under this manager.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ View Leave Requests of Team (Manager)
    public void viewTeamLeaves(int managerId) {

        String sql = """
                SELECT lr.id, lr.employee_id, e.name, lr.leave_type, lr.start_date, lr.end_date, lr.status
                FROM leave_request lr
                JOIN employee e ON lr.employee_id = e.id
                WHERE e.manager_id = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== TEAM LEAVE REQUESTS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "LeaveID: " + rs.getInt("id") +
                                " | EmpID: " + rs.getInt("employee_id") +
                                " | Name: " + rs.getString("name") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status")
                );
            }

            if (!found) {
                System.out.println("⚠️ No leave requests found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Approve / Reject Leave (Manager Action)
    public void updateLeaveStatus(int leaveId, String status) {

        String sql = "UPDATE leave_request SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, leaveId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Leave status updated to: " + status);
            } else {
                System.out.println("❌ Leave ID not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

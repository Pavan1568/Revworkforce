package com.revworkforce.dao;

import com.revworkforce.model.LeaveRequest;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveDAO {

    // APPLY LEAVE
    public void applyLeave(LeaveRequest leave) {

        String sql = "INSERT INTO leave_request(employee_id, leave_type, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setDate(3, leave.getStartDate());
            ps.setDate(4, leave.getEndDate());
            ps.setString(5, leave.getReason());
            ps.setString(6, leave.getStatus());

            ps.executeUpdate();
            System.out.println("✅ Leave applied successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL LEAVES (MANAGER)
    public void viewAllLeaves() {

        String sql = "SELECT * FROM leave_request";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== ALL LEAVE REQUESTS =====");

            while (rs.next()) {
                System.out.println(
                        "Leave ID: " + rs.getInt("id") +
                                " | Emp ID: " + rs.getInt("employee_id") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // APPROVE / REJECT LEAVE (MANAGER)
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

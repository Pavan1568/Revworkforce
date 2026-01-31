package com.revworkforce.dao;

import com.revworkforce.model.LeaveRequest;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.temporal.ChronoUnit;

public class LeaveDAO {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    /* ================= APPLY LEAVE ================= */
    public void applyLeave(LeaveRequest leave) {

        String insertSql = "INSERT INTO leave_request " +
                "(employee_id, leave_type, start_date, end_date, reason, status) " +
                "VALUES (?, ?, ?, ?, ?, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSql)) {

            ps.setInt(1, leave.getEmployeeId());
            ps.setString(2, leave.getLeaveType());
            ps.setDate(3, leave.getStartDate());
            ps.setDate(4, leave.getEndDate());
            ps.setString(5, leave.getReason());

            ps.executeUpdate();

            // ✅ AUTO LEAVE BALANCE DEDUCTION
            long days = ChronoUnit.DAYS.between(
                    leave.getStartDate().toLocalDate(),
                    leave.getEndDate().toLocalDate()
            ) + 1;

            employeeDAO.deductLeaveBalance(
                    leave.getEmployeeId(),
                    leave.getLeaveType(),
                    (int) days
            );

            System.out.println("✅ Leave applied and balance updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= VIEW ALL LEAVES (MANAGER / ADMIN) ================= */
    public void viewAllLeaves() {

        String sql = "SELECT * FROM leave_request";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== LEAVE REQUESTS =====");

            while (rs.next()) {
                System.out.println(
                        "Leave ID: " + rs.getInt("id") +
                                " | Emp ID: " + rs.getInt("employee_id") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status") +
                                " | Comment: " + rs.getString("manager_comment")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= VIEW EMPLOYEE LEAVES ================= */
    public void viewMyLeaves(int employeeId) {

        String sql = "SELECT * FROM leave_request WHERE employee_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== MY LEAVES =====");

            while (rs.next()) {
                System.out.println(
                        "Leave ID: " + rs.getInt("id") +
                                " | Type: " + rs.getString("leave_type") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= CANCEL LEAVE ================= */
    public void cancelLeave(int leaveId, int employeeId) {

        String sql = "UPDATE leave_request SET status='CANCELLED' " +
                "WHERE id=? AND employee_id=? AND status='PENDING'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leaveId);
            ps.setInt(2, employeeId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Leave cancelled successfully!");
            } else {
                System.out.println("❌ Cannot cancel this leave!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ================= APPROVE / REJECT LEAVE ================= */
    public void updateLeaveStatus(int leaveId, String status, String comment) {

        String sql = "UPDATE leave_request SET status=?, manager_comment=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setString(2, comment);
            ps.setInt(3, leaveId);

            ps.executeUpdate();
            System.out.println("✅ Leave " + status + " successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

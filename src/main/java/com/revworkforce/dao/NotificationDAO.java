package com.revworkforce.dao;

import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NotificationDAO {

    public void addNotification(int employeeId, String message) {

        String sql = "INSERT INTO notification (employee_id, message) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setString(2, message);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("❌ Error creating notification: " + e.getMessage());
        }
    }

    public void showNotifications(int employeeId) {

        String sql = "SELECT * FROM notification WHERE employee_id = ? ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== NOTIFICATIONS =====");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("• " + rs.getString("message") +
                        " | " + rs.getTimestamp("created_at"));
            }

            if (!found) {
                System.out.println("No notifications.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error fetching notifications: " + e.getMessage());
        }
    }
}

package com.revworkforce.dao;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public Employee login(String email, String password) {

        String sql = "SELECT * FROM employee WHERE email = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

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
                        rs.getInt("paid_leave"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("emergency_contact")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

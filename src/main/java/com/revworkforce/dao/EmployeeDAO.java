package com.revworkforce.dao;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    // INSERT EMPLOYEE
    public void insertEmployee(Employee emp) {

        String sql = "INSERT INTO employee(name, email, role, salary, manager_id, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getRole());
            ps.setDouble(4, emp.getSalary());

            if (emp.getManagerId() == null) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, emp.getManagerId());
            }

            ps.setString(6, emp.getPassword());

            ps.executeUpdate();
            System.out.println("✅ Employee inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW ALL EMPLOYEES
    public void getAllEmployees() {

        String sql = "SELECT * FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== ALL EMPLOYEES =====");

            while (rs.next()) {

                Integer managerId = rs.getObject("manager_id", Integer.class);

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("role") + " | " +
                                rs.getDouble("salary") + " | Manager ID: " +
                                managerId
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIEW EMPLOYEES UNDER A MANAGER
    public void getEmployeesByManager(int managerId) {

        String sql = "SELECT * FROM employee WHERE manager_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);

            try (ResultSet rs = ps.executeQuery()) {

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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

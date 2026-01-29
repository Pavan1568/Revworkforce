package com.revworkforce.dao;

import com.revworkforce.model.Employee;
import com.revworkforce.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    // ================= ADD EMPLOYEE =================
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (name, email, role, salary, manager_id, password, casual_leave, sick_leave, paid_leave) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET name=?, email=?, role=?, salary=?, manager_id=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getRole());
            ps.setDouble(4, employee.getSalary());
            ps.setObject(5, employee.getManagerId());
            ps.setInt(6, employee.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= DELETE EMPLOYEE =================
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= VIEW ALL EMPLOYEES =================
    public void getAllEmployees() {
        String sql = "SELECT * FROM employee";

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
                                " | Salary: " + rs.getDouble("salary")
                );
            }

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

    // ================= UPDATE PROFILE =================
    public boolean updateProfile(int id, String phone, String address, String emergencyContact) {
        String sql = "UPDATE employee SET phone=?, address=?, emergency_contact=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, phone);
            ps.setString(2, address);
            ps.setString(3, emergencyContact);
            ps.setInt(4, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= CHANGE PASSWORD =================
    public boolean changePassword(int id, String oldPass, String newPass) {
        String checkSql = "SELECT * FROM employee WHERE id=? AND password=?";
        String updateSql = "UPDATE employee SET password=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, id);
            checkPs.setString(2, oldPass);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) return false;

            try (PreparedStatement updatePs = con.prepareStatement(updateSql)) {
                updatePs.setString(1, newPass);
                updatePs.setInt(2, id);
                updatePs.executeUpdate();
            }

            return true;

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
        String sql = "SELECT * FROM employee WHERE manager_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== TEAM MEMBERS =====");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Name: " + rs.getString("name") +
                                " | Email: " + rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= SEARCH EMPLOYEE ✅ (THIS FIXES YOUR ERROR) =================
    public void searchEmployee(String keyword) {

        String sql = "SELECT * FROM employee WHERE name LIKE ? OR email LIKE ? OR id=?";

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
                                " | Role: " + rs.getString("role")
                );
            }

            if (!found) {
                System.out.println("❌ No employee found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

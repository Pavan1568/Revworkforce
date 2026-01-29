package com.revworkforce.validation;

import com.revworkforce.exception.EmployeeException;

import java.util.regex.Pattern;

public class ValidationUtil {

    // ================= EMAIL VALIDATION =================
    public static void validateEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new EmployeeException("Email cannot be empty!");
        }

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (!Pattern.matches(regex, email)) {
            throw new EmployeeException("Invalid email format!");
        }
    }

    // ================= SALARY VALIDATION =================
    public static void validateSalary(double salary) {

        if (salary <= 0) {
            throw new EmployeeException("Salary must be greater than 0!");
        }
    }

    // ================= ROLE VALIDATION =================
    public static void validateRole(String role) {

        if (role == null) {
            throw new EmployeeException("Role cannot be null!");
        }

        if (!(role.equalsIgnoreCase("EMPLOYEE") ||
                role.equalsIgnoreCase("MANAGER") ||
                role.equalsIgnoreCase("ADMIN"))) {
            throw new EmployeeException("Invalid role! Must be EMPLOYEE, MANAGER, or ADMIN.");
        }
    }

    // ================= PHONE VALIDATION =================
    public static void validatePhone(String phone) {

        if (phone == null || phone.trim().isEmpty()) {
            throw new EmployeeException("Phone number cannot be empty!");
        }

        String regex = "^[0-9]{10}$";

        if (!Pattern.matches(regex, phone)) {
            throw new EmployeeException("Invalid phone number! Must be 10 digits.");
        }
    }

    // ================= PASSWORD VALIDATION 🔐 =================
    public static void validatePassword(String password) {

        if (password == null || password.trim().isEmpty()) {
            throw new EmployeeException("Password cannot be empty!");
        }

        if (password.length() < 6) {
            throw new EmployeeException("Password must be at least 6 characters long!");
        }

        // optional strong password rules (you can keep or remove)
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }

        if (!hasUpper || !hasLower || !hasDigit) {
            throw new EmployeeException(
                    "Password must contain at least one uppercase letter, one lowercase letter, and one digit!"
            );
        }
    }
}

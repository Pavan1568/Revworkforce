package com.revworkforce.util;

public class ValidationUtil {

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean isValidRole(String role) {
        return role.equalsIgnoreCase("EMPLOYEE")
                || role.equalsIgnoreCase("MANAGER")
                || role.equalsIgnoreCase("ADMIN");
    }

    public static boolean isValidSalary(double salary) {
        return salary > 0;
    }
}

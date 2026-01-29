package com.revworkforce.model;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String role;
    private double salary;
    private Integer managerId;
    private String password;
    private int casualLeave;
    private int sickLeave;
    private int paidLeave;
    private String phone;
    private String address;
    private String emergencyContact;

    // ✅ FULL CONSTRUCTOR (ALL FIELDS)
    public Employee(int id, String name, String email, String role, double salary,
                    Integer managerId, String password,
                    int casualLeave, int sickLeave, int paidLeave,
                    String phone, String address, String emergencyContact) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.salary = salary;
        this.managerId = managerId;
        this.password = password;
        this.casualLeave = casualLeave;
        this.sickLeave = sickLeave;
        this.paidLeave = paidLeave;
        this.phone = phone;
        this.address = address;
        this.emergencyContact = emergencyContact;
    }

    // ✅ STANDARD HRM CONSTRUCTOR (MOST USED)
    public Employee(int id, String name, String email, String role, double salary,
                    Integer managerId, String password,
                    int casualLeave, int sickLeave, int paidLeave) {
        this(id, name, email, role, salary, managerId, password,
                casualLeave, sickLeave, paidLeave, null, null, null);
    }

    // ✅ SAFE MINIMAL CONSTRUCTOR (LOGIN PURPOSE)
    public Employee(int id, String name, String email, String role, double salary,
                    Integer managerId, String password) {
        this(id, name, email, role, salary, managerId, password,
                10, 10, 10, null, null, null);
    }

    // ================= GETTERS =================

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public double getSalary() { return salary; }
    public Integer getManagerId() { return managerId; }
    public String getPassword() { return password; }
    public int getCasualLeave() { return casualLeave; }
    public int getSickLeave() { return sickLeave; }
    public int getPaidLeave() { return paidLeave; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEmergencyContact() { return emergencyContact; }

    // ================= SETTERS =================

    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
}

package com.revworkforce.model;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String role;
    private double salary;
    private Integer managerId;
    private String password;

    public Employee(int id, String name, String email, String role, double salary, Integer managerId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.salary = salary;
        this.managerId = managerId;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public String getPassword() {
        return password;
    }
}

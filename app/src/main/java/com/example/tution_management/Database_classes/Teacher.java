package com.example.tution_management.Database_classes;

public class Teacher {
    private int id;
    private String name;
    private String password;
    private String address;
    private String phone;
    private String assignedCourses;

    public Teacher(int id, String name, String password, String address, String phone, String assignedCourses) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.assignedCourses = assignedCourses;
    }

    // Getters and Setters (optional)

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getAssignedCourses() {
        return assignedCourses;
    }
}

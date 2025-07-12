package com.example.tution_management.Database_classes;


public class Course {
    private int id;
    private String courseName;
    private String courseDescription;
    private String assignedTeacher;

    public Course(int id, String courseName, String courseDescription, String assignedTeacher) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.assignedTeacher = assignedTeacher;
    }

    // Getters and setters (optional but recommended)
    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getAssignedTeacher() {
        return assignedTeacher;
    }

    // You can also add setters if you want to modify these values later
}


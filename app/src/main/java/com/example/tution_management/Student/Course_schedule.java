package com.example.tution_management.Student;

public class Course_schedule {

        private String courseName;
        private String date;
        private String time;

        public Course_schedule(String courseName, String date, String time) {
            this.courseName = courseName;
            this.date = date;
            this.time = time;
        }

        public String getCourseName() { return courseName; }
        public String getDate() { return date; }
        public String getTime() { return time; }
    }



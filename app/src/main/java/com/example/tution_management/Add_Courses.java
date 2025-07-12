package com.example.tution_management;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Courses extends Activity {

    EditText editCourseName, editCourseDescription, editAssignedTeacher;
    Button buttonSubmit;
    Tution_Database dbHelper;  // Use your actual DB helper class name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);

        editCourseName = findViewById(R.id.courseName);
        editCourseDescription = findViewById(R.id.courseDescription);
        editAssignedTeacher = findViewById(R.id.assignedTeacher); // New input for assigned teacher
        buttonSubmit = findViewById(R.id.submitCourseButton);

        dbHelper = new Tution_Database(this);

        buttonSubmit.setOnClickListener(v -> {
            String courseName = editCourseName.getText().toString().trim();
            String courseDescription = editCourseDescription.getText().toString().trim();
            String assignedTeacher = editAssignedTeacher.getText().toString().trim();

            if (courseName.isEmpty()) {
                Toast.makeText(Add_Courses.this, "Please enter course name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (assignedTeacher.isEmpty()) {
                Toast.makeText(Add_Courses.this, "Please enter assigned teacher", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insert data into database with assigned teacher
            boolean inserted = dbHelper.insertCourse(courseName, courseDescription, assignedTeacher);

            if (inserted) {
                Toast.makeText(Add_Courses.this, "Course added successfully!", Toast.LENGTH_SHORT).show();
                // Clear the fields
                editCourseName.setText("");
                editCourseDescription.setText("");
                editAssignedTeacher.setText("");
            } else {
                Toast.makeText(Add_Courses.this, "Error adding course", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

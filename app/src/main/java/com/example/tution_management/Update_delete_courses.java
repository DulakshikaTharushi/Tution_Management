package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tution_management.Tution_Database;


public class Update_delete_courses extends AppCompatActivity {

    private EditText editCourseName, editCourseDescription, editAssignedTeacher;
    private Button btnUpdate, btnDelete;

    private int courseId;
    private Tution_Database tutionDatabase;  // Correct instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_courses);

        // Initialize views
        editCourseName = findViewById(R.id.editCourseName);
        editCourseDescription = findViewById(R.id.editCourseDescription);
        editAssignedTeacher = findViewById(R.id.editTeacher);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);

        // Initialize database helper
        tutionDatabase = new Tution_Database(this);

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            courseId = intent.getIntExtra("COURSE_ID", -1);
            String name = intent.getStringExtra("COURSE_NAME");
            String description = intent.getStringExtra("COURSE_DESCRIPTION");
            String teacher = intent.getStringExtra("ASSIGNED_TEACHER");

            // Populate fields
            editCourseName.setText(name);
            editCourseDescription.setText(description);
            editAssignedTeacher.setText(teacher);
        }

        // Update Button Logic
        btnUpdate.setOnClickListener(v -> {
            String updatedName = editCourseName.getText().toString().trim();
            String updatedDescription = editCourseDescription.getText().toString().trim();
            String updatedTeacher = editAssignedTeacher.getText().toString().trim();

            if (updatedName.isEmpty() || updatedDescription.isEmpty() || updatedTeacher.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isUpdated = tutionDatabase.updateCourse(courseId, updatedName, updatedDescription, updatedTeacher);
                if (isUpdated) {
                    Toast.makeText(this, "Course updated successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close and return to previous page
                } else {
                    Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete Button Logic
        btnDelete.setOnClickListener(v -> {
            boolean isDeleted = tutionDatabase.deleteCourse(courseId);
            if (isDeleted) {
                Toast.makeText(this, "Course deleted", Toast.LENGTH_SHORT).show();
                finish(); // Close and return
            } else {
                Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

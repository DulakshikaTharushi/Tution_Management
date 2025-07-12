package com.example.tution_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tution_management.Database_classes.Course;
import com.example.tution_management.Database_classes.CourseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Course_Details extends Activity {

    FloatingActionButton addCourseButton;
    RecyclerView recyclerView;
    CourseAdapter courseAdapter;
    Tution_Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        // Initialize UI elements
        addCourseButton = findViewById(R.id.addCourseButton);
        recyclerView = findViewById(R.id.recyclerViewCourses);

        // Database
        dbHelper = new Tution_Database(this);

        // Get all courses from DB
        List<Course> courseList = dbHelper.getAllCourses();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter(this, courseList);

        recyclerView.setAdapter(courseAdapter);

        // Add course button
        addCourseButton.setOnClickListener(v -> {
            Intent intent = new Intent(Course_Details.this, Add_Courses.class);
            startActivity(intent);
        });
    }

    // Refresh list when returning from Add_Courses
    @Override
    protected void onResume() {
        super.onResume();
        List<Course> updatedList = dbHelper.getAllCourses();
        courseAdapter.updateCourses(updatedList);
    }
}

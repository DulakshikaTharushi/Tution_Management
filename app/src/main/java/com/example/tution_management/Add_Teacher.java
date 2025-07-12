package com.example.tution_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Teacher extends AppCompatActivity {

    EditText teacherIdInput, teacherNameInput, passwordInput, contactInput, emailInput, coursesInput;
    Button saveTeacherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        // Match EditText IDs from the layout
        teacherIdInput = findViewById(R.id.teacherId);
        teacherNameInput = findViewById(R.id.teacherName);
        passwordInput = findViewById(R.id.password);
        contactInput = findViewById(R.id.contactNumber);
        coursesInput = findViewById(R.id.assignedCourses);
        saveTeacherBtn = findViewById(R.id.submitButton);

        saveTeacherBtn.setOnClickListener(v -> {
            String id = teacherIdInput.getText().toString().trim();
            String name = teacherNameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String contact = contactInput.getText().toString().trim();
            String courses = coursesInput.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || password.isEmpty() || contact.isEmpty() ||  courses.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save data to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("teachers", Context.MODE_PRIVATE);
                int count = prefs.getInt("count", 0);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("teacher_" + count + "_id", id);
                editor.putString("teacher_" + count + "_name", name);
                editor.putString("teacher_" + count + "_password", password);
                editor.putString("teacher_" + count + "_contact", contact);
                editor.putString("teacher_" + count + "_courses", courses);
                editor.putInt("count", count + 1);
                editor.apply();

                Toast.makeText(this, "Teacher Saved", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, Teachers_Details.class));
                finish();
            }
        });
    }
}

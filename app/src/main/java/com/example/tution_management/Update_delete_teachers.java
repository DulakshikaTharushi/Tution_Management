package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update_delete_teachers extends AppCompatActivity {

    private EditText editTeacherName, editTeacherPassword, editTeacherContact, editTeacherAddress, editAssignedCourses;
    private Button btnUpdate, btnDelete;

    private int teacherId;
    private Tution_Database tutionDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_teachers);

        // Initialize views
        editTeacherName = findViewById(R.id.editTeacherName);
        editTeacherPassword = findViewById(R.id.editTeacherPassword);
        editTeacherContact = findViewById(R.id.editTeacherContact);
        editTeacherAddress = findViewById(R.id.editTeacherAddress);
        editAssignedCourses = findViewById(R.id.editAssignedCourses);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);

        // Initialize database
        tutionDatabase = new Tution_Database(this);

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            teacherId = intent.getIntExtra("TEACHER_ID", -1);
            String name = intent.getStringExtra("TEACHER_NAME");
            String password = intent.getStringExtra("TEACHER_PASSWORD");
            String contact = intent.getStringExtra("TEACHER_CONTACT");
            String address = intent.getStringExtra("TEACHER_ADDRESS");
            String courses = intent.getStringExtra("ASSIGNED_COURSES");

            // Populate fields
            editTeacherName.setText(name);
            editTeacherPassword.setText(password);
            editTeacherContact.setText(contact);
            editTeacherAddress.setText(address);
            editAssignedCourses.setText(courses);
        }

        // Update logic
        btnUpdate.setOnClickListener(v -> {
            String updatedName = editTeacherName.getText().toString().trim();
            String updatedPassword = editTeacherPassword.getText().toString().trim();
            String updatedContact = editTeacherContact.getText().toString().trim();
            String updatedAddress = editTeacherAddress.getText().toString().trim();
            String updatedCourses = editAssignedCourses.getText().toString().trim();

            if (updatedName.isEmpty() || updatedPassword.isEmpty() || updatedContact.isEmpty()
                    || updatedAddress.isEmpty() || updatedCourses.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isUpdated = tutionDatabase.updateTeacher(
                        teacherId, updatedName, updatedPassword, updatedContact, updatedAddress, updatedCourses
                );
                if (isUpdated) {
                    Toast.makeText(this, "Teacher updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete logic
        btnDelete.setOnClickListener(v -> {
            boolean isDeleted = tutionDatabase.deleteTeacher(teacherId);
            if (isDeleted) {
                Toast.makeText(this, "Teacher deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

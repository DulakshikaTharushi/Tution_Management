package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tution_management.Student.Student_Dashboard;

public class Student_Login extends AppCompatActivity {

        EditText editTextUsername, editTextPassword;
        Button buttonLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_student_login);

            // Link UI elements
            editTextUsername = findViewById(R.id.usernameField);
            editTextPassword = findViewById(R.id.passwordField);
            buttonLogin = findViewById(R.id.loginButton);

            // Set onClickListener for login button
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    if (username.equals("Mery") && password.equals("m123")) {
                        // Success: Navigate to student dashboard
                        Intent intent = new Intent(Student_Login.this, Student_Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Failure: Show error
                        Toast.makeText(Student_Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


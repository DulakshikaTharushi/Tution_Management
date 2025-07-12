package com.example.tution_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Teacher_Login extends AppCompatActivity {

    EditText teacherNameInput, passwordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        teacherNameInput = findViewById(R.id.usernameField);
        passwordInput = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String inputName = teacherNameInput.getText().toString().trim();
            String inputPassword = passwordInput.getText().toString().trim();

            if (inputName.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("teachers", Context.MODE_PRIVATE);
            int count = prefs.getInt("count", 0);

            boolean loggedIn = false;

            for (int i = 0; i < count; i++) {
                String savedName = prefs.getString("teacher_" + i + "_name", "");
                String savedPassword = prefs.getString("teacher_" + i + "_password", "");

                if (inputName.equals(savedName) && inputPassword.equals(savedPassword)) {
                    loggedIn = true;
                    break;
                }
            }

            if (loggedIn) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                // TODO: Navigate to the teacher dashboard activity
                // startActivity(new Intent(this, TeacherDashboard.class));
                // finish();
            } else {
                Toast.makeText(this, "Invalid name or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

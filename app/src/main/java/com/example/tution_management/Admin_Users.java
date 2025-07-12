package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin_Users extends AppCompatActivity {

    LinearLayout teacherCard, studentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        teacherCard = findViewById(R.id.cardTeacher);
        studentCard = findViewById(R.id.cardStudent);

        teacherCard.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Users.this, Teachers_Details.class);
            startActivity(intent);
        });

        studentCard.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Users.this, Student_Details.class);
            startActivity(intent);
        });
    }
}

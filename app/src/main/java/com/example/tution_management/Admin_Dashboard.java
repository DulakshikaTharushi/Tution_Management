package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin_Dashboard extends AppCompatActivity {

    LinearLayout usersContainer, coursesContainer, reportsContainer, notificationsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);

        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind the layout containers
        usersContainer = findViewById(R.id.users_container);
        coursesContainer = findViewById(R.id.courses_container);
        reportsContainer = findViewById(R.id.reports_container);
        notificationsContainer = findViewById(R.id.notifications_container);

        // Set click listeners
        usersContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Dashboard.this, Admin_Users.class);
            startActivity(intent);
        });

        coursesContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Dashboard.this, Course_Details.class);
            startActivity(intent);
        });

        reportsContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Dashboard.this, Report_Dashboard   .class);
            startActivity(intent);
        });

        notificationsContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Dashboard.this, Admin_Notification.class);
            startActivity(intent);
        });
    }
}

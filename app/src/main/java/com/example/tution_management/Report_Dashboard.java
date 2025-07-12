package com.example.tution_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;  // Import View

import androidx.appcompat.app.AppCompatActivity;

public class Report_Dashboard extends AppCompatActivity {

    View attendanceContainer, resultsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_dashboard);  // <-- Your layout xml file name here

        attendanceContainer = findViewById(R.id.attendanceContainer);
        resultsContainer = findViewById(R.id.resultsContainer);

        attendanceContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Report_Dashboard.this, Attendance_Report.class);
            startActivity(intent);
        });

        resultsContainer.setOnClickListener(v -> {
            Intent intent = new Intent(Report_Dashboard.this, Result_Report.class);
            startActivity(intent);
        });
    }
}

package com.example.tution_management.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tution_management.R;

public class Student_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔗 Profile button
        Button buttonMyProfile = findViewById(R.id.button_my_profile);
        buttonMyProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Student_Dashboard.this, Student_profile.class);
            startActivity(intent);
        });

        // 🔗 Payments button (was named button_my_payments but opens schedule)
        Button buttonMyCourses = findViewById(R.id.button_my_payments);
        buttonMyCourses.setOnClickListener(v -> {
            Intent intent = new Intent(Student_Dashboard.this, Student_Course_Schedule.class);
            startActivity(intent);
        });

        // ✅ Result button
        Button buttonResult = findViewById(R.id.button_my_courses); // Ensure this ID exists in your XML
        buttonResult.setOnClickListener(v -> {
            Intent intent = new Intent(Student_Dashboard.this, Student_result.class);
            startActivity(intent);
        });

        // ✅ QR Code button
        Button buttonQRCode = findViewById(R.id.button_my_qr); // Make sure this ID exists in your XML
        buttonQRCode.setOnClickListener(v -> {
            Intent intent = new Intent(Student_Dashboard.this, Student_QR_code.class);

            // 🧾 Add student data here
            intent.putExtra("name", "Mery");
            intent.putExtra("email", "mery123@gmail.com");
            intent.putExtra("contact", "0712345678");

            startActivity(intent);
        });
    }
}

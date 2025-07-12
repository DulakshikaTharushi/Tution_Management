package com.example.tution_management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tution_management.Database_classes.Student;
import com.example.tution_management.Database_classes.StudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class Student_Details extends Activity {

    FloatingActionButton addStudentButton;
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    Tution_Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        addStudentButton = findViewById(R.id.addStudentButton);
        recyclerView = findViewById(R.id.recyclerViewStudents);

        dbHelper = new Tution_Database(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Student> studentList = dbHelper.getAllStudents();
        studentAdapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(studentAdapter);

        addStudentButton.setOnClickListener(v -> {
            startActivity(new Intent(Student_Details.this, Add_Student.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Student> updatedList = dbHelper.getAllStudents();
        studentAdapter.updateStudents(updatedList);
    }
}

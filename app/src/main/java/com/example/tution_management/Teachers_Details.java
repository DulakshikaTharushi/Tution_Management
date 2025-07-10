package com.example.tution_management;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Teachers_Details extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addTeacherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Teacher_Details);

        recyclerView = findViewById(R.id.teacherRecyclerView);
        addTeacherButton = findViewById(R.id.addTeacherButton);

        List<TeacherItem> teacherList = new ArrayList<>();
        teacherList.add(new TeacherItem("Dr. Eleanor Vance", "Math, Physics"));
        teacherList.add(new TeacherItem("Prof. Samuel Reed", "Chemistry, Biology"));
        teacherList.add(new TeacherItem("Ms. Olivia Hayes", "History, Geography"));
        teacherList.add(new TeacherItem("Mr. Ethan Carter", "English, Literature"));
        teacherList.add(new TeacherItem("Dr. Sophia Bennett", "Computer Science, IT"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TeacherAdapter(teacherList));

        addTeacherButton.setOnClickListener(v -> {
            // TODO: Handle add teacher action
        });
    }
}

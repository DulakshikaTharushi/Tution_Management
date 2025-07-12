package com.example.tution_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tution_management.Add_Teacher;
import com.example.tution_management.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.tution_management.Database_classes.TeacherAdapter;
import java.util.ArrayList;
import java.util.List;

public class Teachers_Details extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    TeacherAdapter adapter;
    List<String> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_details);

        recyclerView = findViewById(R.id.teacherRecyclerView);
        fab = findViewById(R.id.addCourseButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        teacherList = new ArrayList<>();

        adapter = new TeacherAdapter(teacherList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            startActivity(new Intent(this, Add_Teacher.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTeachersFromPrefs();
    }

    private void loadTeachersFromPrefs() {
        teacherList.clear();

        SharedPreferences prefs = getSharedPreferences("teachers", Context.MODE_PRIVATE);
        int count = prefs.getInt("count", 0);

        for (int i = 0; i < count; i++) {
            String name = prefs.getString("teacher_" + i + "_name", "Unknown");
            teacherList.add(name);
        }

        adapter.notifyDataSetChanged();
    }
}

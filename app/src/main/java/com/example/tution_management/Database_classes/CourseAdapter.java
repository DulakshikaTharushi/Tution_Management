package com.example.tution_management.Database_classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tution_management.R;
import com.example.tution_management.Update_delete_courses;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private List<Course> courseList;
    private Context context;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    // Method to update the list of courses
    public void updateCourses(List<Course> newCourseList) {
        this.courseList = newCourseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textCourseName.setText(course.getCourseName());
        holder.textDescription.setText(course.getCourseDescription());
        holder.textTeacher.setText("Teacher: " + course.getAssignedTeacher());

        // Navigate to Update_delete_courses activity with course details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, com.example.tution_management.Update_delete_courses.class);
            intent.putExtra("COURSE_ID", course.getId());
            intent.putExtra("COURSE_NAME", course.getCourseName());
            intent.putExtra("COURSE_DESCRIPTION", course.getCourseDescription());
            intent.putExtra("ASSIGNED_TEACHER", course.getAssignedTeacher());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView textCourseName, textDescription, textTeacher;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.courseName);
            textDescription = itemView.findViewById(R.id.courseDescription);
            textTeacher = itemView.findViewById(R.id.assignedTeacher);
        }
    }
}

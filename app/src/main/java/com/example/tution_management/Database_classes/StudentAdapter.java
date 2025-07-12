package com.example.tution_management.Database_classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tution_management.Student_Details;  // Make sure this exists
import com.example.tution_management.R;
import com.example.tution_management.Student_Details;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.nameText.setText(student.getName());
        holder.emailText.setText(student.getEmail());
        holder.phoneText.setText(student.getPhone());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void updateStudents(List<Student> updatedStudents) {
        this.studentList = updatedStudents;
        notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameText, emailText, phoneText;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.studentName);
            emailText = itemView.findViewById(R.id.studentEmail);
            phoneText = itemView.findViewById(R.id.studentPhone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Student clickedStudent = studentList.get(position);
                Intent intent = new Intent(context, Student_Details.class);
                intent.putExtra("student_id", clickedStudent.getId());

                if (!(context instanceof Activity)) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
            }
        }
    }
}

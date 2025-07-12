package com.example.tution_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tution_management.Database_classes.Course;
import com.example.tution_management.Database_classes.Student;
import com.example.tution_management.Database_classes.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Tution_Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TuitionManagement.db";
    private static final int DATABASE_VERSION = 1;

    // Courses table
    public static final String TABLE_COURSES = "Courses";
    public static final String COLUMN_COURSE_ID = "id";
    public static final String COLUMN_COURSE_NAME = "courseName";
    public static final String COLUMN_COURSE_DESCRIPTION = "courseDescription";
    public static final String COLUMN_ASSIGNED_TEACHER = "assignedTeacher";

    // Students table
    public static final String TABLE_STUDENTS = "Student";
    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_NAME = "name";
    public static final String COLUMN_STUDENT_EMAIL = "email";
    public static final String COLUMN_STUDENT_PHONE = "phone";

    // Teachers table
    public static final String TABLE_TEACHERS = "Teacher";
    public static final String COLUMN_TEACHER_ID = "id";
    public static final String COLUMN_TEACHER_NAME = "name";
    public static final String COLUMN_TEACHER_PASSWORD = "password";
    public static final String COLUMN_TEACHER_ADDRESS = "address";
    public static final String COLUMN_TEACHER_PHONE = "phone";
    public static final String COLUMN_TEACHER_ASSIGNED_COURSES = "assignedCourses";

    public Tution_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COURSES_TABLE = "CREATE TABLE " + TABLE_COURSES + " ("
                + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COURSE_NAME + " TEXT, "
                + COLUMN_COURSE_DESCRIPTION + " TEXT, "
                + COLUMN_ASSIGNED_TEACHER + " TEXT)";
        db.execSQL(CREATE_COURSES_TABLE);

        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + " ("
                + COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STUDENT_NAME + " TEXT, "
                + COLUMN_STUDENT_EMAIL + " TEXT, "
                + COLUMN_STUDENT_PHONE + " TEXT)";
        db.execSQL(CREATE_STUDENTS_TABLE);

        String CREATE_TEACHERS_TABLE = "CREATE TABLE " + TABLE_TEACHERS + " ("
                + COLUMN_TEACHER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TEACHER_NAME + " TEXT, "
                + COLUMN_TEACHER_PASSWORD + " TEXT, "
                + COLUMN_TEACHER_ADDRESS + " TEXT, "
                + COLUMN_TEACHER_PHONE + " TEXT, "
                + COLUMN_TEACHER_ASSIGNED_COURSES + " TEXT)";
        db.execSQL(CREATE_TEACHERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHERS);
        onCreate(db);
    }

    // ================== COURSE METHODS ==================

    public boolean insertCourse(String courseName, String description, String assignedTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_NAME, courseName);
        values.put(COLUMN_COURSE_DESCRIPTION, description);
        values.put(COLUMN_ASSIGNED_TEACHER, assignedTeacher);

        long result = db.insert(TABLE_COURSES, null, values);
        db.close();
        return result != -1;
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COURSES, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COURSE_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE_DESCRIPTION));
                String teacher = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ASSIGNED_TEACHER));

                courseList.add(new Course(id, name, description, teacher));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;
    }

    public boolean updateCourse(int id, String courseName, String description, String assignedTeacher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_NAME, courseName);
        values.put(COLUMN_COURSE_DESCRIPTION, description);
        values.put(COLUMN_ASSIGNED_TEACHER, assignedTeacher);

        int rows = db.update(TABLE_COURSES, values, COLUMN_COURSE_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    public boolean deleteCourse(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_COURSES, COLUMN_COURSE_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }


    // ================== STUDENT METHODS ==================

    public boolean insertStudent(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, name);
        values.put(COLUMN_STUDENT_EMAIL, email);
        values.put(COLUMN_STUDENT_PHONE, phone);

        long result = db.insert(TABLE_STUDENTS, null, values);
        db.close();
        return result != -1;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_EMAIL));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDENT_PHONE));

                studentList.add(new Student(id, name, email, phone));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }

    // ================== TEACHER METHODS ==================

    public boolean insertTeacher(String name, String password, String address, String phone, String assignedCourses) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEACHER_NAME, name);
        values.put(COLUMN_TEACHER_PASSWORD, password);
        values.put(COLUMN_TEACHER_ADDRESS, address);
        values.put(COLUMN_TEACHER_PHONE, phone);
        values.put(COLUMN_TEACHER_ASSIGNED_COURSES, assignedCourses);

        long result = db.insert(TABLE_TEACHERS, null, values);
        db.close();
        return result != -1;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEACHERS, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_NAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_PASSWORD));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_ADDRESS));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_PHONE));
                String assignedCourses = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_ASSIGNED_COURSES));

                teacherList.add(new Teacher(id, name, password, address, phone, assignedCourses));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return teacherList;
    }

    public Teacher getTeacherById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TEACHERS, null, COLUMN_TEACHER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_NAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_PASSWORD));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_ADDRESS));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_PHONE));
            String assignedCourses = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEACHER_ASSIGNED_COURSES));

            cursor.close();
            db.close();
            return new Teacher(id, name, password, address, phone, assignedCourses);
        }

        cursor.close();
        db.close();
        return null;
    }

    public boolean updateTeacher(int id, String name, String password, String address, String phone, String assignedCourses) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEACHER_NAME, name);
        values.put(COLUMN_TEACHER_PASSWORD, password);
        values.put(COLUMN_TEACHER_ADDRESS, address);
        values.put(COLUMN_TEACHER_PHONE, phone);
        values.put(COLUMN_TEACHER_ASSIGNED_COURSES, assignedCourses);

        int rows = db.update(TABLE_TEACHERS, values, COLUMN_TEACHER_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }

    public boolean deleteTeacher(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_TEACHERS, COLUMN_TEACHER_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows > 0;
    }
}

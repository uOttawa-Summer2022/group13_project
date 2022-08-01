package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class StudentViewAllCourses extends AppCompatActivity implements CourseSubscriber {
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DBFB", " From StudentViewAllCourses constructor");
        //displayCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_view_all_courses);

        final Button backToMenu = findViewById(R.id.backButton);
        fBH = new FireBaseDataBaseHandler();
        fBH.registerCourseSubscriber(this);
        fBH.readStudentCoursesFromFireBase();
        //displayCourses();
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivityStudent = new Intent(StudentViewAllCourses.this,GeneralUserActivity_Student.class);
                startActivity(backToGeneralUserActivityStudent);
            }
        });

    }
    private void displayCourses(){
        Log.d("DBFB", " From viewCoursesStudent displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1,fBH.getCourses());
        final ListView courseListView= findViewById(R.id.viewAllStudentCourses);
        courseListView.setAdapter(adapter);
    }

    @Override
    public void updateCourses() {
        displayCourses();
    }
}

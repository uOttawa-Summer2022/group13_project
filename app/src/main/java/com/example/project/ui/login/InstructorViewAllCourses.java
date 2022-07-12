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

public class InstructorViewAllCourses extends AppCompatActivity implements CourseSubscriber {
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DBFB", " From InstructorViewAllCourses constructor");
        //displayCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses_instructor);

        final Button backToMenu = findViewById(R.id.bckButton);
        fBH = new FireBaseDataBaseHandler();
        fBH.registerCourseSubscriber(this);
        fBH.readCoursesFromFireBase();
        //displayCourses();
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivity = new Intent(InstructorViewAllCourses.this,GeneralUserActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });

    }
    private void displayCourses(){
//        while(!fBH.isCourseCallbackDone()){
//            Log.d("DBFB", " wait viewCoursesInstructor displayCourses");
//        }
        Log.d("DBFB", " From viewCoursesInstructor displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1,fBH.getCourses());
        final ListView courseListView= findViewById(R.id.viewAllCourses);
        courseListView.setAdapter(adapter);
    }

    @Override
    public void updateCourses() {
        displayCourses();
    }
}
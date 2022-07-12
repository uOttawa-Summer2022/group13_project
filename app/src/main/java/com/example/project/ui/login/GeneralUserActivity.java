package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.project.R;
//import com.example.project.ui.login.InstructorViewAllCourses;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class GeneralUserActivity extends AppCompatActivity {

    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_general);
        fBH = new FireBaseDataBaseHandler();
        fBH.readUsersFromFireBase();

        final Button viewCoursesBtn = findViewById(R.id.viewAllCourseBtn);
        final Button searchCourseBtn = findViewById(R.id.searchCodeBtn);
        final Button assignBtn= findViewById(R.id.assignAsInstructorBtn);
        final Button unassignBtn= findViewById(R.id.unassignBtn);
        final Button addCourseDesBtn= findViewById(R.id.addCourseDesBtn);
        final Button editCourseDesBtn = findViewById(R.id.editCourseDes);
        final Button backTologinBtn = findViewById(R.id.backToSignInPage);

        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "View Course");
                Intent viewAllCourses = new Intent(GeneralUserActivity.this, InstructorViewAllCourses.class);
                startActivity(viewAllCourses);
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Search Course");
                Intent searchAllCourses = new Intent(GeneralUserActivity.this, InstructorSearchCourse.class);
                startActivity(searchAllCourses);
            }
        });

        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Assign Instructor Course");
                Intent assignCourse = new Intent(GeneralUserActivity.this, InstructorAssignCourseActivity.class);
                startActivity(assignCourse);
            }
        });

        unassignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Unassign Instructor Course");
                Intent unassignCourse = new Intent(GeneralUserActivity.this, InstructorUnassignCourseActivity.class);
                startActivity(unassignCourse);
            }
        });

        editCourseDesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Add Description Course");
                Intent addDescription = new Intent(GeneralUserActivity.this, InstructorEditDescription.class);
                startActivity(addDescription);
            }
        });

/*
        editCourseDesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Edit Description Course");
                Intent searchAllCoursesI = new Intent(GeneralUserActivity.this,SearchCourseInstructor.class);
                startActivity(searchAllCoursesI);
            }
        });

 */

        backTologinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Logout");
                Intent backToGeneralUserActivity = new Intent(GeneralUserActivity.this, LoginActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });

    }
}
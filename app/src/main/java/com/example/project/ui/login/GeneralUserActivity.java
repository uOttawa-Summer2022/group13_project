package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        final Button backTologinBtn = findViewById(R.id.backToSignInpPage);

        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewAllCoursesI = new Intent(GeneralUserActivity.this, InstructorViewAllCourses.class);
                startActivity(viewAllCoursesI);
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchAllCoursesI = new Intent(GeneralUserActivity.this, InstructorSearchCourse.class);
                startActivity(searchAllCoursesI);
            }
        });

        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent assignCourseI = new Intent(GeneralUserActivity.this,InstructorAssignCourseActivity.class);
                startActivity(assignCourseI);
            }
        });

        unassignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent unassignCourseI = new Intent(GeneralUserActivity.this,InstructorUnassignCourseActivity.class);
                startActivity(unassignCourseI);
            }
        });

        addCourseDesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDescriptionI = new Intent(GeneralUserActivity.this,InstructorAssignDescription.class);
                startActivity(addDescriptionI);
            }
        });

/*
        editCourseDesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchAllCoursesI = new Intent(GeneralUserActivity.this,SearchCourseInstructor.class);
                startActivity(searchAllCoursesI);
            }
        });

 */

        backTologinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivity = new Intent(GeneralUserActivity.this, LoginActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });

    }
}
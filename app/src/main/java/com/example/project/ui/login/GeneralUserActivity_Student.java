package com.example.project.ui.login;

//import android.widget.ArrayAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;


public class GeneralUserActivity_Student extends AdminActivity{
    FireBaseDataBaseHandler fBH;
    //ArrayAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_general);
        fBH = new FireBaseDataBaseHandler();
        fBH.readUsersFromFireBase();

        final Button viewCoursesBtn = findViewById(R.id.viewAllEnrolledCourseBtn);
        final Button searchCourseBtn = findViewById(R.id.searchCourseByEverythingBtn);
        final Button enrollBtn= findViewById(R.id.unenrollBtn);
        final Button unEnrollBtn= findViewById(R.id.unenrollBtn);
        final Button backTologinBtn = findViewById(R.id.backToSignInPage);



        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "View Course student");
                //Change the second param
                Intent viewAllCourses = new Intent(GeneralUserActivity_Student.this, StudentViewAllCourses.class);
                startActivity(viewAllCourses);
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Search Course");
                //Change the second param
                Intent searchAllCourses = new Intent(GeneralUserActivity_Student.this, StudentSearchCourse.class);
                startActivity(searchAllCourses);
            }
        });


        enrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Enroll Student Course");
                //Change the second param
                Intent enrollCourses = new Intent(GeneralUserActivity_Student.this, StudentEnrollCourseActivity.class);
                startActivity(enrollCourses);
            }
        });

        unEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Unenroll Student Course");
                //Change the second param
                Intent unEnrollCourses = new Intent(GeneralUserActivity_Student.this, StudentUnenrollCourseActivity.class);
                startActivity(unEnrollCourses);
            }
        });
        backTologinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Search Course");
                //Change the second param
                Intent backToGeneralUserActivity_Student = new Intent(GeneralUserActivity_Student.this, LoginActivity.class);
                startActivity(backToGeneralUserActivity_Student);
            }
        });






    }


}

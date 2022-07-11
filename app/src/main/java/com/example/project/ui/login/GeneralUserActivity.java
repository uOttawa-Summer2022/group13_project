package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.project.R;
import com.example.project.ViewAllCoursesInstructor;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;
import com.example.project.data.model.User;

public class GeneralUserActivity extends AppCompatActivity {

    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_user);
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
                Intent viewAllCoursesI = new Intent(GeneralUserActivity.this, ViewAllCoursesInstructor.class);
                startActivity(viewAllCoursesI);
            }
        });

        searchCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
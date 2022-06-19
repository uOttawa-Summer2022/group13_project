package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;

import com.example.project.data.model.Course;

public class AdminDeleteCourseActivity extends AppCompatActivity {
    boolean isCodeValid = false;
    boolean isNameValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_course);

        final EditText courseCode = findViewById(R.id.editCourseCourseNumber);
        final EditText courseName = findViewById(R.id.editCourseCourseName);
        final Button deleteBtn = findViewById(R.id.editCourseButton);
        final Button backToMenu = findViewById(R.id.returnToMenu);



        courseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCode.getText().toString()!= null){
                    isCodeValid = true;
                }
                if(enableCreateButton()){
                    deleteBtn.setEnabled(true);
                }
            }
        });



        courseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCode.getText().toString()!= null){
                    isNameValid = true;
                }
                if(enableCreateButton()){
                    deleteBtn.setEnabled(true);
                }
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
            }
        });






        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminDeleteCourseActivity.this,AdminActivity.class);
                startActivity(backToAdminActivity);
            }
        });




    }

    private boolean enableCreateButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }


}

package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class InstructorAssignDescription extends AppCompatActivity{

    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    private boolean isDurationValid=false;
    private boolean isHoursValid = false;
    private boolean isCapacityValid = false;
    private boolean isDescriptionValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_instructor_add_course_description);

        final EditText courseCode = findViewById(R.id.additionCourseNumber);
        final EditText courseName = findViewById(R.id.additionCourseName);
        final EditText courseDescription = findViewById(R.id.description);
        final EditText courseDuration = findViewById(R.id.courseDuration_add);
        final EditText courseHours = findViewById(R.id.courseHours_add);
        final EditText courseCapacity = findViewById(R.id.courseCapacity_add);

        final Button done = findViewById(R.id.done);
        final Button cancel = findViewById(R.id.returnToMenu_addCourseDes);


        courseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(courseCode.getText()!= null && courseCode.getText().toString().matches("[A-Z]{3}[0-9]{4}")) {
                    courseCode.setError(null);
                    isCodeValid = true;
                }
                else{
                    courseCode.setError("Coursecode format should be (i.e): ABC1234");
                    isCodeValid = false;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
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
                if(courseName.getText().toString()!= null){
                    isNameValid = true;
                }
                else{
                    isNameValid = false;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
                }
            }
        });


        courseDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseDuration.getText().toString()!=null  && courseDuration.getText().toString().matches("[0-9][0-9]")){
                    courseDuration.setError(null);
                    isDurationValid=true;
                }else{
                    courseDuration.setError("CourseDuration format should be (i.e): 12");
                    isDurationValid=false;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
                }
            }
        });


        courseHours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseHours.getText().toString()!=null  && courseHours.getText().toString().matches("[0-9][0-9]")){
                    courseHours.setError(null);
                    isHoursValid=true;
                }else{
                    courseHours.setError("CourseHours format should be (i.e): 12");
                    isHoursValid=false;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
                }
            }
        });


        courseCapacity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCapacity.getText().toString()!=null  && courseCapacity.getText().toString().matches("[0-9][0-9][0-9]")){
                    courseCapacity.setError(null);
                    isCapacityValid=true;
                }else{
                    courseCapacity.setError("CourseCapacity format should be (i.e): 123");
                    isCapacityValid=false;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
                }
            }
        });


        courseDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(courseDescription.getText().toString() != null){
                    isDescriptionValid=true;
                }
                if(enableDoneButton()){
                    done.setEnabled(true);
                }
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                course.setCourseDescription(courseDescription.getText().toString());
                course.setCourseDuration(courseDuration.getText().toString());
                course.setCourseHours(courseHours.getText().toString());
                course.setCourseCapacity(courseCapacity.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
                if(fBH.courseCodeExistsInDatabase(course)){
                    //Shabrina changed
                    if(fBH.checkCourseInstructorExists(course)){
                        Log.d("DBFB", "Course exists and description can be added");
                        fBH.addDescriptionToFirebase(course);
                        Toast.makeText(getApplicationContext(), "Description for " + course.getCourseCode() + " has been added" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.d("DBFB", "course has not instructor");
                    }

                   }
                else{
                    Log.d("DBFB", "course not exist");

                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                courseDuration.setText("");
                courseDuration.setError(null);
                courseCapacity.setText("");
                courseCapacity.setError(null);
                courseHours.setText("");
                courseHours.setError(null);
                courseDescription.setText("");
                courseDescription.setError(null);
                isCodeValid = false;
                isNameValid = false;
                isCapacityValid=false;
                isHoursValid=false;
                isDurationValid=false;
                done.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Description " + course.getCourseDescription() + " added to database" , Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "Logout");
                Intent backToGeneralUserActivity = new Intent(InstructorAssignDescription.this, GeneralUserActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });
    }

    private boolean enableDoneButton(){
        if(isCodeValid && isNameValid && isDescriptionValid && isCapacityValid && isHoursValid && isDurationValid){
            return true;
        }
        else{
            return false;
        }
    }
}

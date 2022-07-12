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
import android.widget.ListView;
import android.widget.Toast;


import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;
import com.google.firebase.database.FirebaseDatabase;

public class InstructorAssignDescription extends AppCompatActivity{

    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.add_course_description);
        final EditText courseCode = findViewById(R.id.additionCourseNumber);
        final EditText courseName = findViewById(R.id.additionCourseName);
        final Button done = findViewById(R.id.done);
        final Button cancel = findViewById(R.id.cancel);

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
                if(doneButton()){
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
                if(doneButton()){
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
                ///have to instantiate databse obj and create a course obj and add that to datab
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "Description exists");
                    Toast.makeText(getApplicationContext(), "Description for " + course.getCourseCode() + " already exist! NOT ADDED" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("DBFB", "course not exist");
                    fBH.addDescriptionToFirebase(course);
                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                isCodeValid = false;
                isNameValid = false;
                done.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Description " + course.getCourseDescription() + " added to database" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean doneButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }
}

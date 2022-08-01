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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class StudentUnenrollCourseActivity extends AppCompatActivity {

    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    private boolean isCodeValid = false;
    private boolean isNameValid = false;
    private boolean isDescriptionValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_students_unenroll);

        final EditText courseCode = findViewById(R.id.unenrollCourseCourseNumber);
        final EditText courseName = findViewById(R.id.unenrollCourseCourseName);
        final TextView studentToBeUnEnrolled = findViewById(R.id.StudentTobeUnEnrolled);
        final Button unEnroll = findViewById(R.id.unenrollBtn);
        final Button Cancel = findViewById(R.id.returnToStudentMenu);



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
                if(enableUnEnrollButton()){
                    unEnroll.setEnabled(true);
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
                if(enableUnEnrollButton()){
                    unEnroll.setEnabled(true);
                }
            }
        });

        studentToBeUnEnrolled.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameValid && isCodeValid){
                    isDescriptionValid=true;
                }
                if(enableUnEnrollButton()){
                    unEnroll.setEnabled(true);
                }
            }
        });
    
        
        unEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                course.setCourseStudent(studentToBeUnEnrolled.getText().toString());
                Log.d("DBFB", " From unass:" + studentToBeUnEnrolled.getText().toString());
              
                if(fBH.courseCodeExistsInDatabase(course)){
                
                    Log.d("DBFB", "Course exists and student can be unEnrolled");
                    fBH.unEnrollStudentToCourse(course);
                    Toast.makeText(getApplicationContext(), "Course for " + course.getCourseCode() + " has been set to null" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("DBFB", "course does not exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + " does not exist in and hence student can not be set to null" , Toast.LENGTH_SHORT).show();
                    
                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                isCodeValid = false;
                isNameValid = false;
                unEnroll.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Description " + course.getCourseDescription() + " added to database" , Toast.LENGTH_SHORT).show();
            }

        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivityStudent = new Intent(StudentUnenrollCourseActivity.this,GeneralUserActivity_Student.class);
                startActivity(backToGeneralUserActivityStudent);
            }
        });


    }
    private boolean enableUnEnrollButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }
    }

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

public class InstructorUnassignCourseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_instructor_unassign_course);

        final EditText courseCode = findViewById(R.id.unAssignCourseCourseNumber);
        final EditText courseName = findViewById(R.id.unAssignCourseCourseName);
        final TextView instructoToBeUnAssigned = findViewById(R.id.InstructorTobeUnAssigned);
        final Button unAssign = findViewById(R.id.unassignBtn);
        final Button Cancel = findViewById(R.id.returnToMenu2);



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
                if(enableUnAssignButton()){
                    unAssign.setEnabled(true);
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
                if(enableUnAssignButton()){
                    unAssign.setEnabled(true);
                }
            }
        });

        instructoToBeUnAssigned.addTextChangedListener(new TextWatcher() {
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
                if(enableUnAssignButton()){
                    unAssign.setEnabled(true);
                }
            }
        });
    
        
        unAssign.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
/*                if (courseCode.getText().toString().equals("")) {
                    Toast.makeText(InstructorUnassignCourseActivity.this, "Specify the courseCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (courseName.getText().equals("")) {
                    Toast.makeText(InstructorUnassignCourseActivity.this, "Specify the courseName", Toast.LENGTH_SHORT).show();
                    return;
                }

                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());

                if (fBH.courseCodeExistsInDatabase(course)) {
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found !!", Toast.LENGTH_SHORT).show();

                } else {
                    Log.d("DBFB", "Course with" + course.getCourseCode() + "do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                course.setCourseInstructor(instructoToBeUnAssigned.getText().toString());
                Log.d("DBFB", " From unass:" + instructoToBeUnAssigned.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
                if(fBH.courseCodeExistsInDatabase(course)){
                    //Shabrina changed
                    Log.d("DBFB", "Course exists and instructor can be unAssigned");
                    fBH.unAssignInstructorToCourse(course);
                    Toast.makeText(getApplicationContext(), "Instructor for " + course.getCourseCode() + " has been set to null" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("DBFB", "course does not exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + " does not exist in and hence Instructor can not be set to null" , Toast.LENGTH_SHORT).show();
                    //fBH.addDescriptionToFirebase(course);
                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                isCodeValid = false;
                isNameValid = false;
                unAssign.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Description " + course.getCourseDescription() + " added to database" , Toast.LENGTH_SHORT).show();
            }

        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivity = new Intent(InstructorUnassignCourseActivity.this,GeneralUserActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });


    }
    private boolean enableUnAssignButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }
    }

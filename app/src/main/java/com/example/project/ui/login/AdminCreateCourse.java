package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCreateCourse extends AppCompatActivity {
    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;


  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_admin_create_course);
        final EditText courseCode = findViewById(R.id.createCourseCourseNumber);
        final EditText courseName = findViewById(R.id.createCourseCourseName);
        final Button createBtn = findViewById(R.id.createCourseSubmitButton);
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
                if(courseCode.getText()!= null && courseCode.getText().toString().matches("[A-Z]{3}[0-9]{4}")) {
                    courseCode.setError(null);
                    isCodeValid = true;
                    //courseCode.setError("");
                }else{
                    courseCode.setError("Coursecode format should be (i.e): ABC1234");
                    isCodeValid = false;
                   // Toast.makeText(AdminCreateCourse.this, "Coursecode format should be (i.e): ABC1234", Toast.LENGTH_SHORT).show();


                }
                if(enableCreateButton()){
                    createBtn.setEnabled(true);
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
                if(enableCreateButton()){
                    createBtn.setEnabled(true);
                }
            }
        });


        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + " already exist! NOT ADDED" , Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("DBFB", "course not exist");
                    fBH.addCourseToFireBase(course);
                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                isCodeValid = false;
                isNameValid = false;
                createBtn.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + " added to database" , Toast.LENGTH_SHORT).show();
            }
        });






        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminCreateCourse.this,AdminActivity.class);
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

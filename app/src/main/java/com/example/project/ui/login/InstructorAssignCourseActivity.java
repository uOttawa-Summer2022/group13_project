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

public class InstructorAssignCourseActivity extends AppCompatActivity{

    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_instructor_assign_course);

        final EditText courseCode = findViewById(R.id.assignCourseNumber);
        final EditText courseName = findViewById(R.id.assignCourseName);
        final Button Assign = findViewById(R.id.assignBtn);
        final Button Cancel = findViewById(R.id.returnToMenu);
    
        
        Assign.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                if (courseCode.getText().toString().equals("")) {
                    Toast.makeText(InstructorAssignCourseActivity.this, "Specify the courseCode", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (courseName.getText().equals("")) {
                    Toast.makeText(InstructorAssignCourseActivity.this, "Specify the courseName", Toast.LENGTH_SHORT).show();
                    return;
                }
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                if (fBH.courseCodeExistsInDatabase(course)) {
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found !!", Toast.LENGTH_SHORT).show();
                    fBH.editCourseName(course);
                } else {
                    Log.d("DBFB", "Course with" + course.getCourseCode() + "do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivity = new Intent(InstructorAssignCourseActivity.this,GeneralUserActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });

    }
}

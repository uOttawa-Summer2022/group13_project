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
    //ArrayAdapter<Course> adapter;
    private boolean isCodeValid = false;
    private boolean isNameValid = false;
    private boolean isInstructorNameValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_instructor_assign_course);

        final EditText courseCode = findViewById(R.id.assignCourseNumber);
        final EditText courseName = findViewById(R.id.assignCourseName);
        final EditText instructorName = findViewById(R.id.assignInstructor);
        final Button AssignBtn = findViewById(R.id.assignBtn);
        final Button Cancel = findViewById(R.id.returnToMenu);




        courseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fBH.readCoursesFromFireBase();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fBH.readCoursesFromFireBase();
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
                if(enableAssignButton()){
                    AssignBtn.setEnabled(true);
                }
            }
        });

        instructorName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fBH.readCoursesFromFireBase();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fBH.readCoursesFromFireBase();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(instructorName.getText().toString()!= null){
                    isInstructorNameValid = true;
                }else{
                    isInstructorNameValid = false;
                }

                if(enableAssignButton()){
                    AssignBtn.setEnabled(true);
                }
            }
        });

        courseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                fBH.readCoursesFromFireBase();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fBH.readCoursesFromFireBase();
            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(courseName.getText().toString()!= null){
                        isNameValid = true;
                    }
                    else{
                        isNameValid = false;
                    }
                    if(enableAssignButton()){
                        AssignBtn.setEnabled(true);
                    }
            }
        });




        AssignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
/*                if (courseCode.getText().toString().equals("")) {
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
                }*/
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                course.setCourseInstructor(instructorName.getText().toString());
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "****course exist :"+ fBH.checkCourseInstructorExists(course));
                   // Log.d("DBFB", "SetCourse instructor field :"+ fBH.checkCourseInstructorExists(course));
                    //Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found and course exist and instructor can be assigned !!", Toast.LENGTH_SHORT).show();
                    if (fBH.checkCourseInstructorExists(course)){
                        Log.d("DBFB", "course exist and instructor can be assigned ");
                        Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found and course exist and instructor can be assigned !!", Toast.LENGTH_SHORT).show();
                        fBH.assignInstructorToCourse(course);
                    }else{
                        Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  is not found but instructor already exist and instructor can not be assigned !!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("DBFB", "course does not exist and instructor can be assigned ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  is not found and course exist and instructor can not be assigned !!", Toast.LENGTH_SHORT).show();
                }


                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                instructorName.setText("");
                AssignBtn.setEnabled(false);

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

    private boolean enableAssignButton() {
        if(isCodeValid && isNameValid && isInstructorNameValid){
            return true;
        }
        else{
            return false;
        }

    }
}

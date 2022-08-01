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

public class StudentEnrollCourseActivity extends AppCompatActivity{

    FireBaseDataBaseHandler fBH;
    //ArrayAdapter<Course> adapter;
    private boolean isCodeValid = false;
    private boolean isNameValid = false;
    private boolean isStudentNameValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_students_enroll);

        final EditText courseCode = findViewById(R.id.enrollCourseNumber);
        final EditText courseName = findViewById(R.id.enrollCourseName);
        final EditText studentName = findViewById(R.id.enrollStudent);
        final Button EnrollBtn = findViewById(R.id.enrollBtn);
        final Button Cancel = findViewById(R.id.returnToStudentMenu);




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
                if(enableEnrollButton()){
                    EnrollBtn.setEnabled(true);
                }
            }
        });

        studentName.addTextChangedListener(new TextWatcher() {
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
                if(studentName.getText().toString()!= null){
                    isStudentNameValid = true;
                }else{
                    isStudentNameValid = false;
                }

                if(enableEnrollButton()){
                    EnrollBtn.setEnabled(true);
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
                    if(enableEnrollButton()){
                        EnrollBtn.setEnabled(true);
                    }
            }
        });




        EnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                course.setCourseStudent(studentName.getText().toString());
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "****course exist :"+ fBH.checkCourseStudentExists(course));
                    if (fBH.checkCourseStudentExists(course)){
                        Log.d("DBFB", "course exist");

                        if(!fBH.courseConflict(studentName.getText().toString())){
                            Log.d("DBFB", "No conflict. Student can join");
                            Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found and course exist and student can be assigned !!", Toast.LENGTH_SHORT).show();
                            fBH.enrollStudentToCourse(course);
                        }
                        else{
                            Log.d("DBFB","Conflict. Student cannot join because schedule mismatch");
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  is not found but student already exist and student can not be assigned !!", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Log.d("DBFB", "course does not exist and student can be assigned ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  is not found and course exist and student can not be assigned !!", Toast.LENGTH_SHORT).show();
                }


                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                studentName.setText("");
                EnrollBtn.setEnabled(false);

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToStudentMenuActivity = new Intent(StudentEnrollCourseActivity.this,GeneralUserActivity_Student.class);
                startActivity(backToStudentMenuActivity);
            }
        });

    }

    private boolean enableEnrollButton() {
        if(isCodeValid && isNameValid && isStudentNameValid){
            return true;
        }
        else{
            return false;
        }

    }
}

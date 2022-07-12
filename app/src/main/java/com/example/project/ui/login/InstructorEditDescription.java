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

public class InstructorEditDescription extends AppCompatActivity{
    boolean isCodeValid = false;
    boolean isNameValid = false;
    boolean isDescriptionValid = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_instructor_edit_course_description);

        final EditText courseCode2 = findViewById(R.id.courseCode2);
        final EditText courseName2 = findViewById(R.id.courseName2);
        final EditText newDescription = findViewById(R.id.description2);
        final Button done2 = findViewById(R.id.done2);
        final Button cancel2 = findViewById(R.id.returnToMenu3);

        courseCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(courseCode2.getText()!= null && courseCode2.getText().toString().matches("[A-Z]{3}[0-9]{4}")) {
                    courseCode2.setError(null);
                    isCodeValid = true;
                    //courseCode.setError("");
                }else{
                    courseCode2.setError("Coursecode format should be (i.e): ABC1234");
                    isCodeValid = false;
                    // Toast.makeText(AdminCreateCourse.this, "Coursecode format should be (i.e): ABC1234", Toast.LENGTH_SHORT).show();


                }
                if(enableCreateButton()){
                    done2.setEnabled(true);
                }
            }
        });

        courseCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCode2.getText().toString()!= null){
                    isNameValid = true;
                }
                if(enableCreateButton()){
                    done2.setEnabled(true);
                }
            }
        });

        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course();
                course.setCourseCode(courseCode2.getText().toString());
                course.setCourseName(courseName2.getText().toString());
                course.setCourseDescription(newDescription.getText().toString());
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found !!" , Toast.LENGTH_SHORT).show();
                    fBH.editDescriptionOnFireBase(course);
                }else{
                    Log.d("DBFB", "Course with"+course.getCourseCode()+"do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database" , Toast.LENGTH_SHORT).show();
                }
                courseCode2.setText("");
                courseCode2.setError(null);
                courseName2.setText("");
                isCodeValid = false;
                isNameValid = false;
                done2.setEnabled(false);
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(InstructorEditDescription.this,GeneralUserActivity.class);
                startActivity(backToAdminActivity);
            }
        });
    }

    private boolean enableCreateButton(){
        if(isCodeValid && isNameValid && isDescriptionValid){
            return true;
        }
        else{
            return false;
        }
    }


}

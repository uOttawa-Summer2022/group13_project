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
    boolean isDurationValid = false;
    boolean isHoursValid = false;
    boolean isCapacityValid = false;

    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_instructor_edit_course_description);

        final EditText courseCode2 = findViewById(R.id.courseCode2);
        final EditText courseName2 = findViewById(R.id.courseName2);
        final EditText courseDuration = findViewById(R.id.courseDuration);
        final EditText courseHours = findViewById(R.id.courseHours);
        final EditText courseCapacity = findViewById(R.id.courseCapacity);
        final EditText newDescription = findViewById(R.id.description2);
        final Button done2 = findViewById(R.id.done2);
        final Button cancel2 = findViewById(R.id.returnToMenu_editCourseDes);

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
                if(enableDoneButton()){
                    done2.setEnabled(true);
                }
            }
        });

        courseName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseName2.getText().toString()!= null){
                    isNameValid = true;
                }
                else{
                    isNameValid = false;
                }
                if(enableDoneButton()){
                    done2.setEnabled(true);
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
                    done2.setEnabled(true);
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
                    done2.setEnabled(true);
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
                    done2.setEnabled(true);
                }
            }
        });



        newDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
/*                if(newDescription.getText().toString()!= null){
                    isNameValid = true;
                }*/
                //Shabrina Changed
                if(isNameValid && isCodeValid){
                    isDescriptionValid=true;
                }
                if(enableDoneButton()){
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
                course.setCourseDuration(courseDuration.getText().toString());
                course.setCourseCapacity(courseCapacity.getText().toString());
                course.setCourseHours(courseHours.getText().toString());
                if(fBH.courseCodeExistsInDatabase(course)){
                    if(fBH.checkCourseInstructorExists(course)){
                        Log.d("DBFB", "course exist and description can be edited");
                        Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found and description can be edited!!" , Toast.LENGTH_SHORT).show();
                        fBH.editDescriptionOnFireBase(course);
                    }
                    else{
                        Log.d("DBFB", "Course with"+course.getCourseCode()+"has not instructor ");
                        Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  has not instructor" , Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Log.d("DBFB", "Course with "+course.getCourseCode()+" do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database" , Toast.LENGTH_SHORT).show();
                }
                courseCode2.setText("");
                courseCode2.setError(null);
                courseCapacity.setText("");
                courseCapacity.setError(null);
                courseDuration.setText("");
                courseDuration.setError(null);
                courseHours.setText("");
                courseHours.setError(null);
                courseName2.setText("");
                newDescription.setText("");
                newDescription.setError(null);
                isCodeValid = false;
                isNameValid = false;
                isDurationValid=false;
                isHoursValid=false;
                isCapacityValid=false;
                isDescriptionValid=false;
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

    private boolean enableDoneButton(){
        if(isCodeValid && isNameValid && isDescriptionValid && isCapacityValid && isHoursValid && isDurationValid){
            return true;
        }
        else{
            return false;
        }
    }


}

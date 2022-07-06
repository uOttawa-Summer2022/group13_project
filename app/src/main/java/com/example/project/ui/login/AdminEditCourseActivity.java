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

public class AdminEditCourseActivity extends AppCompatActivity {

    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_admin_edit_course);

        final EditText courseCode = findViewById(R.id.editCourseCourseNumber);
        final EditText courseName = findViewById(R.id.editCourseCourseName);
        final Button updateBtn = findViewById(R.id.editCourseSubmitButton);
        final Button backToMenu = findViewById(R.id.returnToMenu);
        final ListView listView = findViewById(R.id.editCourseListView);



        courseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                displayCourses();
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
                    updateBtn.setEnabled(true);
                }
            }
        });



        courseName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                displayCourses();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCode.getText().toString()!= null){
                    isNameValid = true;
                }
                if(enableCreateButton()){
                    updateBtn.setEnabled(true);
                }
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found !!" , Toast.LENGTH_SHORT).show();
                    fBH.editCourseName(course);
                }else{
                    Log.d("DBFB", "Course with"+course.getCourseCode()+"do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database" , Toast.LENGTH_SHORT).show();
                }
                courseCode.setText("");
                courseCode.setError(null);
                courseName.setText("");
                isCodeValid = false;
                isNameValid = false;
                updateBtn.setEnabled(false);
                Toast.makeText(getApplicationContext(), "CourseName for " + course.getCourseCode() + " has been changed" , Toast.LENGTH_SHORT).show();
            }
        });






        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminEditCourseActivity.this,AdminActivity.class);
                startActivity(backToAdminActivity);
            }
        });


            displayCourses();

    }

    private void displayCourses() {
        Log.d("DBFB", "displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, fBH.getCourses());
        final ListView listView = findViewById(R.id.editCourseListView);
        listView.setAdapter(adapter);
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


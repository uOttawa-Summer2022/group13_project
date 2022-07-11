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





public class AdminDeleteCourseActivity extends AppCompatActivity {
    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_admin_delete_course);


        Log.d("DBFB", "from deleteCourseActivity");

        final EditText courseCode = findViewById(R.id.deleteCourseCourseNumber);
        final EditText courseName = findViewById(R.id.deleteCourseCourseName);
        final Button deleteBtn = findViewById(R.id.deleteCourseSubmitButton);
        final Button backToMenu = findViewById(R.id.returnToMenu);

        final ListView listView = findViewById(R.id.deleteCourseListView);




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
                Log.d("DBFB", "from deleteCourseActivity afterTextChanged");
                if(courseCode.getText()!= null && courseCode.getText().toString().matches("[A-Z]{3}[0-9]{4}")) {
                    courseCode.setError(null);
                    isCodeValid = true;
                    courseCode.setError(null);
                }else{
                    courseCode.setError("Coursecode format should be (i.e): ABC1234");
                    isCodeValid = false;
                    // Toast.makeText(AdminCreateCourse.this, "Coursecode format should be (i.e): ABC1234", Toast.LENGTH_SHORT).show();


                }
                if(enableCreateButton()){
                    deleteBtn.setEnabled(true);
                }
            }
        });



        courseName.addTextChangedListener(new TextWatcher() {
            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after){
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
                    deleteBtn.setEnabled(true);
                }
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "from deleteCourseActivity setonclicklistener");
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to database
                if(fBH.courseCodeExistsInDatabase(course)){
                    Log.d("DBFB", "course exist");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  found !!" , Toast.LENGTH_SHORT).show();
                    fBH.deleteCourse(course);
                    Toast.makeText(getApplicationContext(), "The " + course.getCourseCode() + "with course name" + course.getCourseName()+" has been deleted" , Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("DBFB", "Course with"+course.getCourseCode()+"do not exist ");
                    Toast.makeText(getApplicationContext(), "Course " + course.getCourseCode() + "  do not exist in the database" , Toast.LENGTH_SHORT).show();
                }
                courseCode.setText("");
                courseName.setText("");
                courseCode.setError(null);
                deleteBtn.setEnabled(false);

            }

        });






        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminDeleteCourseActivity.this,AdminActivity.class);
                startActivity(backToAdminActivity);
            }
        });




    }

    private void displayCourses() {
        Log.d("DBFB", "displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, fBH.getCourses());
        final ListView listView = findViewById(R.id.deleteCourseListView);
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


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

import java.util.ArrayList;

public class InstructorSearchCourse extends AppCompatActivity implements CourseSubscriber {

    boolean isCodeValid = false;
    boolean isNameValid = false;
    boolean searchPressed = false;
    boolean courseListUpdated = false;
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;



    String courseNameToSearch = null;
    String courseCodeToSearch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course_instructor);


        fBH = new FireBaseDataBaseHandler();
        fBH.registerCourseSubscriber(this);
        fBH.readCoursesFromFireBase();




        final Button searchButton = findViewById(R.id.searchBtn2);
        final Button backToMenu = findViewById(R.id.backButton);
        final ListView listView = findViewById(R.id.searchCourseListView);
        final EditText courseCode = findViewById(R.id.editTextCourseCode);
        final EditText courseName = findViewById(R.id.editTextCourseName);
        searchButton.setEnabled(false);

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
                if(enableSearchButton()){
                    searchButton.setEnabled(true);
                }else{
                    searchButton.setEnabled(false);
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
                if(enableSearchButton()) {
                    searchButton.setEnabled(true);
                }else{
                    searchButton.setEnabled(false);
                }

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "searchBuy start");
                courseCodeToSearch = courseCode.getText().toString();
                courseNameToSearch = courseName.getText().toString();
                searchButton.setEnabled(false);
                searchPressed = true;
                if(courseListUpdated){
                    displaySearchedCourses();
                }
                courseName.setText("");
                courseCode.setText("");
                Log.d("DBFB", "searchBuy done");
            }
        });
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToGeneralUserActivity = new Intent(InstructorSearchCourse.this,GeneralUserActivity.class);
                startActivity(backToGeneralUserActivity);
            }
        });

    }

    private void displaySearchedCourses() {
        Log.d("DBFB", "displayCourses");
        ArrayList<Course> searchedCourseResult= fBH.searchForCourse(courseCodeToSearch, courseNameToSearch);


        if (searchedCourseResult.size() == 0){
            Toast.makeText(getApplicationContext(), "The CourseCode " + courseCodeToSearch + " and course name " + courseNameToSearch +
                    " not found", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), " Course found!", Toast.LENGTH_SHORT).show();
        }

        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1, searchedCourseResult);
        final ListView listView = findViewById(R.id.searchCourseListView);
        listView.setAdapter(adapter);
        courseCodeToSearch = null;
        courseNameToSearch = null;
        searchPressed = false;

    }

    private boolean enableSearchButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updateCourses() {
        Log.d("DBFB", "from search updateCourses");
        courseListUpdated = true;
        if(searchPressed == true){
            displaySearchedCourses();


        }

    }


}
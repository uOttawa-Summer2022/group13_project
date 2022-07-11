package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class ViewAllCoursesInstructor extends AppCompatActivity {
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DBFB", " From ViewAllCoursesInstructor constructor");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses_instructor);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        displayCourses();

      // final ListView courseListView = findViewById(R.id.viewAllCoursesList);
    }

    private void displayCourses(){
        while(!fBH.isCourseCallbackDone()){
            Log.d("DBFB", " wait viewCoursesInstructor displayCourses");
        }
        Log.d("DBFB", " From viewCoursesInstructor displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1,fBH.getCourses());
        final ListView courseListView= findViewById(R.id.viewAllCoursesList);
        courseListView.setAdapter(adapter);
    }


}
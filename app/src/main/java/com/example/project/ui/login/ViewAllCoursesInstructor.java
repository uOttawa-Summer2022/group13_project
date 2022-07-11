package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;

public class ViewAllCoursesInstructor extends AppCompatActivity implements CourseSubscriber {
    FireBaseDataBaseHandler fBH;
    ArrayAdapter<Course> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DBFB", " From ViewAllCoursesInstructor constructor");
        //displayCourses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_courses_instructor);

        setContentView(R.layout.activity_view_all_courses_instructor);
        fBH = new FireBaseDataBaseHandler();
        fBH.registerCourseSubscriber(this);
        fBH.readCoursesFromFireBase();
        //displayCourses();

    }
    private void displayCourses(){
//        while(!fBH.isCourseCallbackDone()){
//            Log.d("DBFB", " wait viewCoursesInstructor displayCourses");
//        }
        Log.d("DBFB", " From viewCoursesInstructor displayCourses");
        adapter = new ArrayAdapter<Course>(this, android.R.layout.simple_list_item_1,fBH.getCourses());
        final ListView courseListView= findViewById(R.id.viewAllCourses);
        courseListView.setAdapter(adapter);
    }

    @Override
    public void updateCourses() {
        displayCourses();
    }
}
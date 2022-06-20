package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
     EditText courseName, courseCode;
     ListView courseListView;

    ArrayList<String> courseList;
    ArrayAdapter adapter;
    AdminCreateCourse adminCreateCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final Button createC = findViewById(R.id.createCourseButton);
        final Button editC = findViewById(R.id.editCourseButton);
        final Button deleteC = findViewById(R.id.deleteCourseButton);
        final Button manageB = findViewById(R.id.manage_user_account);

        manageB.setEnabled(false);
        
        courseList = new ArrayList<>();

        courseName = (EditText)findViewById(R.id.courseName);
        courseCode = (EditText)findViewById(R.id.courseCode);
        
        courseListView = findViewById(R.id.courseListView);

        adminCreateCourse = new AdminCreateCourse(this);
        createC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createCourseIntent = new Intent(AdminActivity.this, AdminCreateCourse.class);
                startActivity(createCourseIntent);
            }
        });
      
        editC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ediCourseIntent = new Intent(AdminActivity.this, AdminEditCourseActivity.class);
                startActivity(ediCourseIntent);
            }
        });
      
        deleteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "delete button clicked");
                Intent deleteCourseIntent = new Intent(AdminActivity.this, AdminDeleteCourseActivity.class);
                startActivity(deleteCourseIntent);
            }
        });

        manageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DBFB", "userM button clicked");
                Intent userAdIntent = new Intent(AdminActivity.this, AdminUserAccountManagement.class);
                startActivity(userAdIntent);
            }
        });
         
         private void viewCourse() {
        courseList.clear();
        Cursor cursor = adminCreateCourse.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                courseList.add(cursor.getString(1) + " (" +cursor.getString(2)+")");
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
        courseListView.setAdapter(adapter);
    }
         
        private void createCourse(View view){
            AdminCreateCourse admincreateCourse = new AdminCreateCourse(this);

            int code = Integer.parseInt(courseCode.getText().toString());

            Course course = new Course(courseName.getText().toString(), code);

            AdminCreateCourse.createCourse(course);


            courseName.setText("");
            courseCode.setText("");
        }

    }
}

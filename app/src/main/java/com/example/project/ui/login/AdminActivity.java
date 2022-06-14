package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final Button createC = findViewById(R.id.createCourseButton);
        final Button editC = findViewById(R.id.editCourseButton);
        final Button deleteC = findViewById(R.id.deleteCourseButton);
        final Button manageB = findViewById(R.id.manage_user_account);
        manageB.setEnabled(false);
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
                Intent deleteCourseIntent = new Intent(AdminActivity.this, AdminDeleteCourseActivity.class);
                startActivity(deleteCourseIntent);
            }
        });
    }
}
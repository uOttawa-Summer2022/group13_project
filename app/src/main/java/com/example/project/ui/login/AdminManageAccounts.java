package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCreateCourse extends AppCompatActivity {
    boolean isCodeValid = false;
    boolean isNameValid = false;
    FireBaseDataBaseHandler fBH;


  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fBH = new FireBaseDataBaseHandler();
        fBH.readCoursesFromFireBase();
        setContentView(R.layout.activity_admin_manage_accounts);
        final EditText editStudent = findViewById(R.id.editStudentAccount);
        final EditText editIntructor = findViewById(R.id.editInstructorAccount);
        final Button ManageBtn = findViewById(R.id.ManageAccountSubmitButton);
        final Button backToMenu = findViewById(R.id.returnToMenu);



        editStudent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              
        });



        editInstructor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              
        });


        ManageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              
        });






        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToAdminActivity = new Intent(AdminCreateCourse.this,AdminActivity.class);
                startActivity(backToAdminActivity);
            }
        });




    }

 

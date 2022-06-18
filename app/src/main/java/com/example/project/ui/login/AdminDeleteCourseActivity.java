package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;

import com.example.project.data.model.Course;

public class AdminDeleteCourseActivity extends AppCompatActivity {
    boolean isCodeValid = false;
    boolean isNameValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_course);

        final EditText courseCode = findViewById(R.id.editCourseCourseNumber);
        final EditText courseName = findViewById(R.id.editCourseCourseName);
        final Button deleteBtn = findViewById(R.id.editCourseButton);
        final Button backToMenu = findViewById(R.id.returnToMenu);



        courseCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(courseCode.getText().toString()!= null){
                    isCodeValid = true;
                }
                if(enableCreateButton()){
                    deleteBtn.setEnabled(true);
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
                Course course = new Course();
                course.setCourseCode(courseCode.getText().toString());
                course.setCourseName(courseName.getText().toString());
                ///have to instantiate databse obj and create a course obj and add that to datab
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

    private boolean enableCreateButton(){
        if(isCodeValid && isNameValid){
            return true;
        }
        else{
            return false;
        }
    }

/**
package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminDeleteCourseActivity extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COURSE_NAME = "name";
    private static final String COLUMN_COURSE_CODE = "code";
    private static final String DATABASE_NAME = "CourseDelete.db";
    private static final int DATABASE_VERSION = 1;

    public AdminDeleteCourseActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String create_table_cmd = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + "INTEGER PRIMARY KEY, " +
                COLUMN_COURSE_NAME + " TEXT, " +
                COLUMN_COURSE_CODE + " INT" + ")";

        db.execSQL(create_table_cmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null); // returns "cursor" all products from the table
    }




    public boolean deleteCourse(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_COURSE_NAME + " = \"" + courseName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);

            db.delete(TABLE_NAME,COLUMN_ID + " = " + idStr, null);
            result = true;
        }
        db.close();
        return result;
    }
}


//public class AdminDeleteCourseActivity extends AppCompatActivity {

    @Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_admin_delete_course);
   // }
**/
}

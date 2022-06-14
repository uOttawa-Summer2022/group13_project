package com.example.project.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.R;

public class AdminCreateCourse extends AppCompatActivity {
    private static final String TABLE_NAME = "courses";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "code";
    private static final String DATABASE_NAME = "adminCreateCourse.db";
    private static final int DATABASE_VERSION = 1;

    public adminCreateCourse(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_course);
    }
}

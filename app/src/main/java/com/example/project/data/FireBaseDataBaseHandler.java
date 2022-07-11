package com.example.project.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project.data.model.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseDataBaseHandler {

    private FirebaseDatabase myDataBase;
    private DatabaseReference myRootRef ;
    private final String COURSE_NAME = "CourseName";
    private final String COURSE_INSTRUCTOR = "CourseInstructor";
    private ArrayList<Course> courses;



    public FireBaseDataBaseHandler(){
        myDataBase = FirebaseDatabase.getInstance();
        myRootRef = myDataBase.getReference();
        courses = new ArrayList<Course>();
    }

    public void readCoursesFromFireBase(){
       myRootRef.child("Courses").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               courses.clear();
               for(DataSnapshot d: dataSnapshot.getChildren() ){
                   Course c = new Course(d.child(COURSE_NAME).getValue().toString(),d.getKey());
                   courses.add(c);
                   Log.d("DBFB", c.toString() + " and the courses saved size is " + courses.size());
               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });



    }

    public void addCourseToFireBase(Course course){
        myRootRef.child("Courses").child(course.getCourseCode()).child(COURSE_NAME).setValue(course.getCourseName());
    }
    
    public boolean checkCourseInstructorExists(Course course){
        for (Course c: courses){
            Log.d("DBFB", "Check!!!!! i is" + i.toString() + "instructor is" + course.toString());
                if(course.getCourseInstructor().equals(c.(getCourseInstructor())){
                    return true;
                }
            }
    }
    
    public boolean addCourseInstructor(Course course){
        Log.d("DBFB", "editInstructor done");
        myRootRef.child("Courses").child(course.getCourseInstructor()).child(INSTRUCTOR_NAME).setValue(course.getCourseInstructor());
        
    }
                   
     public boolean deleteCourseInstructor(Course course){
        Log.d("DBFB", "from databasehandler deleteInstructor");
        myRootRef.child("Courses").child(course.getCourseInstructor()).setValue(null);
        
    }               
                   
    

    public void editData(Course course){

    }

    public boolean courseCodeExistsInDatabase(Course course) {
        for (Course c: courses) {
            Log.d("DBFB", "Check!!!!! c is" + c.toString() + " course is " + course.toString());
                if(course.getCourseCode().equals(c.getCourseCode())){
                    return true;
                }
        }
        return false;
    }
    public ArrayList<Course> getCourses(){
        return this.courses;
    }
}

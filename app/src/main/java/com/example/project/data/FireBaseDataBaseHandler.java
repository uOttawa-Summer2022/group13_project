package com.example.project.data;

import static java.lang.Thread.*;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project.data.model.Course;
import com.example.project.data.model.User;
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
    private final String USER_NAME = "UserName";
    private final String FIRST_NAME = "FirstName";
    private final String LAST_NAME = "LastName";
    private final String PASSWORD = "Password";
    private final String ROLE = "Role";
    private ArrayList<Course> courses;
    private ArrayList<User> users;



    private boolean isUsersCallbackDone = false;



    public FireBaseDataBaseHandler(){
        myDataBase = FirebaseDatabase.getInstance();
        myRootRef = myDataBase.getReference();
        courses = new ArrayList<Course>();
        users = new ArrayList<User>();

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
    public void readUsersFromFireBase(){
        myRootRef.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                isUsersCallbackDone= true;
                users.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    //String.valueOf()
                    //User u = new User(d.child(USER_NAME).getValue().toString(),"", "","","");
                            //d.child(FIRST_NAME).getValue().toString(), d.child(LAST_NAME).getValue().toString(),
                    //d.child(PASSWORD).getValue().toString(), User.convertToRole(d.child("roleAsString").getValue().toString()) );
                    User u = dataSnapshot.child(d.getKey()).getValue(User.class);
                    users.add(u);
                    Log.d("DBFB", u.toString() + " and the user saved size is " + users.size());

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
    public void addUserToFireBase(User u){
        Log.d("DBFB", "addUser");
        String key = myRootRef.child("Users").push().getKey();
        myRootRef.child("Users").child(key).setValue(u);
        Log.d("DBFB", "addUserDone");
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
    public boolean userExistsInDatabase(User user) {

        for (User u: users) {
            Log.d("DBFB", "Check!!!!! u is" + u.toString() + " user is " + user.toString());
            if(user.getUserName().equals(u.getUserName())){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Course> getCourses(){
        return this.courses;
    }
    public ArrayList<User> getUsers(){
        return this.users;
    }
    public boolean isUsersCallbackDone() {
        return isUsersCallbackDone;
    }
}

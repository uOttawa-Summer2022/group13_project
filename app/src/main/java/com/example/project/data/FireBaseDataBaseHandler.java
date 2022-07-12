package com.example.project.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project.data.model.Course;
import com.example.project.data.model.User;
import com.example.project.ui.login.CourseSubscriber;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FireBaseDataBaseHandler {

    private FirebaseDatabase myDataBase;
    private DatabaseReference myRootRef ;
    private final String COURSE_NAME = "CourseName";
    private final String COURSE_INSTRUCTOR = "CourseInstructor";
    private final String COURSE_DESCRIPTION = "CourseDescription";
    private final String USER_NAME = "UserName";  //user name denotes the email
    private final String FIRST_NAME = "FirstName";
    private final String LAST_NAME = "LastName";
    private final String PASSWORD = "Password";
    private final String ROLE = "Role";
    private ArrayList<Course> courses;
    private Map<String, User> users;



    private boolean isUsersCallbackDone = false;
    private boolean isCourseCallbackDone = false;
    private CourseSubscriber cScriber = null;



    public FireBaseDataBaseHandler(){
        myDataBase = FirebaseDatabase.getInstance();
        myRootRef = myDataBase.getReference();
        courses = new ArrayList<Course>();
        users = new HashMap<String , User>();

    }

    public void readCoursesFromFireBase(){
       myRootRef.child("Courses").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               isCourseCallbackDone = true;
               if(cScriber != null){
                   cScriber.updateCourses();
               }
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
        Log.d("DBFB", "readUsersFromFire");
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
                    users.put(d.getKey().toString(), u);
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

    public void addDescriptionToFirebase(Course course){
        myRootRef.child("Description").child(course.getCourseDescription()).child(COURSE_DESCRIPTION).setValue(course.getCourseDescription());
    }

    public void editDescriptionOnFireBase(Course course){
        myRootRef.child("Description").child(course.getCourseDescription()).child(COURSE_DESCRIPTION).setValue(course.getCourseDescription());
    }


    public void addUserToFireBase(User u){
        Log.d("DBFB", "addUser");
        String key = myRootRef.child("Users").push().getKey();
        myRootRef.child("Users").child(key).setValue(u);
        Log.d("DBFB", "addUserDone");
    }

    public void editCourseName(Course course){
        myRootRef.child("Courses").child(course.getCourseCode()).child(COURSE_NAME).setValue(course.getCourseName());
    }


    public void deleteCourse(Course course){
        Log.d("DBFB", "from databasehandler delecourse");
        myRootRef.child("Courses").child(course.getCourseCode()).setValue(null);
    }
    public void deleteUser(User u){
        Log.d("DBFB", "from databasehandler deleteUser");
        Iterator<String> it = users.keySet().iterator();
        String keyToDelete= null;
        while(it.hasNext()){
            String currentKey = it.next();
            if (users.get(currentKey ).getUserName().equals(u.getUserName())) {
                Log.d("DBFB", "deleteUser found key");
                keyToDelete = currentKey;
            }

        }
        if(keyToDelete!=null){
            Log.d("DBFB", "deleteUser done");
            myRootRef.child("Users").child(keyToDelete).setValue(null);
        }

    }

    public boolean checkCourseInstructorExists(Course course){
        for (Course c: courses){
            Log.d("DBFB", "Check!!!!! i is" + i.toString() + "instructor is" + course.toString());
                if(course.getCourseInstructor().equals(i.getCourseInstructor())){
                    return true;
                }
            }
    }
    
    public boolean addCourseInstructor(Course course){
        Log.d("DBFB", "editInstructor done");
        myRootRef.child("Courses").child(course.getCourseInstructor()).child(COURSE_INSTRUCTOR).setValue(course.getCourseInstructor());
        
    }
                   
     public boolean deleteCourseInstructor(Course course){
        Log.d("DBFB", "from databasehandler deleteInstructor");
        myRootRef.child("Courses").child(course.getCourseInstructor()).setValue(null);
        
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

        for (User u: users.values()) {
            Log.d("DBFB", "Check!!!!! u is" + u.toString() + " user is " + user.toString());
            if(user.getUserName().equals(u.getUserName())){
                return true;
            }
        }
        return false;
    }
    public boolean userNameAndPasswordMatches(User user) {

        for (User u: users.values()) {
            Log.d("DBFB", "Check!!!!! u is" + u.toString() + " user is " + user.toString());
            if(user.getUserName().equals(u.getUserName()) && user.getPassword().equals(u.getPassword()) ){
                return true;
            }
        }
        return false;
    }
    public ArrayList<Course> getCourses(){
        return this.courses;
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> usersAL = new ArrayList<>();

        for (User u: users.values()) {
            usersAL.add(u);
        }
        return usersAL;
    }
    public ArrayList<String> getUserNames(){
        ArrayList<String> userNAL = new ArrayList<String>();

        for (User u: users.values()) {
            userNAL.add(u.getUserName());
        }
        return userNAL;
    }
    public User getUser(String userName) {
        for (User u : users.values()) {
            Log.d("DBFB", "Check!!!!! u is" + u.toString() + " user is " + u.toString());
            if (userName.equals(u.getUserName())) {
                return u;
            }

        }
        return new User();
    }
    public boolean isCourseCallbackDone()
    {
        return this.isCourseCallbackDone;
    }

    public boolean isUsersCallbackDone() {
        return isUsersCallbackDone;
    }
    public void registerCourseSubscriber(CourseSubscriber cS){
        this.cScriber = cS;
    }

     public ArrayList<Course> searchForCourse(String courseCode, String courseName) {
         ArrayList<Course> toRet = new ArrayList<Course>();
         for(Course c : courses){
             Log.d("DBFB", "Check!!!!! c is" + c.toString() + " coursecode is " + courseCode + " courname is " + courseName);
             if(c.getCourseName().equals(courseName) && c.getCourseCode().equals(courseCode)){
                 toRet.add(c);
                 return toRet;
             }
         }
         return toRet;
    }
}

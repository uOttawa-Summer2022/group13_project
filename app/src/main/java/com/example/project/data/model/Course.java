package com.example.project.data.model;

public class Course {

    private String courseName;
    private String courseCode;
    private String courseInstructor;
    private String courseDescription;
    private String courseDuration;
    private String courseHours;
    private String courseCapacity;
    private String courseStudent;
    private String courseDay;


    public Course() {
        this.courseName = "";
        this.courseCode = "";
        this.courseDescription = "";
        this.courseInstructor = "";
        this.courseStudent = "";
        this.courseDuration = "";
        this.courseHours = "";
        this.courseCapacity = "";
        this.courseDay = "";
    }



    public Course(String courseName, String courseCode){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseDescription = "";
        this.courseInstructor = "";
        this.courseStudent = "";
        this.courseDuration = "";
        this.courseHours = "";
        this.courseCapacity = "";
        this.courseDay = "";
}
    public Course(String courseName, String courseCode, String courseDescription, String courseInstructor, String courseDuration, String courseHours, String courseCapacity, String courseStudent, String courseDay) {
        this.courseName=courseName;
        this.courseCode=courseCode;
        this.courseInstructor=courseInstructor;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
        this.courseHours = courseHours;
        this.courseCapacity= courseCapacity;
        this.courseStudent = courseStudent;
        this.courseDay = courseDay;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName=courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode=courseCode;
    }
    
    public void setCourseInstructor(String name){
        this.courseInstructor = name;
    }

    public String getCourseInstructor(){return this.courseInstructor;}

    public String getCourseDescription(){ return courseDescription;}
    
    public String getCourseStudent(){return this.courseStudent;}
    
     public void setCourseStudent(String name){
        this.courseStudent = name;
    }
    
    public String getCourseDay(){return this.courseDay;}
    
     public void setCourseDay(String day){
        this.courseDay = day;
    }
    

    public String getCourse(){return this.courseInstructor;}

    public void setCourseDescription(String describe){ this.courseDescription = describe;}
    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(String courseHours) {
        this.courseHours = courseHours;
    }

    public String getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(String courseCapacity) {
        this.courseCapacity = courseCapacity;
    }
    
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseInstructor='" + courseInstructor + '\'' +
                ", courseStudent='" + courseStudent + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseDay='" + courseDay + '\'' +
                ", courseHours='" + courseHours + '\'' +
                ", courseDuration='" + courseDuration + '\'' +
                ", courseCapacity='" + courseCapacity + '\'' +
                '}';
    }
}


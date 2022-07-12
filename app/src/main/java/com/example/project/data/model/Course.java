package com.example.project.data.model;

public class Course {
    private int courseId;
    private String courseName;
    //Changed here to int from String
    private String courseCode;
    private String courseInstructor;
    private String courseDescription;

    public Course() {
    }

//    public Course(int courseIId, String courseName, String courseCode){
//        this.courseId = courseId;
//        this.courseName=courseName;
//        this.courseCode=courseCode;
//          this.courseInstructor="";
//    }

    public Course(String courseName, String courseCode, String courseDescription) {
        this.courseName=courseName;
        this.courseCode=courseCode;
        this.courseInstructor="";
        this.courseDescription = courseDescription;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
    
    //public String getCourseInstructor() {
       // return courseInstructor;
    //}
    
    public void setCourseInstructor(String name){
        this.courseInstructor = name;
    }

    public String getCourseDescription(){ return courseDescription;}

    public void setCourseDescription(String describe){ this.courseDescription = describe;}
    
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseInstructor='" + courseInstructor + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }
}


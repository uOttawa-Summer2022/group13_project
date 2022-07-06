package com.example.project.data.model;

public class Course {
    private int courseId;
    private String courseName;
    //Changed here to int from String
    private String courseCode;

    public Course() {
    }

//    public Course(int courseIId, String courseName, String courseCode){
//        this.courseId = courseId;
//        this.courseName=courseName;
//        this.courseCode=courseCode;
//    }

    public Course(String courseName, String courseCode) {
        this.courseName=courseName;
        this.courseCode=courseCode;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                '}';
    }
}


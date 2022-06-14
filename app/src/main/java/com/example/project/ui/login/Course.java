package com.example.project.ui.login;

public class Course {
    private int courseId;
    private String courseName;
    private int courseCode;

    public Course() {
    }

    public Course(int courseIId, String courseName, int courseCode){
        this.courseId = courseId;
        this.courseName=courseName;
        this.courseCode=courseCode;
    }

    public Course(String courseName, int courseCode) {
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

    public double getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode=courseCode;
    }
}


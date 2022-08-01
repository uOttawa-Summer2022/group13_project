package com.example.project;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.project.data.FireBaseDataBaseHandler;
import com.example.project.data.model.Course;
import com.example.project.data.model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void studentCheckExistTest(){
        FireBaseDataBaseHandler fBH = new FireBaseDataBaseHandler();
        Course c = new Course();
        assertEquals(false, fBH.checkCourseStudentExists(c));
    }

    @Test
    public void userCheckInDatabase(){
        FireBaseDataBaseHandler fBH = new FireBaseDataBaseHandler();
        User u = new User();
        assertEquals(false, fBH.userExistsInDatabase(u));
    }

    @Test
    public void courseConfliectCheck(){
        FireBaseDataBaseHandler fBH = new FireBaseDataBaseHandler();
        String studentName = "testName";
        assertEquals(false,fBH.courseConflict(studentName));
    }

    @Test
    public void courseInstructorExist(){
        FireBaseDataBaseHandler fBH = new FireBaseDataBaseHandler();
        Course c = new Course();
        assertEquals(false, fBH.checkCourseInstructorExists(c));
    }

    @Test
    public void UserNamePasswordMatchCheck(){
        FireBaseDataBaseHandler fBH = new FireBaseDataBaseHandler();
        User u = new User();
        assertEquals(false, fBH.userNameAndPasswordMatches(u));
    }
}
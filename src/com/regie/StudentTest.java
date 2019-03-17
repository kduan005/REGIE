package com.regie;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class StudentTest {
    private Connection conn = DBConnection.getConn();
    private Statement stmt = null;
    private ResultSet rs = null;
    private Student fakeStudent = new Student("fakeStudent", 5, "fakestudent@uchicago.edu");
    private Course fakeCourse = new Course("Fake Course",4, "fakeFaculty");

    @Test
    public void registerCourse() {
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(fakeStudent.registerCourse(fakeCourse));
            rs = stmt.executeQuery(fakeStudent.showMyCourse());
            assertEquals(rs.getString("name"), "Fake Course");
        }
        catch (Exception ex){
        }
    }

    @Test
    public void dropCourse() {
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(fakeStudent.dropCourse(fakeCourse));
            rs = stmt.executeQuery(fakeStudent.showMyCourse());
            assertFalse(rs.next());
            stmt.executeUpdate(fakeStudent.registerCourse(fakeCourse));
        }
        catch (Exception ex){
        }
    }

    @Test
    public void showMyCourse() {
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(fakeStudent.showMyCourse());
            assertEquals(rs.getString("name"), "Fake Course");
        }
        catch (Exception ex){
        }
    }

    @Test
    public void viewGrades() {
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(fakeStudent.viewGrades());
            assertNull(rs.getString("grade"));
        }
        catch (Exception ex){
        }
    }
}
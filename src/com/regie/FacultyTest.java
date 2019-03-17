package com.regie;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class FacultyTest {
    private Connection conn = DBConnection.getConn();
    private Statement stmt = null;
    private ResultSet rs = null;
    private Faculty fakeFaculty = new Faculty("fakeFaculty", 2, "fakestudent@uchicago.edu");

    @Test
    public void assignGrade() {
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(fakeFaculty.assignGrade("Fake Course", "fakestudent@uchicago.edu", "A-"));
            rs = stmt.executeQuery(fakeFaculty.showGrade("Fake Course", "fakestudent@uchicago.edu"));
            assertEquals(rs.getString("grade"), "A-");
        }
        catch (Exception ex){
        }
    }

    @Test
    public void showGrade() {
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(fakeFaculty.showGrade("Fake Course", "fakestudent@uchicago.edu"));
            assertEquals(rs.getString("grade"), "A-");
        }
        catch (Exception ex){
        }

    }
}
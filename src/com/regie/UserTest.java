package com.regie;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.Assert.*;

public class UserTest {
    private Connection conn = DBConnection.getConn();
    private Statement stmt = null;
    private ResultSet rs = null;
    private Administrater fakeAdmin = new Administrater("fakeAdmin", 1);

    @Test
    public void searchCourse() {
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(fakeAdmin.searchCourse("Fake Course"));
            assertEquals(rs.getString("name"), "Fake Course");
        }
        catch (Exception ex){
        }
    }
}
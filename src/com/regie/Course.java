package com.regie;

import java.sql.ResultSet;

public class Course {
    public String name;
    public int id;
    public String faculty;

    public Course(String name, int id, String faculty){
        this.name = name;
        this.id = id;
        this.faculty = faculty;
    }

    public static String showDetails(ResultSet rs){
        //return course detail string that will be utilized by multiple output textfield in different frames
        try {
            String output = ("Course Name: " + rs.getString("name") + "; Faculty: " + rs.getString("faculty") + "; Location: " + rs.getString("location") + "; Time: " + rs.getString("time"));
            return output;
        }catch (Exception ex){
            return null;
        }
    }
}

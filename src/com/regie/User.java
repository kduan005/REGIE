package com.regie;

public class User {

    public String name;
    public int id;
    public String email;

    public User(){}
    public User(String name, int id, String email){
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String searchCourse(String courseName) {
        return "SELECT * FROM courses WHERE name = '" + courseName + "'";
    }
}

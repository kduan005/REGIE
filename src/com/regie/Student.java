package com.regie;

import java.util.ArrayList;

public class Student extends User{

    public ArrayList<Course> courses;

    public Student(String name, int id, String email) {
        super(name, id, email);
    }

    public String registerCourse(Course course) {
        // input is course object, generate sql query inserting data entry into registrations table, return sqlSyntax
        String query;
        query = String.format("INSERT INTO registrations ( student_id, course_id ) VALUES ( %d, % d)", this.id, course.id);

        return query;
    }

    public String dropCourse(Course course) {
        // input is course object, generate sql query deleting data entry from registrations table, return sqlSyntax
        String query;
        query = String.format("DELETE FROM registrations WHERE student_id = %d AND course_id = %d", this.id, course.id);

        return query;
    }

    public String showMyCourse() {
        String sqlSyntax = String.format("SELECT name FROM REGIE.courses JOIN (SELECT course_id FROM REGIE.registrations WHERE student_id = %d) AS a ON id = a.course_id", id);
        return sqlSyntax;
    }

    public String viewGrades() {
        String sqlSyntax = String.format("SELECT name, grade FROM (SELECT student_id, name, grade FROM (REGIE.courses INNER JOIN REGIE.registrations ON courses.id = registrations.course_id)) AS a WHERE a.student_id = %d", id);
        return sqlSyntax;
    }
}

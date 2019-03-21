package com.regie;

import java.util.ArrayList;

public class Student extends User{

    public ArrayList<Course> courses;

    public Student(String name, int id, String email) {
        super(name, id, email);
    }

    public String registerCourse(Course course) {
        // allow students to be able to register in at most 3 courses
        String query;
        query = String.format("INSERT INTO registrations ( student_id, course_id ) VALUES ( %d, % d)", this.id, course.id);

        return query;
    }

    public String dropCourse(Course course) {
        // allow students to be able to drop the courses that he/she's registered in
        String query;
        query = String.format("DELETE FROM registrations WHERE student_id = %d AND course_id = %d", this.id, course.id);

        return query;
    }

    public String showMyCourse() {
        // show all the courses a student has registered in
        String sqlSyntax = String.format("SELECT name, id, faculty FROM REGIE.courses JOIN (SELECT course_id FROM REGIE.registrations WHERE student_id = %d) AS a ON id = a.course_id", id);
        return sqlSyntax;
    }

    public String viewGrades() {
        // view the grades of all courses a student has registered in
        String sqlSyntax = String.format("SELECT name, grade FROM (SELECT student_id, name, grade FROM (REGIE.courses INNER JOIN REGIE.registrations ON courses.id = registrations.course_id)) AS a WHERE a.student_id = %d", id);
        return sqlSyntax;
    }
}

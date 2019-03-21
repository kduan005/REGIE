package com.regie;

public class Faculty extends User {

    public Faculty(String name, int id, String email){
        super(name, id, email);
    }

    public String showGrade(String courseName, String studentEmail){
        //create query for showing grade for students registered in a course by using student email and course name

        String query = String.format("SELECT grade, c.faculty FROM REGIE.registrations r,\n" +
                "REGIE.users u, REGIE.courses c\n" +
                "WHERE u.type = \"S\" and u.typeid = r.student_id\n" +
                "and c.id = r.course_id and u.email = \"%s\"\n" +
                "and c.name = \"%s\"", studentEmail, courseName);
        return query;

    }

    public String assignGrade(String courseName, String studentEmail, String newGrade) {
        //if student hasn't been assign a grade yet, assign the grade, else change the current grade to a new grade

        String query = String.format("UPDATE REGIE.registrations AS r\n" +
                "INNER JOIN REGIE.users AS u\n" +
                "ON u.typeid = r.student_id\n" +
                "INNER JOIN REGIE.courses AS c\n" +
                "ON c.id = r.course_id\n" +
                "SET grade = \"%s\"\n" +
                "WHERE u.type = \"S\"\n" +
                "AND u.email = \"%s\"\n" +
                "AND c.name = \"%s\"", newGrade, studentEmail, courseName);
        return query;
    }

}

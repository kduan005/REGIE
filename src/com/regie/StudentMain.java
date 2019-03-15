package com.regie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class StudentMain {
    private JTextField input;
    private JTextField output;
    private JButton searchCourse;
    private JPanel studentMain;
    private JButton registerCourse;
    private JButton dropCourse;
    private JButton back;
    public JFrame studentMainFrame = new JFrame("studentMain");
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Student student;

    public StudentMain(){
        student = (Student) Login.user;
        studentMainFrame.setContentPane(studentMain);
        studentMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentMainFrame.pack();
        studentMainFrame.setVisible(false);
        this.conn = DBConnection.getConn();

        searchCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText("Course Name: "+ rs.getString("name") + " Faculty: " + rs.getString("faculty") + " Location: "+ rs.getString("location") + " Time: " + rs.getString("time"));
                    }else{
                        JOptionPane.showMessageDialog(null, "No course record in system. Please try again.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        registerCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText("Course Name: "+ rs.getString("name") + " Faculty: " + rs.getString("faculty") + " Location: "+ rs.getString("location") + " Time: " + rs.getString("time"));
                        Course course = new Course(rs.getString("name"), rs.getInt("id"), rs.getString("faculty"));

                        // Search for courses student has already registered in
                        ResultSet myCourseRs = stmt.executeQuery(student.showMyCourse());
                        int nCourse = 0;
                        HashSet<String> courseNames = new HashSet<>();
                        while (myCourseRs.next()) {
                            nCourse = nCourse + 1;
                            courseNames.add(myCourseRs.getString("name"));
                        }

                        // If student has already registered 3 courses
                        if (nCourse == 3){
                            JOptionPane.showMessageDialog(null, "You've exceed 3 courses limit. Please drop another course before you register.");
                        }
                        // Student has already registered in the course
                        else if (courseNames.contains(course.name) ){
                            JOptionPane.showMessageDialog(null, "You've already registered in the course.");
                        }
                        // Can register
                        else {
                            stmt.executeUpdate(student.registerCourse(course));
                            JOptionPane.showMessageDialog(null, String.format("You've successfully enrolled in %s", course.name));
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "No course record in system. Please try again.");
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        dropCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText("Course Name: "+ rs.getString("name") + " Faculty: " + rs.getString("faculty") + " Location: "+ rs.getString("location") + " Time: " + rs.getString("time"));
                        Course course = new Course(rs.getString("name"), rs.getInt("id"), rs.getString("faculty"));
                        stmt.executeUpdate(student.dropCourse(course));
                        JOptionPane.showMessageDialog(null, String.format("You've successfully dropped from %s", course.name));
                    }
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                studentMainFrame.setVisible(false);
                StudentHome studentHome = new StudentHome();
                studentHome.studentHomeFrame.setVisible(true);
            }
        });
    }
}
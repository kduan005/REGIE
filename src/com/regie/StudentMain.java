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
                //search for course info
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText(Course.showDetails(rs));
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
                //register a course
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText(Course.showDetails(rs));
                        Course course = new Course(rs.getString("name"), rs.getInt("id"), rs.getString("faculty"));

                        // first search for courses student has already registered
                        ResultSet myCourseRs = stmt.executeQuery(student.showMyCourse());
                        int nCourse = 0;
                        HashSet<String> courseNames = new HashSet<>();
                        while (myCourseRs.next()) {
                            nCourse = nCourse + 1;
                            courseNames.add(myCourseRs.getString("name"));
                        }

                        // if student has already registered 3 courses, show exceeding course number limitation message
                        if (nCourse == 3){
                            JOptionPane.showMessageDialog(null, "You've exceed 3 courses limit. Please drop another course before you register.");
                        }
                        // if student has already registered in the course, show message
                        else if (courseNames.contains(course.name) ){
                            JOptionPane.showMessageDialog(null, "You've already registered in the course.");
                        }
                        // case when student can register course
                        else {
                            stmt.executeUpdate(student.registerCourse(course));
                            JOptionPane.showMessageDialog(null, String.format("You've successfully enrolled in %s", course.name));
                        }
                    }else{
                        //no course entry matches input
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
                //allow student to drop a course
                try{
                    stmt = conn.createStatement();
                    String courseName = input.getText();
                    rs = stmt.executeQuery(student.searchCourse(courseName));
                    if (rs.next()){
                        output.setText(Course.showDetails(rs));
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
                //go back to studentHome
                try{if (rs != null)
                        rs.close();
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

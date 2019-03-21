package com.regie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class FacultyMain {
    private JTextField courseInput;
    private JTextField courseOutput;
    private JTextField grade;
    private JButton searchCourse;
    private JButton showGrade;
    private JButton assignGrade;
    private JPanel facultyMain;
    private JTextField studentEmail;
    private JButton back;
    public JFrame facultyMainFrame = new JFrame("facultyMain");
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Faculty faculty;

    public FacultyMain() {
        faculty = (Faculty) Login.user;
        facultyMainFrame.setContentPane(facultyMain);
        facultyMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        facultyMainFrame.pack();
        facultyMainFrame.setVisible(false);
        this.conn = DBConnection.getConn();

        searchCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //search course functionality for faculty users
                try{
                    stmt = conn.createStatement();
                    String courseName = courseInput.getText();
                    rs = stmt.executeQuery(faculty.searchCourse(courseName));
                    if (rs.next()){
                        courseOutput.setText(Course.showDetails(rs));
                    }else{
                        courseOutput.setText("Cannot find course record.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        showGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show grade of student
                // a faculty is not eligible to view grades for students of courses he/she is not teaching
                try{
                    stmt = conn.createStatement();
                    String courseName = courseInput.getText();
                    courseOutput.setText(null);
                    String sEmail = studentEmail.getText();
                    rs = stmt.executeQuery(faculty.showGrade(courseName, sEmail));
                    if (rs.next()){
                        if (rs.getString("faculty").equals(faculty.name)){
                            if (rs.getString("grade") == null){
                                grade.setText(null);
                                JOptionPane.showMessageDialog(null, "The student hasn't been graded yet.");
                            }
                            grade.setText(rs.getString("grade"));
                        }else{
                            grade.setText(null);
                            // faculties are only allowed to view grades for students of courses he/she is teaching
                            JOptionPane.showMessageDialog(null, "Sorry, you're not authorized to view the grade.");
                        }
                    }else{
                        // no registration entry matches inputs
                        JOptionPane.showMessageDialog(null,"Cannot find grade record. Please try again.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        assignGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // allow faculties to assign/change grades for students
                try{
                    stmt = conn.createStatement();
                    String courseName = courseInput.getText();
                    String sEmail = studentEmail.getText();
                    rs = stmt.executeQuery(faculty.showGrade(courseName, sEmail));
                    if (rs.next()){
                        if (rs.getString("faculty").equals(faculty.name)){
                            String newGrade = grade.getText();
                            stmt.executeUpdate(faculty.assignGrade(courseName, sEmail, newGrade));
                            JOptionPane.showMessageDialog(null, "Grade updated successfully!");
                        }else{
                            grade.setText(null);
                            //faculties are only allowed to assign/change grades for students of course he/she is teaching
                            JOptionPane.showMessageDialog(null, "Sorry, you're not authorized to assign/update the grade");
                        }
                    }else{
                        grade.setText(null);
                        //no registration entry matches inputs
                        JOptionPane.showMessageDialog(null, "Cannot find grade record. Please try again.");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // return to facultyHome
                try{if (rs != null)
                        rs.close();
                    if(stmt!=null)
                        stmt.close();
                    }catch(SQLException se2){
                }
                facultyMainFrame.setVisible(false);
                FacultyHome facultyHome = new FacultyHome();
                facultyHome.facultyHomeFrame.setVisible(true);
            }
        });
    }
}

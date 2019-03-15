package com.regie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class StudentHome {
    private JPanel home;
    private JButton searchCourse;
    private JButton showMyCourse;
    private JButton viewGrades;
    private JButton registerOrDrop;
    private JButton logout;
    JFrame studentHomeFrame = new JFrame("StudentHome");
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Student student;

    public StudentHome(){
        student = (Student) Login.user;
        conn = DBConnection.getConn();
        studentHomeFrame.setContentPane(home);
        studentHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        studentHomeFrame.pack();
        studentHomeFrame.setVisible(false);
        searchCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomeFrame.setVisible(false);
                StudentMain studentMain = new StudentMain();
                studentMain.studentMainFrame.setVisible(true);
            }
        });
        registerOrDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomeFrame.setVisible(false);
                StudentMain studentMain = new StudentMain();
                studentMain.studentMainFrame.setVisible(true);
            }
        });
        showMyCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(student.showMyCourse());
                    String courseRS = "";
                    while (rs.next()) courseRS += rs.getString("name") + "\n";
                    JOptionPane.showMessageDialog(null, "You're registered in\n\n"+ courseRS);

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        viewGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(student.viewGrades());
                    String gradeRS = "";
                    while (rs.next()) gradeRS += rs.getString("name") + " "+ rs.getString("grade") + "\n";
                    JOptionPane.showMessageDialog(null, "Your grades are:\n\n" + gradeRS);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
            }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
                System.out.println("Goodbye!");
                System.exit(0);
            }
        });
    }
}

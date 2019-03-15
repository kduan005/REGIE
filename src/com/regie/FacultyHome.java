package com.regie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class FacultyHome {
    private JButton searchCourse;
    private JButton assignOrDrop;
    private JPanel home;
    private JButton logout;
    JFrame facultyHomeFrame = new JFrame("FacultyHome");
    private Connection conn;

    public FacultyHome() {
        conn = DBConnection.getConn();
        facultyHomeFrame.setContentPane(home);
        facultyHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        facultyHomeFrame.pack();
        facultyHomeFrame.setVisible(false);
        searchCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facultyHomeFrame.setVisible(false);
                FacultyMain facultyMain = new FacultyMain();
                facultyMain.facultyMainFrame.setVisible(true);
            }
        });
        assignOrDrop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facultyHomeFrame.setVisible(false);
                FacultyMain facultyMain = new FacultyMain();
                facultyMain.facultyMainFrame.setVisible(true);
            }
        });
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

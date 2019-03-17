package com.regie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class Login {
    private JButton loginButton;
    private JPanel login;
    private JButton cancelButton;
    private JTextField email;
    private JTextField password;
    private Connection conn;
    private Statement stmt = null;
    private ResultSet rs = null;
    private JFrame loginFrame = new JFrame("Login");
    public static User user;

    private Login() {
        conn = DBConnection.getConn();

        loginFrame.setContentPane(login);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    stmt = conn.createStatement();
                    String userEmail = email.getText();
                    String userPass = password.getText();
                    String sql = "SELECT * FROM users WHERE email = '" + userEmail + "' && password = '" + userPass + "'";
                    rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        String name = rs.getString("name");
                        String type = rs.getString("type");
                        String email = rs.getString("email");
                        int id = Integer.parseInt(rs.getString("typeid"));

                        switch (type){
                            case "F":
                                user = new Faculty(name, id, email);
                                break;
                            case "S":
                                user = new Student(name, id, email);
                                break;
                        }

                        if (user instanceof Student){
                            loginFrame.setVisible(false);
                            StudentHome studentHome = new StudentHome();
                            studentHome.studentHomeFrame.setVisible(true);
                        } else if(user instanceof Faculty){
                            loginFrame.setVisible(false);
                            FacultyHome facultyHome = new FacultyHome();
                            facultyHome.facultyHomeFrame.setVisible(true);
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Passord or email is invalid");
                    }

                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); }
        });
    }

    public static void main(String[] args) {
        new Login();
    }

}

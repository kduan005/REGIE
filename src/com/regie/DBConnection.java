package com.regie;

import java.sql.*;

public class DBConnection{
    // https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/REGIE?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "pass1234";
    private static Connection conn = null;

    private Connection connection(){

        System.out.println("Welcome to Uchicago course registrations system!");

        try{
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConn(){
//        create a conn that is singleton
        if (conn == null)
            conn = new DBConnection().connection();
        return conn;
    }
}


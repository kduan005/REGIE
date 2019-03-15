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

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = searchCourseSql;
//            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                String name = rs.getString("name");
//                String faculty = rs.getString("faculty");
//                String location = rs.getString("location");
//                Date date = rs.getDate("time");
//
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", com.regie.Course Name: " + name);
//                System.out.print(", com.regie.Faculty: " + faculty);
//                System.out.println(", location: " + location);
//                System.out.println(", date:" + date);
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            return null;
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            return null;
        }
//        finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        System.out.println("Goodbye!");
    }//end connection

    public static Connection getConn(){
//        create a conn that is singleton
        if (conn == null)
            conn = new DBConnection().connection();
        return conn;
    }
}//end com.regie.DBConnection


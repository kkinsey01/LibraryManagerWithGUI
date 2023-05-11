package Database;

import java.sql.*;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

// class is meant to create a universal functionality for all the classes in order to made code simplier, and do simple SQL queries

public class DBConnection {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/library";
    private static final String user = "root";
    private static final String password = "MyFirstSQL0";
    Connection connection;

    public DBConnection() {
        connect();
    }
    public void connect() { // connections to the database
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch(SQLException|ClassNotFoundException e) {
            System.out.println("Error connecting to database");
            System.out.println(e.getMessage());
        }
    }
    public ResultSet select(String query) { // searches a specific database based on input
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch(SQLException e) {
            System.out.println("Error while executing select query");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public int update(String query){ // updates a specific database based on input
        try{
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR while executing update query");
            System.out.println(e.toString());
            return -1;
        }
    }

    public int delete(String query){ // deletes from a specific database based on input
        try{
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        }catch(SQLException e){
            System.out.println("ERROR while deleting line!");
            System.out.println(e.toString());
            return -1;
        }
    }
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

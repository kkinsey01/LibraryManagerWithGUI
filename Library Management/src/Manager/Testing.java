package Manager;

import DBEnterNames.AddEmployees;
import DBEnterNames.AddBooks;
import Database.DBConnection;
import Interface.*;
import DBEnterNames.AddCustomers;
import java.sql.*;
import java.util.Scanner;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class Testing {

    static DBConnection connection = new DBConnection(); // Create a connection

    public static void main(String[] args) {
        // Create a connection

        new AddCustomers(); // Add customers from AccountNames.txt into SQL table
        new AddEmployees(); // Add employees from EmployeeNames.txt into SQL table
        new AddBooks(); // Add books from BookNames.txt into SQL table

        new LoginScreen(); // calls the Login screen GUI to begin user interaction

        connection.close();

    }


}

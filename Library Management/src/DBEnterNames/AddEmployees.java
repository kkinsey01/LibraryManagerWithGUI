package DBEnterNames;

import java.io.File;
import java.util.Scanner;
import Manager.Employee;
import Database.DBConnection;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class AddEmployees {

    public AddEmployees() {
        deleteDatabase();
        call();
    }
    private void call() {
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File("EmployeeNames.txt")); // reads data from EmployeeNames.txt
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        while (fileInput.hasNextLine()) {
            String fName = fileInput.next();
            String lName = fileInput.next();
            String fullName = fName + " " + lName;
            int salary = fileInput.nextInt();
            if (fileInput.hasNextLine()) {
                fileInput.nextLine();
                Employee temp = new Employee(fullName, salary); // creates Employee Object based on data. Check Manager.Employee for full details ob Object\
                addToDatabase(temp);
            }
            else {
                break;
            }
        }
    }
    private void addToDatabase(Employee temp) { // inserts employee object into database
        DBConnection connection = new DBConnection();
        String sql = "INSERT INTO employee VALUES (" + temp.getId() + ", '" + temp.getName() + "', '" + temp.getPhoneNumber() + "', " + temp.getSalary() + ")";
        connection.update(sql);
        connection.close();
    }
    private void deleteDatabase() { // deletes entire table. Mostly for testing purposes
        DBConnection connection = new DBConnection();
        String sql = "DELETE FROM employee";
        connection.delete(sql);
        connection.close();
    }
}

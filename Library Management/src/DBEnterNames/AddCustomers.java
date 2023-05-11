package DBEnterNames;

import java.io.File;
import java.util.Scanner;
import Manager.Account;
import Database.DBConnection;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class AddCustomers {

    public AddCustomers() {
        deleteDatabase();
        call();
    }
    private void call() {
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File("AccountNames.txt")); // reads data from AccountNames.txt
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        while (fileInput.hasNextLine()) {
            String name = fileInput.nextLine();
            Account temp = new Account(name); // creates an Account object based on name. Generates ID, phone number, etc. Look at Manager.Account for full details
            addToDatabase(temp);
        }
    }
    private void addToDatabase(Account temp) { // inserts account object into database
        DBConnection connection = new DBConnection();
        String sql = "INSERT INTO customer VALUES (" + temp.getId() + ", '" + temp.getName() + "', '" + temp.getPhoneNumber() + "', " + temp.getNumRentals() + ", " + null + ")";
        connection.update(sql);
        connection.close();
    }
    private void deleteDatabase() { // delete entire table. Mostly for testing purposes
        DBConnection connection = new DBConnection();
        String sql = "DELETE FROM customer";
        connection.delete(sql);
        connection.close();
    }
}

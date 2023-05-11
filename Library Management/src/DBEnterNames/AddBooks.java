package DBEnterNames;

import java.io.File;
import java.util.Scanner;
import Manager.Book;
import Database.DBConnection;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class AddBooks {

    public AddBooks() {
        deleteDatabase();
        call();
    }
    private void call() {
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File("BookNames.txt")); // read file from BookNames.txt
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        while (fileInput.hasNextLine()) {
            String author = fileInput.nextLine();
            String title = fileInput.nextLine();
            String genre = fileInput.nextLine();
            Book temp = new Book(author, title, genre); // creates Book Object based on data in file. Look at Manager.Book for full details on object
            addToDatabase(temp);
        }
    }
    private void addToDatabase(Book temp) { // inserts book object into database
        DBConnection connection = new DBConnection();
        String sql = "INSERT INTO books VALUES ( " + temp.getId() + ", '" + temp.getAuthor() + "', '" + temp.getTitle() + "', '" + temp.getGenre() + "', " + temp.getAvailability() + ", " + null + ")";
        connection.update(sql);
        connection.close();
    }
    private void deleteDatabase() { // delete entire table (mostly for testing purposes)
        DBConnection connection = new DBConnection();
        String sql = "DELETE FROM books";
        connection.delete(sql);
        connection.close();
    }
}

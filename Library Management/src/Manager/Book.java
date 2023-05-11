package Manager;

import java.util.Random;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class Book {
    private String author;
    private String title;
    private String genre;
    private int id;
    private boolean available;

    public Book(String author, String title, String genre) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.id = generateID();
        available = true;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAvailability(boolean set) {
        available = set;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
    public boolean getAvailability() {
        return available;
    }
    private int generateID() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);
        return number;
    }
}

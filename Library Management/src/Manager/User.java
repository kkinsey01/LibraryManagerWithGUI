package Manager;

import java.util.Random;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class User {
    private String id;
    private String name;
    private String phoneNumber;

    public User() {
        this.id = "empty";
        this.name = "";
        this.phoneNumber = "empty";
    }
    public User(String name) {
        this.id = generateID();
        this.name = name;
        this.phoneNumber = generatePhoneNumber();
    }

    public int getId() {
        return Integer.parseInt(id);
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId() {
        this.id = generateID();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber() {
        this.phoneNumber = generatePhoneNumber();
    }

    private String generateID() {
        Random rnd = new Random();
        int number = rnd.nextInt(99999999);
        return String.format("%06d", number);
    }

    private String generatePhoneNumber() {
        String result = "814-";
        int random;
        for (int i = 0; i < 8; i++) {
            random = (int) (Math.random() * 10);
            if (i != 3) {
                result += Integer.toString(random);
            }
            else {
                result += "-";
            }
        }
        return result;
    }
}

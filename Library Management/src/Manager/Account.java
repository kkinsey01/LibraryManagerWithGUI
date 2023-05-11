package Manager;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class Account extends User {

    private static final boolean adminStatus = false;
    private int numRentals;

    public Account(String name) {
        super(name);
        this.numRentals = 0;
    }
    public boolean isAdminStatus() {
        return adminStatus;
    }
    public int getNumRentals() {
        return numRentals;
    }
    public boolean addRental() {
        this.numRentals++;
        return true;
    }
    public boolean removeRental() {
        this.numRentals--;
        return true;
    }
}

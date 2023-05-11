package Manager;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class Employee extends User {
    private final boolean adminStatus = true;
    private int salary;

    public Employee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isAdminStatus() {
        return adminStatus;
    }
}

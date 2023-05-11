package Interface;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Objects;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class FindAccount {

    static DBConnection connection = new DBConnection();
    private JLabel criteria;
    private JLabel prompt = new JLabel("Enter ID here: ");
    private JPanel panel;
    private JFrame frame;
    private JButton button1;
    private String choice = "ID";
    private String table = "customer";
    private String searchItem = "";
    private int yCord = 0;

    public FindAccount() {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("Find An Account");
        frame.add(panel);
        frame.setSize(new Dimension(550, 350));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        criteria = new JLabel("Select account search criteria, then click OK");
        criteria.setFont(new Font("Serif", Font.BOLD, 18));
        yCord += 10;
        criteria.setBounds(100, yCord, criteria.getPreferredSize().width, criteria.getPreferredSize().height);
        panel.add(criteria);

        String[] choices = {"ID", "Name", "Phone_Number"};
        String[] choices2 = {"customer", "employee"};
        final JComboBox<String> cb = new JComboBox<String>(choices);
        final JComboBox<String> cb2 = new JComboBox<String>(choices2);
        cb.setFont(new Font("Ariel", Font.PLAIN, 15));
        cb2.setFont(new Font("Ariel", Font.PLAIN, 15));
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setMaximumSize(cb.getPreferredSize());
        yCord += 5 + cb.getPreferredSize().height;
        cb.setBounds(115, yCord, cb.getPreferredSize().width, cb.getPreferredSize().height);
        cb2.setBounds(130 + cb.getWidth(), yCord, cb2.getPreferredSize().width, cb2.getPreferredSize().height);
        panel.add(cb);
        panel.add(cb2);

        JButton ok = new JButton("OK");
        ok.setBounds(145 + cb.getWidth() + cb2.getWidth(), yCord, 30 + ok.getPreferredSize().width, 3 + ok.getPreferredSize().height);
        ok.addActionListener( e -> {
            choice = Objects.requireNonNull(cb.getSelectedItem().toString());
            table = Objects.requireNonNull(cb2.getSelectedItem().toString());
            prompt.setText("Enter " + choice + " here: ");
            frame.invalidate();
            frame.repaint();
        });
        panel.add(ok);
        prompt.setFont(new Font("Ariel", Font.PLAIN, 16));
        yCord += 45;
        prompt.setBounds(10, yCord, 30 + prompt.getPreferredSize().width, prompt.getPreferredSize().height);
        panel.add(prompt);

        JTextArea text = new JTextArea();
        Font newFont = new Font(text.getFont().getName(), text.getFont().getStyle(), 16);
        text.setFont(newFont);
        text.setEditable(true);
        text.setPreferredSize(new Dimension(300, 100));
        text.setBounds(5 + prompt.getWidth(), yCord, 300, 45);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchItem = text.getText();
                    findData();
                }
            }
        });
        panel.add(text);

        JButton enter = new JButton("Enter");
        enter.addActionListener(e -> {
            searchItem = text.getText();
            findData();
        });
        yCord += 30 + prompt.getHeight();
        enter.setBounds( -60 + prompt.getWidth() + text.getWidth(), yCord, enter.getPreferredSize().width, enter.getPreferredSize().height);
        panel.add(enter);

        frame.setVisible(true);
    }

    public void findData() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setTitle("Data");
        frame.add(panel);
        frame.setSize(new Dimension(450, 200));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        int dataIndex = 0;
        String[] data = searchForPerson(table, choice, searchItem);
        if (data[0] == null) {
            JLabel returnData = new JLabel("Error: No person found!");
            returnData.setFont(new Font("Serif", Font.BOLD, 24));
            returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnData.setForeground(Color.RED);
            panel.add(returnData);
        }
        else {
            JLabel ID = new JLabel("ID --- " + data[dataIndex++]);
            ID.setFont(new Font("Serif", Font.BOLD, 18));
            ID.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel name = new JLabel("Name --- " + data[dataIndex++]);
            name.setFont(new Font("Serif", Font.BOLD, 18));
            name.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel phoneNumber = new JLabel("Phone-Number --- " + data[dataIndex++]);
            phoneNumber.setFont(new Font("Serif", Font.BOLD, 18));
            phoneNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(ID);
            panel.add(name);
            panel.add(phoneNumber);

            if (table.equals("customer")) {
                JLabel numRentals = new JLabel("Number of Rentals --- " + data[dataIndex++]);
                numRentals.setFont(new Font("Serif", Font.BOLD, 18));
                numRentals.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel rentalList = new JLabel("Current rentals --- " + data[dataIndex++]);
                rentalList.setFont(new Font("Serif", Font.BOLD, 18));
                rentalList.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(numRentals);
                panel.add(rentalList);
            }
            else {
                JLabel salary = new JLabel("Salary --- " + data[dataIndex]);
                salary.setFont(new Font("Serif", Font.BOLD, 18));
                salary.setAlignmentX(Component.CENTER_ALIGNMENT);

                panel.add(salary);
            }
        }
    }
    public static String[] searchForPerson(String table, String criteria, String item) {
        String sql = "SELECT * FROM " + table + " WHERE LOWER";
        ResultSet rs = null;
        String[] result;
        if (table.equals("customer")) {
            result = new String[5];
        }
        else {
            result = new String[4];
        }
        criteria = criteria.toUpperCase();
        switch (criteria) {
            case "NAME":
                sql += "(Name) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            case "PHONE_NUMBER":
                sql += "(Phone_Number) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            case "ID":
                if (table.equals("customer")) {
                    sql += "(CustomerID) = LOWER('" + item + "')";
                }
                else {
                    sql += "(EmployeeID) = LOWER('" + item + "')";
                }
                rs = connection.select(sql);
                break;
            default:
                System.out.println("Error with select query");
                break;

        }
        if (rs == null) {
            return null;
        }
        else {
            try {
                rs.next();
                int rsIndex = 1;
                int index = 0;
                result[index++] = rs.getString(rsIndex++);
                result[index++] = rs.getString(rsIndex++);
                result[index++] = rs.getString(rsIndex++);
                result[index++] = rs.getString(rsIndex++);
                if (table.equals("customer")) {
                    result[index] = rs.getString(rsIndex);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return result;
        }
    }
}

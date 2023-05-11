package Interface;

import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class BookReturn {
    private static DBConnection connection = new DBConnection();
    private JPanel panel;
    private JFrame frame;
    private String criteria = "ID";
    private String bookCriteria = "ID";
    private String searchItem = "";
    private String bookSearch = "";
    private int yCord = 0;

    public BookReturn() {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("Book Return");
        frame.add(panel);
        frame.setSize(new Dimension(650, 350));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel prompt1 = new JLabel("Select account return criteria, then click OK");
        prompt1.setFont(new Font("Serif", Font.BOLD, 16));
        yCord += 10;
        prompt1.setBounds(25, yCord, prompt1.getPreferredSize().width, prompt1.getPreferredSize().height);

        String[] prompt1Choices = {"ID", "Name", "Phone_Number" };
        final JComboBox<String> cb = new JComboBox<>(prompt1Choices);
        cb.setFont(new Font("Ariel", Font.PLAIN, 14));
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setBounds(40 + prompt1.getWidth(), yCord, cb.getPreferredSize().width, cb.getPreferredSize().height);
        panel.add(cb);

        JButton ok = new JButton("OK");
        ok.setBounds(60 + cb.getWidth() + prompt1.getWidth(), yCord, 30 + ok.getPreferredSize().width, 3 + ok.getPreferredSize().height);
        ok.addActionListener( e -> {
            criteria = Objects.requireNonNull(cb.getSelectedItem().toString());

        });

        JLabel prompt2 = new JLabel("Please enter data: ");
        prompt2.setFont(new Font("Serif", Font.BOLD, 16));
        yCord += 45;
        prompt2.setBounds(25, yCord, prompt2.getPreferredSize().width, prompt2.getPreferredSize().height);

        JTextArea text = new JTextArea();
        Font newFont = new Font(text.getFont().getName(), text.getFont().getStyle(), 16);
        text.setFont(newFont);
        text.setEditable(true);
        text.setPreferredSize(new Dimension(400, 100));
        text.setBounds(30 + prompt2.getWidth(), yCord, 400, 45);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);



        JLabel prompt3 = new JLabel("Select book return criteria, then click OK");
        prompt3.setFont(new Font("Serif", Font.BOLD, 16));
        yCord += 70;
        prompt3.setBounds(25, yCord, prompt1.getPreferredSize().width, prompt1.getPreferredSize().height);

        String[] prompt3Choices = {"ID", "Author", "Title"};
        final JComboBox<String> cb1 = new JComboBox<>(prompt3Choices);
        cb1.setFont(new Font("Ariel", Font.PLAIN, 14));
        cb1.setMaximumSize(cb.getPreferredSize());
        cb1.setBounds(40 + prompt1.getWidth(), yCord, cb.getPreferredSize().width, cb.getPreferredSize().height);
        panel.add(cb1);

        JButton ok1 = new JButton("OK");
        ok1.setBounds(60 + cb.getWidth() + prompt1.getWidth(), yCord, 30 + ok.getPreferredSize().width, 3 + ok.getPreferredSize().height);
        ok1.addActionListener( e -> {
            bookCriteria = Objects.requireNonNull(cb1.getSelectedItem().toString());

        });

        JLabel prompt4 = new JLabel("Please enter data: ");
        prompt4.setFont(new Font("Serif", Font.BOLD, 16));
        yCord += 45;
        prompt4.setBounds(25, yCord, prompt2.getPreferredSize().width, prompt2.getPreferredSize().height);

        JTextArea text1 = new JTextArea();
        Font newFont1 = new Font(text.getFont().getName(), text.getFont().getStyle(), 16);
        text1.setFont(newFont1);
        text1.setEditable(true);
        text1.setPreferredSize(new Dimension(400, 100));
        text1.setBounds(30 + prompt2.getWidth(), yCord, 400, 45);
        text1.setLineWrap(true);
        text1.setWrapStyleWord(true);
        text1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchItem = text.getText();
                    bookSearch = text1.getText();
                    doStuff();
                }
            }
        });


        JButton returnBook = new JButton("Return");
        returnBook.addActionListener(e -> {
            searchItem = text.getText();
            bookSearch = text1.getText();
            doStuff();
        });
        yCord += 60;
        returnBook.setBounds(265, yCord, 10 + returnBook.getPreferredSize().width, 8 + returnBook.getPreferredSize().height);



        panel.add(prompt1);
        panel.add(prompt2);
        panel.add(ok);
        panel.add(text);

        panel.add(prompt3);
        panel.add(prompt4);
        panel.add(ok1);
        panel.add(returnBook);
        panel.add(text1);
        frame.setVisible(true);

    }
    public void doStuff() {
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

        String[] accountData = FindAccount.searchForPerson("customer", criteria, searchItem);
        String[] bookData = BookSearch.searchForBook(bookCriteria, bookSearch);

        if (accountData[0] == null) {
            JLabel returnData = new JLabel("Error: No person found!");
            returnData.setFont(new Font("Serif", Font.BOLD, 24));
            returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnData.setForeground(Color.RED);
            panel.add(returnData);
        }
        else if (bookData[0] == null) {
            JLabel returnData = new JLabel("Error: No book found!");
            returnData.setFont(new Font("Serif", Font.BOLD, 24));
            returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnData.setForeground(Color.RED);
            panel.add(returnData);
        }
        else {
            if (bookData[bookData.length - 1].equals("1")) {
                JLabel returnData = new JLabel("Book is already returned!");
                returnData.setFont(new Font("Serif", Font.BOLD, 24));
                returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
                returnData.setForeground(Color.RED);
                panel.add(returnData);
                return;
            }
            else {
                String sql = "UPDATE books SET Availability=1, Rented_to=" + null + " WHERE UPPER(Title)='" + bookData[2] + "'";
                connection.update(sql);
                int numRentals = Integer.parseInt(accountData[3]) - 1;
                String accSql = "UPDATE customer SET Number_of_Rentals=" + numRentals + ", Rentals=" + null + " WHERE UPPER(Name)='" + accountData[1] + "'";
                connection.update(accSql);
            }
            JLabel success = new JLabel("Book successfully returned!");
            success.setFont(new Font("Serif", Font.BOLD, 24));
            success.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(success);
        }
    }
}

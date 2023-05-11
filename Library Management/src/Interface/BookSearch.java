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

public class BookSearch {

    private static DBConnection connection = new DBConnection();
    private JLabel prompt = new JLabel("Enter Author here:");
    private String searchItem = "";
    private String choice = "author";

    public BookSearch() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setTitle("Search for Book");
        frame.add(panel);
        frame.setSize(new Dimension(550, 350));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel criteria = new JLabel("Select book search criteria, then click OK");
        criteria.setFont(new Font("Serif", Font.BOLD, 18));
        criteria.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(criteria);

        String[] choices = {"Author", "Title", "BookID", "Genre"};
        final JComboBox<String> cb = new JComboBox<String>(choices);
        cb.setFont(new Font("Ariel", Font.PLAIN, 15));
        cb.setMaximumSize(cb.getPreferredSize());
        panel.add(cb);

        JButton ok = new JButton("OK");
        ok.setSize(new Dimension(50, 40));
        ok.addActionListener(e -> {
            choice = Objects.requireNonNull(cb.getSelectedItem()).toString();
            prompt.setText("Enter " + choice + " here: ");
            frame.invalidate();
            frame.repaint();
        });
        panel.add(ok);
        prompt.setFont(new Font("Ariel", Font.PLAIN, 16));
        prompt.setBounds(-250, 150, 100, 20);
        panel.add(prompt);

        JPanel textPanel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextArea text = new JTextArea();
        Font newFont = new Font(text.getFont().getName(), text.getFont().getStyle(), 24);
        text.setFont(newFont);
        text.setEditable(true);
        text.setPreferredSize(new Dimension(325, 100));
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
        textPanel.add(text);
        panel.add(textPanel);
        frame.setVisible(true);

        JButton enter = new JButton("Enter");
        enter.addActionListener(e -> {
            searchItem = text.getText();
            findData();
        });
        panel.add(enter);

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
        String[] data = searchForBook(choice, searchItem);
        if (data[0] == null) {
            JLabel returnData = new JLabel("Error: No book found!");
            returnData.setFont(new Font("Serif", Font.BOLD, 24));
            returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnData.setForeground(Color.RED);
            panel.add(returnData);
        }
        else {
            JLabel bookID = new JLabel("BookID --- " + data[dataIndex++]);
            bookID.setFont(new Font("Serif", Font.BOLD, 18));
            bookID.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel author = new JLabel("Author --- " + data[dataIndex++]);
            author.setFont(new Font("Serif", Font.BOLD, 18));
            author.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel title = new JLabel("Title --- " + data[dataIndex++]);
            title.setFont(new Font("Serif", Font.BOLD, 18));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel genre = new JLabel("Genre --- " + data[dataIndex++]);
            genre.setFont(new Font("Serif", Font.BOLD, 18));
            genre.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel available;
            if (data[dataIndex].equals("1")) {
                available = new JLabel("Available --- Yes");
            } else {
                available = new JLabel("Available --- No");
            }
            available.setFont(new Font("Serif", Font.BOLD, 18));
            available.setAlignmentX(Component.CENTER_ALIGNMENT);


            panel.add(bookID);
            panel.add(author);
            panel.add(title);
            panel.add(genre);
            panel.add(available);
        }
    }
    public static String[] searchForBook(String criteria, String item) {
        String sql = "SELECT * FROM books WHERE LOWER";
        ResultSet rs = null;
        String[] result = new String[5];
        criteria = criteria.toUpperCase();
        switch (criteria) {
            case "TITLE":
                sql += "(Title) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            case "AUTHOR":
                sql += "(Author) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            case "GENRE":
                sql += "(Genre) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            case "ID":
                sql += "(BookID) = LOWER('" + item + "')";
                rs = connection.select(sql);
                break;
            default:
                System.out.println("Error with query");
                break;
        }
        if (rs == null) {
            return null;
        }
        else {
            try {
                rs.next();
                int index = 0;
                result[index++] = rs.getString("BookID");
                result[index++] = rs.getString("Author");
                result[index++] = rs.getString("Title");
                result[index++] = rs.getString("Genre");
                result[index] = rs.getString("Availability");
            }
            catch (Exception e) {}
            return result;
        }
    }
}

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

public class FindRental {

    static DBConnection connection = new DBConnection();
    private JPanel panel;
    private JFrame frame;
    private String searchCriteria = "ID";
    private String searchItem = "";

    private int yCord = 0;

    public FindRental() {
        panel = new JPanel();
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("Find Rental");
        frame.add(panel);
        frame.setSize(new Dimension(650, 275));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JLabel prompt = new JLabel("Select account search critieria, then click OK");
        prompt.setFont(new Font("Serif", Font.BOLD, 16));
        yCord += 10;
        prompt.setBounds(25, yCord, prompt.getPreferredSize().width, prompt.getPreferredSize().height);

        String[] promptChoices = {"ID", "Name", "Phone_Number"};
        final JComboBox<String> cb = new JComboBox<>(promptChoices);
        cb.setFont(new Font("Ariel", Font.PLAIN, 14));
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setBounds(40 + prompt.getWidth(), yCord, cb.getPreferredSize().width, cb.getPreferredSize().height);
        panel.add(cb);

        JButton ok = new JButton("OK");
        ok.setBounds(60 + cb.getWidth() + prompt.getWidth(), yCord, 30 + ok.getPreferredSize().width, 3 + ok.getPreferredSize().height);
        ok.addActionListener(e -> {
            searchCriteria = Objects.requireNonNull(cb.getSelectedItem().toString());

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

        JButton search = new JButton("Search");
        search.addActionListener(e -> {
            searchItem = text.getText();
            findData();
        });
        yCord += 60;
        search.setBounds(265, yCord, 10 + search.getPreferredSize().width, 8 + search.getPreferredSize().height);

        panel.add(prompt);
        panel.add(prompt2);
        panel.add(ok);
        panel.add(text);
        panel.add(search);
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

        String[] accountData = FindAccount.searchForPerson("customer", searchCriteria, searchItem);

        if (accountData[0] == null) {
            JLabel returnData = new JLabel("Error: No person found!");
            returnData.setFont(new Font("Serif", Font.BOLD, 24));
            returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnData.setForeground(Color.RED);
            panel.add(returnData);
            return;
        }
        else {
            if (accountData[accountData.length - 2].equals("0")) {
                JLabel returnData = new JLabel("No book checked out for account");
                returnData.setFont(new Font("Serif", Font.BOLD, 24));
                returnData.setAlignmentX(Component.CENTER_ALIGNMENT);
                returnData.setForeground(Color.RED);
                panel.add(returnData);
                return;
            }
            else {
                ResultSet rs = null;
                String[] result = new String[3];
                String bookID = accountData[accountData.length - 1];
                String sql = "SELECT * FROM books WHERE BookID=" + bookID;
                rs = connection.select(sql);
                if (rs == null) {
                    return;
                }
                else {
                    try {
                        rs.next();
                        int index = 0;
                        int colIndex = 1;
                        result[index++] = rs.getString(colIndex++);
                        result[index++] = rs.getString(colIndex++);
                        result[index] = rs.getString(colIndex);
                    }
                    catch(Exception e) {
                        return;
                    }

                }
                int dataIndex = 0;
                JLabel ID = new JLabel("ID --- " + result[dataIndex++]);
                ID.setFont(new Font("Serif", Font.BOLD, 18));
                ID.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel author = new JLabel("Author --- " + result[dataIndex++]);
                author.setFont(new Font("Serif", Font.BOLD, 18));
                author.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel genre = new JLabel("Genre --- " + result[dataIndex]);
                genre.setFont(new Font("Serif", Font.BOLD, 18));
                genre.setAlignmentX(Component.CENTER_ALIGNMENT);


                panel.add(ID);
                panel.add(author);
                panel.add(genre);
            }
        }

    }
}

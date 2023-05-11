package Interface;

import javax.swing.*;
import java.awt.*;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class MainMenu {
    private JButton button1, button2, button3, button4, button5;
    private JLabel label;

    private final int buttonWidth = 250;
    private final int buttonHeight = 45;

    public MainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("MAIN MENU");
        frame.add(panel);
        frame.setSize(new Dimension(450, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Main Menu Options:");
        label.setFont(new Font("Serif", Font.BOLD, 22));
        label.setBounds(130, 20, 200, 25);
        panel.add(label);

        button1 = new JButton("Find Book");
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        button1.setBounds(100, 50, buttonWidth, buttonHeight);
        button1.addActionListener(e-> {
            new BookSearch();
        });
        panel.add(button1);

        button2 = new JButton("Find An Account");
        button2.setFont(new Font("Arial", Font.BOLD, 16));
        button2.setBounds(100, 100, buttonWidth, buttonHeight);
        button2.addActionListener( e -> {
            new FindAccount();
        });
        panel.add(button2);

        button3 = new JButton("Check Out Book");
        button3.setFont(new Font("Arial", Font.BOLD, 16));
        button3.setBounds(100, 150, buttonWidth, buttonHeight);
        button3.addActionListener( e -> {
            new BookCheckOut();
        });
        panel.add(button3);

        button4 = new JButton("Return Book");
        button4.setFont(new Font("Arial", Font.BOLD, 16));
        button4.setBounds(100, 200, buttonWidth, buttonHeight);
        button4.addActionListener( e -> {
            new BookReturn();
        });
        panel.add(button4);

        button5 = new JButton("Find Customer's Rental");
        button5.setFont(new Font("Arial", Font.BOLD, 16));
        button5.setBounds(100, 250, buttonWidth, buttonHeight);
        button5.addActionListener(e -> {
            new FindRental();
        });
        panel.add(button5);


    }
}

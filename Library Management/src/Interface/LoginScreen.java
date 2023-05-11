package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
    MADE BY: Kyler Kinsey
    Status: Current Rising Senior at a University
    DATE FINISHED: 5/11/23

 */

public class LoginScreen implements ActionListener {
    private JLabel password1, label;
    private JTextField username;
    private JButton button;
    private JPasswordField password;

    // create JPanel instance
    public LoginScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setTitle("LOGIN PAGE");
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Username");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);

        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);

        password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        panel.add(password1);

        password = new JPasswordField();
        password.setBounds(100, 75, 193, 28);
        panel.add(password);

        button = new JButton("Login");
        button.setBounds(100, 110, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);
        panel.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username1 = username.getText();
        String password1 = String.valueOf(password.getPassword());

        if (username1.equals("admin") && password1.equals("admin")) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            new MainMenu();
        }
        else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch");
    }
}

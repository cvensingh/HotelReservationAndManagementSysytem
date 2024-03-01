package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Class representing the login page for the Hotel Management System
public class loginPage {

    // Components for the login form
    private JPanel loginPanel;
    private JTextField UserIdTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JButton forgotPasswordButton;
    private JLabel userIdField;
    private JLabel passwordlabel;
    private JButton exitButton;
    private JButton newUserButton;

    // Constructor for the login page
    public loginPage() {
        // Creating the main frame for the login page
        JFrame frame = new JFrame("Login Page for Hotel Management System");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.add(loginPanel);

        // ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserIdTextField.getText().isEmpty() || passwordField.getPassword().length <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter your ID and Password to login!");
                } else {
                    // Retrieving the password as a String
                    char[] c = passwordField.getPassword();
                    String uid = new String(c);

                    try {
                        // SQL query to validate user credentials
                        String fetch = "SELECT * FROM Login WHERE userId =? AND password =?";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(fetch);

                        // Setting parameters in the PreparedStatement
                        preparedStatement.setString(1, UserIdTextField.getText().trim());
                        preparedStatement.setString(2, uid.trim());

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            // Successful login
                            JOptionPane.showMessageDialog(null, "Login Success!");

                            frame.dispose();  // Close the login frame after successful login

                            // Assuming userAdmin is a class that opens the admin page
                            new userAdmin();

                        } else {
                            // Invalid credentials
                            JOptionPane.showMessageDialog(null, "Invalid userID or Password.\nPlease enter correct userID and Password.");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        // ActionListener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // ActionListener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserIdTextField.setText("");
                passwordField.setText("");
            }
        });

        // ActionListener for the forgot password button
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new updatePassword();
            }
        });

        // MouseListener for the "New User" label
        newUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new newUserPage();
            }
        });
    }

    // Main method to run the login page
    public static void main(String[] args) {
        new loginPage();
    }
}

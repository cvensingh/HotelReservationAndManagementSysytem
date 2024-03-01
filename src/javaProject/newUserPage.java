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

// Class representing the registration page for new users in the Hotel Management System
public class newUserPage {

    // Components for the registration form
    private JPanel newUserPanel;
    private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField contactField;
    private JTextField emailField;
    private JTextField uidField;
    private JTextField securityAnsField;
    private JButton submitButton;
    private JPasswordField passwordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton othersRadioButton;
    private JComboBox comboBox;
    private JPasswordField rePasswordField;
    private JButton resetButton;
    private JButton backButton;

    private JLabel alreadyAcc;
    private JButton cancelButton;
    private JCheckBox showPasswordCheckBox;
    String Gender;

    // Constructor for the new user registration page
    public newUserPage() {

        // Creating the main frame for the registration page
        JFrame frame = new JFrame("New User Page for Hotel Management System Project");
        frame.pack();
        // frame.setSize(500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(newUserPanel);

        // Grouping radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);
        buttonGroup.add(othersRadioButton);

        // ActionListener for male radio button
        maleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maleRadioButton.isSelected()) {
                    Gender = "Male";
                }
            }
        });

        // ActionListener for female radio button
        femaleRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (femaleRadioButton.isSelected()) {
                    Gender = "Female";
                }
            }
        });

        // ActionListener for others radio button
        othersRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (othersRadioButton.isSelected()) {
                    othersRadioButton.setSelected(true);
                    Gender = "Others";
                }
            }
        });

        // ActionListener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstNameField.getText().length() <= 0 || lastNameField.getText().length() <= 0 ||
                        dobField.getText().length() <= 0 || contactField.getText().length() <= 0 || emailField.getText().length() <= 0 ||
                        uidField.getText().length() <= 0 || passwordField.getPassword().length <= 0 || rePasswordField.getPassword().length <= 0
                        || securityAnsField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Kindly fill all data first before submitting!!!");
                } else {
                    // compare password and Re-Enter Password.
                    char[] pass = passwordField.getPassword();
                    char[] rePass = rePasswordField.getPassword();
                    String password = new String(pass);
                    String rePassword = new String(rePass);
                    if (rePassword.equals(password)) {
                        try {
                            String sql = "INSERT INTO Login values(?,?,?,?,?,?,?,?,?,?,?)";
                            String url = "jdbc:mysql://localhost:3306/myPro";
                            Connection connection = DriverManager.getConnection(url, "root", "root");
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1, firstNameField.getText());
                            preparedStatement.setString(2, middleNameField.getText());
                            preparedStatement.setString(3, lastNameField.getText());
                            preparedStatement.setString(4, Gender);
                            preparedStatement.setString(5, dobField.getText());
                            preparedStatement.setString(6, contactField.getText());
                            preparedStatement.setString(7, emailField.getText());
                            preparedStatement.setString(8, uidField.getText());
                            preparedStatement.setString(9, password);
                            preparedStatement.setString(10, comboBox.getSelectedItem().toString());
                            preparedStatement.setString(11, securityAnsField.getText());

                            int a = preparedStatement.executeUpdate();
                            if (a > 0) {
                                JOptionPane.showMessageDialog(frame, "Your account has been created successfully!!\nPlease note your User ID: "
                                        + uidField.getText() + "\nand your password is: " + password + " for future login.");
                                System.out.println("Account has been created successfully");
                                frame.dispose();
                                new loginPage();
                            } else {
                                JOptionPane.showMessageDialog(null, "Something went wrong!!!\nYour account is not created...");
                            }
                            connection.close();
                            preparedStatement.close();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password didn't match from Re-entered password");
                    }
                }
            }
        });

        // ActionListener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new startupPage();
            }
        });

        // ActionListener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameField.setText(null);
                middleNameField.setText(null);
                lastNameField.setText(null);
                dobField.setText(null);
                contactField.setText(null);
                emailField.setText(null);
                uidField.setText(null);
                passwordField.setText(null);
                rePasswordField.setText(null);
                securityAnsField.setText(null);
            }
        });

        // MouseListener for the "Already have an account?" label
        alreadyAcc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new loginPage();
            }
        });

        // ActionListener for the cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameField.setText(null);
                middleNameField.setText(null);
                lastNameField.setText(null);
                dobField.setText(null);
                contactField.setText(null);
                emailField.setText(null);
                uidField.setText(null);
                buttonGroup.clearSelection();
                comboBox.setSelectedIndex(0);
                securityAnsField.setText(null);
                JOptionPane.showMessageDialog(null, "Your registration has been cancelled");
            }
        });
    }

    // Main method to run the new user registration page
    public static void main(String[] args) {
        new newUserPage();
    }
}

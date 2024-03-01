package javaProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

// Class representing the employee registration page
public class employee {

    // Components for the employee registration form
    private JTextField idField;
    private JTextField fNameField;
    private JTextField mNameField;
    private JTextField lNameField;
    private JRadioButton maleRadioBtn;
    private JRadioButton otherRadioBtn;
    private JRadioButton fRadioBtn;
    private JTextField dobField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField salField;
    private JTextField jDateField;
    private JTextField adhaarField;
    private JTextField addressfield;
    private JTextField distField;
    private JTextField pinField;
    private JComboBox stateBox;
    private JComboBox countryBox;
    private JLabel stateField;
    private JLabel countryField;
    private JLabel saveBtn;
    private JLabel backBtn;
    private JPanel employeePanel;
    String Gender;

    // Constructor for the employee registration page
    public employee() {
        // Creating the main frame for the employee registration page
        JFrame frame = new JFrame("Employee page for Hotel Management System");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.add(employeePanel);

        // Grouping radio buttons to ensure only one can be selected
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioBtn);
        buttonGroup.add(fRadioBtn);
        buttonGroup.add(otherRadioBtn);

        // ActionListener for the male radio button
        maleRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maleRadioBtn.isSelected()) {
                    Gender = "Male";
                }
            }
        });

        // ActionListener for the female radio button
        fRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fRadioBtn.isSelected()) {
                    Gender = "Female";
                }
            }
        });

        // ActionListener for the other radio button
        otherRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (otherRadioBtn.isSelected()) {
                    Gender = "Others";
                }
            }
        });

        // ActionListener for the back button to go back to the CRUD employee page
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new crudEmployee();
            }
        });

        // ActionListener for the save button to save employee details
        saveBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (idField.getText().isEmpty() || fNameField.getText().isEmpty() ||
                        lNameField.getText().isEmpty() || dobField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                        emailField.getText().isEmpty() || departmentField.getText().isEmpty() || salField.getText().isEmpty() ||
                        jDateField.getText().isEmpty() || adhaarField.getText().isEmpty() || addressfield.getText().isEmpty() ||
                        distField.getText().isEmpty() || pinField.getText().isEmpty() || stateField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide your information first to save!!!");
                } else {
                    try {
                        // SQL query to insert employee data
                        String sql = "INSERT into employees values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, idField.getText());
                        preparedStatement.setString(2, fNameField.getText());
                        preparedStatement.setString(3, mNameField.getText());
                        preparedStatement.setString(4, lNameField.getText());
                        preparedStatement.setString(5, Gender);
                        preparedStatement.setString(6, dobField.getText());
                        preparedStatement.setString(7, phoneField.getText());
                        preparedStatement.setString(8, emailField.getText());
                        preparedStatement.setString(9, departmentField.getText());
                        preparedStatement.setString(10, salField.getText());
                        preparedStatement.setString(11, jDateField.getText());
                        preparedStatement.setString(12, adhaarField.getText());
                        preparedStatement.setString(13, addressfield.getText());
                        preparedStatement.setString(14, distField.getText());
                        preparedStatement.setString(15, pinField.getText());
                        preparedStatement.setString(16, Objects.requireNonNull(stateBox.getSelectedItem()).toString());
                        preparedStatement.setString(17, Objects.requireNonNull(countryBox.getSelectedItem()).toString());

                        // Executing the insertion query
                        int a = preparedStatement.executeUpdate();

                        if (a > 0) {
                            // Informing the user about the successful save
                            JOptionPane.showMessageDialog(null, "Details are successfully saved..." +
                                    "\nPlease save your ID: " + idField.getText() + " for the future!!");
                            // Clearing the text fields after successful save
                            idField.setText(null);
                            fNameField.setText(null);
                            mNameField.setText(null);
                            lNameField.setText(null);
                            buttonGroup.clearSelection();
                            dobField.setText(null);
                            phoneField.setText(null);
                            emailField.setText(null);
                            departmentField.setText(null);
                            salField.setText(null);
                            jDateField.setText(null);
                            adhaarField.setText(null);
                            addressfield.setText(null);
                            distField.setText(null);
                            pinField.setText(null);
                            stateBox.setSelectedIndex(0);
                            countryBox.setSelectedIndex(0);

                        } else {
                            // Informing the user about an error during the save
                            JOptionPane.showMessageDialog(null, "Something went wrong. Try again...");

                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }

                }
            }
        });
    }

    // Main method to run the employee registration page
    public static void main(String[] args) {
        new employee();
    }
}

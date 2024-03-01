package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Class representing the employee deletion page
public class DeleteEmployee {

    // Components for the employee deletion form
    private JPanel deletePanel;
    private JTextField iDTextField;
    private JTextField contactTextField;
    private JButton deleteButton;
    private JButton backButton;

    // Constructor for the employee deletion page
    public DeleteEmployee() {
        // Creating the main frame for the employee deletion page
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(deletePanel);
        frame.setVisible(true);

        // ActionListener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checking if the necessary fields are filled
                if (iDTextField.getText().isEmpty() || contactTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Please enter your employee ID and contact number to delete your credentials...");
                }
                else {
                    try {
                        // SQL query to delete employee data
                        String fetch = "DELETE  FROM employees WHERE empId = ? and phone = ?";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(fetch);
                        preparedStatement.setString(1, iDTextField.getText());
                        preparedStatement.setString(2, contactTextField.getText());

                        // Executing the deletion query
                        int a = preparedStatement.executeUpdate();

                        if (a > 0) {
                            // Confirming deletion to the user
                            JOptionPane.showMessageDialog(frame, "Data cannot be retrieved. Are you sure you want to delete? Click OK");
                            JOptionPane.showMessageDialog(frame, "Your data has been deleted successfully!!!");
                            // Clearing the text fields after successful deletion
                            iDTextField.setText(null);
                            contactTextField.setText(null);
                        } else {
                            // Informing the user that the provided ID and contact number do not exist
                            JOptionPane.showMessageDialog(frame, "This ID and contact number do not exist. Please provide correct information.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex);
                    }
                }
            }
        });

        // ActionListener for the back button to go back to the CRUD employee page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new crudEmployee();
            }
        });
    }

    // Main method to run the employee deletion page
    public static void main(String[] args) {
        new DeleteEmployee();
    }
}

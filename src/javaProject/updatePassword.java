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

// Class representing the update password page
public class updatePassword {
    private JPanel updatePassPanel;
    private JPasswordField updatePasswordField;
    private JTextField updateAnsField;
    private JComboBox updateComboBox;
    private JTextField updateContactTextField;
    private JPasswordField updateConfirmPasswordField;
    private JButton updateButton;
    private JLabel cancelLabel;
    private JLabel errorLabel;
    private JButton resetButton;

    // Constructor for the update password page
    public updatePassword() {
        // Creating the main frame for the page
        JFrame frame = new JFrame("Update password Page for Hotel Management System");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.add(updatePassPanel);

        // Action listener for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateContactTextField.getText().isEmpty() || updateAnsField.getText().isEmpty() ||
                        updatePasswordField.getPassword().length == 0 || updateConfirmPasswordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Kindly fill the data first!!!");
                } else {
                    char[] pass = updatePasswordField.getPassword();
                    char[] repass = updateConfirmPasswordField.getPassword();
                    String upPass = new String(pass);
                    String upRePass = new String(repass);
                    if (upRePass.equals(upPass)) {
                        try {
                            String updateData = "UPDATE Login SET password=? WHERE ContactNumber = ? AND SecurityQues = ? AND SecurityAns = ?";
                            String url = "jdbc:mysql://localhost:3306/myPro";
                            Connection connection = DriverManager.getConnection(url, "root", "root");
                            PreparedStatement preparedStatement = connection.prepareStatement(updateData);

                            preparedStatement.setString(2, updateContactTextField.getText());
                            preparedStatement.setString(3, updateComboBox.getSelectedItem().toString());
                            preparedStatement.setString(4, updateAnsField.getText());
                            preparedStatement.setString(1, upPass);

                            int a = preparedStatement.executeUpdate();
                            if (a > 0) {
                                updateContactTextField.setText(null);
                                updateComboBox.setSelectedIndex(0);
                                updateAnsField.setText(null);
                                JOptionPane.showMessageDialog(null, "Your password has been updated successfully!!!");

                                frame.dispose();
                                new loginPage();
                            } else {
                                JOptionPane.showMessageDialog(null, "Data not matched!!\nPassword did not update!!!");
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password and Confirm Password did not match!!");
                        updatePasswordField.setText(null);
                        updateConfirmPasswordField.setText(null);
                    }
                }
            }
        });

        // Action listener for the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContactTextField.setText(null);
                updatePasswordField.setText(null);
                updateConfirmPasswordField.setText(null);
                updateAnsField.setText(null);
                updateComboBox.setSelectedIndex(0);
            }
        });

        // MouseAdapter for the cancel label
        cancelLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null, "Password didn't Updated");
                updateContactTextField.setText(null);
                updateComboBox.setSelectedIndex(0);
                updateAnsField.setText(null);
                updatePasswordField.setText("");
                updateConfirmPasswordField.setText(null);
                frame.dispose();
                new loginPage();
            }
        });
    }

    // Main method to run the update password page
    public static void main(String[] args) {
        new updatePassword();
    }
}

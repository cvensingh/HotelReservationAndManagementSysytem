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
import java.util.Objects;

// Class representing the update employee details page
public class updateEmployee {
    private JPanel employeePanel;
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
    private JLabel stateField;
    private JLabel countryField;
    private JComboBox countryBox;
    private JLabel updateButton;
    private JLabel backBtn;
    private JPanel updatePanel;
    private JButton searchButton;
    private JTextField idTextField;
    String Gender;

    // Constructor for the update employee details page
    public updateEmployee() {
        // Creating the main frame for the page
        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(updatePanel);
        frame.setVisible(true);

        // Creating a ButtonGroup for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioBtn);
        buttonGroup.add(fRadioBtn);
        buttonGroup.add(otherRadioBtn);

        // Action listeners for radio buttons
        maleRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (maleRadioBtn.isSelected()) {
                    Gender = "Male";
                }
            }
        });
        fRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fRadioBtn.isSelected()) {
                    Gender = "Female";
                }
            }
        });
        otherRadioBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (otherRadioBtn.isSelected()) {
                    Gender = "Others";
                }
            }
        });

        // Action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter Your ID to get Details!!!");
                } else {
                    try {
                        String sql = "SELECT * from employees WHERE empId=?";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, idTextField.getText());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            // Setting values from the database to the fields
                            String a = resultSet.getString("empId").trim();
                            idField.setText(String.valueOf(a));
                            String b = resultSet.getString("firstName").trim();
                            fNameField.setText(String.valueOf(b));
                            String c = resultSet.getString("middleName").trim();
                            mNameField.setText(String.valueOf(c));
                            String d = resultSet.getString("lastName").trim();
                            lNameField.setText(String.valueOf(d));
                            String f = resultSet.getString("gender").trim();
                            if (f.equalsIgnoreCase("Male")) {
                                maleRadioBtn.setSelected(true);
                            } else if (f.equalsIgnoreCase("Female")) {
                                fRadioBtn.setSelected(true);
                            } else if (f.equalsIgnoreCase("Other")) {
                                otherRadioBtn.setSelected(true);
                            }
                            String g = resultSet.getString("dob").trim();
                            dobField.setText(String.valueOf(g));
                            String h = resultSet.getString("phone").trim();
                            phoneField.setText(String.valueOf(h));
                            String i = resultSet.getString("email").trim();
                            emailField.setText(String.valueOf(i));
                            String j = resultSet.getString("department").trim();
                            departmentField.setText(String.valueOf(j));
                            String k = resultSet.getString("salary").trim();
                            salField.setText(String.valueOf(k));
                            String l = resultSet.getString("joinDate").trim();
                            jDateField.setText(String.valueOf(l));
                            String m = resultSet.getString("adhaarNum").trim();
                            adhaarField.setText(String.valueOf(m));
                            String n = resultSet.getString("address").trim();
                            addressfield.setText(String.valueOf(n));
                            String o = resultSet.getString("district").trim();
                            distField.setText(String.valueOf(o));
                            String p = resultSet.getString("picode").trim();
                            pinField.setText(String.valueOf(p));
                            String q = resultSet.getString("state").trim();
                            if (q.equalsIgnoreCase("Bihar")) {
                                stateBox.setSelectedIndex(4);
                            } else if (q.equalsIgnoreCase("Uttar Pradesh")) {
                                stateBox.setSelectedIndex(27);
                            } else if (q.equalsIgnoreCase("Goa")) {
                                stateBox.setSelectedIndex(6);
                            } else if (q.equalsIgnoreCase("Rajasthan")) {
                                stateBox.setSelectedIndex(21);
                            } else if (q.equalsIgnoreCase("Maharashtra")) {
                                stateBox.setSelectedIndex(14);
                            } else if (q.equalsIgnoreCase("Haryana")) {
                                stateBox.setSelectedIndex(8);
                            } else if (q.equalsIgnoreCase("Madhya Pradesh")) {
                                stateBox.setSelectedIndex(13);
                            } else if (q.equalsIgnoreCase("Gujarat")) {
                                stateBox.setSelectedIndex(7);
                            }

                            String r = resultSet.getString("country");
                            if (r.equalsIgnoreCase("Bharat")) {
                                countryBox.setSelectedIndex(1);
                            } else if (r.equalsIgnoreCase("others")) {
                                countryBox.setSelectedIndex(2);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Data not found...");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        // MouseAdapter for the update button
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (idField.getText().isEmpty() || fNameField.getText().isEmpty() || mNameField.getText().isEmpty() ||
                        lNameField.getText().isEmpty() || dobField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                        emailField.getText().isEmpty() || departmentField.getText().isEmpty() || salField.getText().isEmpty()||
                        jDateField.getText().isEmpty() || adhaarField.getText().isEmpty() || addressfield.getText().isEmpty() ||
                        distField.getText().isEmpty() || pinField.getText().isEmpty() || stateField.getText().isEmpty() ||
                        stateField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please provide your information first to update!!!");
                } else {
                    try {
                        // SQL query to update employee details
                        String sql = "UPDATE employees SET firstName=?,middleName=?,lastName=?,dob=?,phone=?,email=?,department=?,salary=?,joinDate=?,address=?,district=?,picode=?,state=?,country=? WHERE empId=?";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);

                        // Setting values to the PreparedStatement
                        preparedStatement.setString(1, fNameField.getText());
                        preparedStatement.setString(2, mNameField.getText());
                        preparedStatement.setString(3, lNameField.getText());
                        preparedStatement.setString(4, dobField.getText());
                        preparedStatement.setString(5, phoneField.getText());
                        preparedStatement.setString(6, emailField.getText());
                        preparedStatement.setString(7, departmentField.getText());
                        preparedStatement.setString(8, salField.getText());
                        preparedStatement.setString(9, jDateField.getText());
                        preparedStatement.setString(10, addressfield.getText());
                        preparedStatement.setString(11, distField.getText());
                        preparedStatement.setString(12, pinField.getText());
                        preparedStatement.setString(13, Objects.requireNonNull(stateBox.getSelectedItem()).toString());
                        preparedStatement.setString(14, Objects.requireNonNull(countryBox.getSelectedItem()).toString());
                        preparedStatement.setString(15, idField.getText());

                        int a = preparedStatement.executeUpdate();

                        if (a > 0) {
                            JOptionPane.showMessageDialog(null, "Your details have been updated successfully!!!");
                            // Clearing fields after successful update
                            idTextField.setText(null);
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
                            JOptionPane.showMessageDialog(null, "Data not updated!!!");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        // MouseAdapter for the back button
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new crudEmployee();
            }
        });
    }

    // Main method to run the update employee details page
    public static void main(String[] args) {
        new updateEmployee();
    }
}

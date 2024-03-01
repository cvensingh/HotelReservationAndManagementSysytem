package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Date;

// Class representing the check-out functionality
public class checkOut {

    // Components for the check-out form
    private JTextField bookingIdField;
    private JTextField contactNumberField;
    private JTextField nameField;
    private JTextField mobileField;
    private JTextField checkOutField;
    private JTextField paidField;
    private JTextField payAbleField;
    private JPanel checkOutPanel;
    private JButton checkOutButton;
    private JTextField checkInField;
    private JTextField roomTypeField;
    private JTextField showBookingField;
    private JTextField showContField;
    private JTextField showNameField;
    private JTextField showMobField;
    private JTextField showCheckInField;
    private JTextField showAdharField;
    private JTextField showRoomTypeField;
    private JTextField showPaidField;
    private JTextField showPayableField;
    private JPanel showBookingPanel;
    private JTextField bookedRoomField;
    private JPanel showPanel;
    private JButton showButton;
    private JButton backButton;
    private JLabel nullLabel;

    // Constructor for the check-out functionality
    public checkOut() {
        // Creating the main frame for check-out
        JFrame frame = new JFrame("check Out JForm");
        frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(checkOutPanel);

        // ActionListener for the "Show" button to display customer details
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checking if the required fields are empty
                if (showBookingField.getText().isEmpty() || showContField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Enter your bookingID and contact number to get Details!");
                }
                else {
                    try{
                        // Connecting to the database and retrieving customer details
                        String sql = "SELECT * FROM customerForm WHERE BookingId=? AND ContactNumber=?";
                        String url =  "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url,"root","root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,showBookingField.getText());
                        preparedStatement.setString(2,showContField.getText());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        // Displaying the customer details if found
                        if (resultSet.next()){
                            // Extracting and setting customer details
                            String a = resultSet.getString("CustomerName").trim();
                            showNameField.setText(a);
                            String b = resultSet.getString("ContactNumber").trim();
                            showMobField.setText(b);
                            String c = resultSet.getString("AdharNumber").trim();
                            showAdharField.setText(c);
                            String d = resultSet.getString("BookingDate").trim();
                            showCheckInField.setText(d);
                            String f = resultSet.getString("RoomType").trim();
                            showRoomTypeField.setText(f);
                            String g = resultSet.getString("RoomQuantity").trim();
                            bookedRoomField.setText(g);
                            String h = resultSet.getString("AdvanceMoney").trim();
                            showPaidField.setText(h);
                            String i = resultSet.getString("RemainAmount").trim();
                            showPayableField.setText(i);
                        }
                        // Displaying a message if customer details are not found
                        else {
                            JOptionPane.showMessageDialog(null,"Invalid BookingID or \nYou are not a customer of IF-ELSE Hotel !");
                            showBookingField.setText(null);
                            showContField.setText(null);
                        }
                    }
                    // Handling exceptions
                    catch ( Exception ex ){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                }
            }
        });

        // ActionListener for the "Check Out" button to initiate the check-out process
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checking if the required fields are empty
                if (bookingIdField.getText().isEmpty() || contactNumberField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter your bookingID and contact number!!!\n then click on CheckOut.");
                }
                else {
                    try {
                        // Connecting to the database and retrieving customer details
                        String sql = "SELECT * FROM customerForm WHERE BookingId=? AND ContactNumber=?";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url,"root","root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,bookingIdField.getText());
                        preparedStatement.setString(2,contactNumberField.getText());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        // Displaying the customer details if found
                        if (resultSet.next()){
                            // Extracting and setting customer details
                            String a = resultSet.getString("CustomerName").trim();
                            nameField.setText(String.valueOf(a));
                            String b = resultSet.getString("ContactNumber").trim();
                            mobileField.setText(b);
                            String c = resultSet.getString("BookingDate").trim();
                            checkInField.setText(c);
                            String d = resultSet.getString("RoomType").trim();
                            roomTypeField.setText(d);
                            String f = resultSet.getString("advanceMoney").trim();
                            paidField.setText(f);
                            String g = resultSet.getString("RemainAmount").trim();
                            payAbleField.setText(g);
                            // Getting the current date and setting it as the check-out date
                            Date date = new Date();
                            checkOutField.setText(String.valueOf(date));
                            // Displaying a message to pay the due amount
                            nullLabel.setForeground(Color.RED);
                            nullLabel.setText("plz pay your due of Rs. " +payAbleField.getText()+" amount!!");
                        }
                        // Displaying a message if customer details are not found
                        else {
                            JOptionPane.showMessageDialog(null,"Invalid BookingID or \nYou are not a customer of IF-ELSE Hotel !");
                            bookingIdField.setText(null);
                            contactNumberField.setText(null);
                        }
                    }
                    // Handling exceptions
                    catch (Exception ex ){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                }
            }
        });

        // ActionListener for the "Back" button to go back to the customer form
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new customerForm();
            }
        });
    }

    // Main method to run the check-out functionality
    public static void main ( String [] args){
        new checkOut();
    }
}

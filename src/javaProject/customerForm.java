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
import java.util.Date;
import java.util.Objects;

// Class representing the customer form for hotel management
public class customerForm {

    // Components for the customer form
    private JTextField contactNumber;
    private JTextField bookingdate;
    private JTextField customerName;
    private JTextField adharNum;
    private JTextField price;
    private JTextField advance;
    private JTextField reaminingAmt;
    private JTextField roomQuantity;
    private JTextField availableRoom;
    private JButton saveButton;
    private JButton checkOutButton;
    private JButton showBookingButton;
    private JPanel CustomerPanel;
    private JButton backButton;
    private JComboBox qtyBox;
    private JComboBox roomBox;

    // Constructor for the customer form
    public customerForm() {
        // Creating the main frame for the customer form
        JFrame frame = new JFrame("Customer Form for hotel management project");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // Adding the customer form panel to the frame
        frame.add(CustomerPanel);
        frame.setVisible(true);

        // Making certain fields non-editable
        bookingdate.setEditable(false);
        price.setEditable(false);
        reaminingAmt.setEditable(false);
        availableRoom.setEditable(false);

        // Setting the current date to the booking date field
        Date date = new Date();
        bookingdate.setText(String.valueOf(date));

        // Default room price
        int roomPrice = 3500;
        price.setText(String.valueOf(roomPrice));
        price.setText(null);

        // Handling mouse click on the remaining amount field
        reaminingAmt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int adv = Integer.parseInt(String.valueOf(advance.getText()));
                int price2 = Integer.parseInt(String.valueOf(price.getText()));
                int remain = price2 - adv;
                reaminingAmt.setText(String.valueOf(remain));

                if (adv > price2) {
                    JOptionPane.showMessageDialog(null, "Your advance money is greater than actual money!");
                } else if (remain == 0) {
                    JOptionPane.showMessageDialog(frame, "Dear\n" + customerName.getText() + " you are paying the full amount!");
                }
            }
        });

        // Handling mouse click on the check-out button
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new checkOut();
            }
        });

        // Handling mouse click on the available room field
        availableRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int totalRoom = 5;
                int avail = Integer.parseInt(String.valueOf(qtyBox.getSelectedItem()));

                if (avail == totalRoom) {
                    JOptionPane.showMessageDialog(null, "Hey! " + customerName.getText() +
                            " you booked the maximum available rooms. You will get Rs.500 off the total.\nThank you");
                    availableRoom.setText("No Room Available");
                }

                int available = totalRoom - avail;
                availableRoom.setText(String.valueOf(available));
            }
        });

        // Handling mouse click on the room price field
        price.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String roomType = Objects.requireNonNull(roomBox.getSelectedItem()).toString();
                int vipPrice = 3500;
                int acPrice = 2800;
                int nonAcPrice = 800;

                if (roomType.equals("VIP Room")) {
                    int avail = Integer.parseInt(String.valueOf(qtyBox.getSelectedItem()));
                    if (avail >= 2) {
                        int roomCost = avail * vipPrice;
                        price.setText(String.valueOf(roomCost));
                    } else {
                        price.setText(String.valueOf(vipPrice));
                    }
                } else if (roomType.equals("Ac Room")) {
                    int avail = Integer.parseInt(String.valueOf(qtyBox.getSelectedItem()));
                    if (avail >= 2) {
                        int roomCost = avail * acPrice;
                        price.setText(String.valueOf(roomCost));
                    } else {
                        price.setText(String.valueOf(acPrice));
                    }
                } else if (roomType.equals("Non-ac Room")) {
                    int avail = Integer.parseInt(String.valueOf(qtyBox.getSelectedItem()));
                    if (avail >= 2) {
                        int roomCost = avail * nonAcPrice;
                        price.setText(String.valueOf(roomCost));
                    } else {
                        price.setText(String.valueOf(nonAcPrice));
                    }
                }
            }
        });

        // Handling mouse click on the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new roomType();
            }
        });

        // Handling mouse click on the show booking button
        showBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new checkOut();
            }
        });

        // Handling mouse click on the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bookingdate.getText().isEmpty() || customerName.getText().isEmpty() ||
                        contactNumber.getText().isEmpty() || adharNum.getText().isEmpty() ||
                        availableRoom.getText().isEmpty() || price.getText().isEmpty() ||
                        advance.getText().isEmpty() || reaminingAmt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your details first...");
                } else {
                    try {
                        // Database insertion query
                        String sql = "INSERT INTO customerForm (BookingDate, CustomerName, ContactNumber, AdharNumber, RoomType, RoomQuantity, RoomPrice, AdvanceMoney, RemainAmount, AvailableRoom) VALUES (?,?,?,?,?,?,?,?,?,?)";
                        String url = "jdbc:mysql://localhost:3306/myPro";
                        Connection connection = DriverManager.getConnection(url, "root", "root");
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);

                        // Setting values to query parameters
                        preparedStatement.setString(1, bookingdate.getText());
                        preparedStatement.setString(2, customerName.getText());
                        preparedStatement.setString(3, contactNumber.getText());
                        preparedStatement.setString(4, adharNum.getText());
                        preparedStatement.setString(5, Objects.requireNonNull(roomBox.getSelectedItem().toString()));
                        preparedStatement.setString(6, Objects.requireNonNull(qtyBox.getSelectedItem().toString()));
                        preparedStatement.setString(7, price.getText());
                        preparedStatement.setString(8, advance.getText());
                        preparedStatement.setString(9, reaminingAmt.getText());
                        preparedStatement.setString(10, availableRoom.getText());

                        // Executing the query
                        int column = preparedStatement.executeUpdate();

                        if (column != 0) {
                            // Resetting form fields after successful booking
                            bookingdate.setText(null);
                            customerName.setText(null);
                            contactNumber.setText(null);
                            adharNum.setText(null);
                            roomBox.setSelectedIndex(0);
                            qtyBox.setSelectedIndex(0);
                            price.setText(null);
                            advance.setText(null);
                            reaminingAmt.setText(null);
                            availableRoom.setText(null);

                            // Informing the user about the successful booking
                            JOptionPane.showMessageDialog(null, "Your booking was successful.\nWelcome to IF-ELSE Hotel");

                            // Updating the booking date and room quantity fields
                            Date date1 = new Date();
                            bookingdate.setText(String.valueOf(date1));
                            qtyBox.setSelectedIndex(0);
                            roomBox.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong... Try Again");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
    }

    // Main method to run the customer form
    public static void main(String[] args) {
        new customerForm();
    }
}

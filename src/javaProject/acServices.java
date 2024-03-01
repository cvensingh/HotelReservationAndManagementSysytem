package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the air conditioning services page
public class acServices {

    // Components for the air conditioning services form
    private JList list2;
    private JButton acButton;
    private JButton backButton;
    private JPanel acType;

    // Constructor for the air conditioning services page
    public acServices() {
        // Creating the main frame for the air conditioning services page
        JFrame frame = new JFrame("Room Type for hotel management project");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // Adding the air conditioning services panel to the frame
        frame.add(acType);
        frame.setVisible(true);

        // ActionListener for the "Back" button to go back to the room type page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new roomType();
            }
        });

        // ActionListener for the "AC" button to proceed to the customer form
        acButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new customerForm();
            }
        });
    }

    // Main method to run the air conditioning services page
    public static void main(String[] args) {
        new acServices();
    }
}

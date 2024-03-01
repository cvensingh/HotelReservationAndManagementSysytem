package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the non-A/C services page for hotel management project
public class nonAcServices {
    private JList list3;
    private JButton nonAcButton;
    private JButton backButton;
    private JPanel nonAcType;

    // Constructor for the non-A/C services page
    public nonAcServices() {
        // Creating the main frame for the non-A/C services page
        JFrame frame = new JFrame("Room Type for Hotel Management Project");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        frame.add(nonAcType);
        frame.setVisible(true);

        // ActionListener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new roomType();
            }
        });

        // ActionListener for the non-A/C button
        nonAcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new customerForm();
            }
        });
    }

    // Main method to run the non-A/C services page
    public static void main(String[] args) {
        new nonAcServices();
    }
}

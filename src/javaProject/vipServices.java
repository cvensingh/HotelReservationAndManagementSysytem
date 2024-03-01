package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class representing the VIP Services page
public class vipServices {
    private JList list1;
    private JPanel panel1;
    private JButton vipButton;
    private JButton backButton;

    // Constructor for the VIP Services page
    public vipServices() {
        JFrame frame = new JFrame("VIP Services");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(panel1);
        frame.setVisible(true);

        // ActionListener for the backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new roomType();
            }
        });

        // ActionListener for the vipButton
        vipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new customerForm();
            }
        });
    }

    // Main method to run the VIP Services page
    public static void main(String[] args) {
        new vipServices();
    }
}

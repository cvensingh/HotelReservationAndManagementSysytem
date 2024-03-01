package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class representing the room type selection page for hotel management project
public class roomType {
    private JPanel roomPanel;
    private JLabel exitBtn;
    private JLabel vipBtn;
    private JLabel acBtn;
    private JLabel nonAcBtn;

    // Constructor for the room type selection page
    public roomType() {
        // Creating the main frame for the room type selection page
        JFrame frame = new JFrame("Room Type for Hotel Management Project");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        frame.add(roomPanel);
        frame.setVisible(true);

        // MouseAdapter for the exit button
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new userAdmin();
            }
        });

        // MouseAdapter for the VIP button
        vipBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new vipServices();
            }
        });

        // MouseAdapter for the AC button
        acBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new acServices();
            }
        });

        // MouseAdapter for the Non-AC button
        nonAcBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new nonAcServices();
            }
        });
    }

    // Main method to run the room type selection page
    public static void main(String[] args) {
        new roomType();
    }
}

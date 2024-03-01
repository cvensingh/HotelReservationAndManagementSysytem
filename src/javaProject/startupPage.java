package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class representing the startup page for the Hotel Management System
public class startupPage {
    private JPanel startUpPanel;
    private JLabel hotelImage;
    private JLabel forwardBtn;

    // Constructor for the startup page
    public startupPage() {
        // Creating the main frame for the startup page
        JFrame frame = new JFrame("User And Admin Page for Hotel Management System");
        frame.pack();
        // frame.setSize(500,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        frame.add(startUpPanel);

        // MouseAdapter for the forward button
        forwardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new loginPage();
            }
        });
    }

    // Main method to run the startup page
    public static void main(String[] args) {
        new startupPage();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

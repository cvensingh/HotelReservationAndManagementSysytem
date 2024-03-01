package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class representing the User and Admin page
public class userAdmin {
    private JLabel adminIconLabel;
    private JPasswordField adminPasswordField;
    private JLabel adminPLabel;
    private JPanel userAdminPanel;
    private JLabel heading;
    private JLabel teamLabelIcon;
    private JPasswordField teamPasswordField;
    private JLabel teamPasswordLabel;
    private JLabel customerClick;
    private JLabel adminClick;
    private JLabel adminErrorLabel;
    private JLabel teamErrorLabel;
    private JLabel adminResetLabel;
    private JLabel teamResetLabel;
    private JButton xButton;

    // Constructor for the User and Admin page
    public userAdmin() {

        JFrame frame = new JFrame("User And Admin Page for Hotel Management System");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        adminPLabel.setVisible(false);
        adminPasswordField.setVisible(false);

        adminClick.setVisible(false);
        customerClick.setVisible(false);
        frame.add(userAdminPanel);

        // MouseAdapter for the adminResetLabel
        adminResetLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminPasswordField.setText(null);
                adminErrorLabel.setText(null);
            }
        });

        // MouseAdapter for the userAdminPanel
        userAdminPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                customerClick.setVisible(false);
                adminPLabel.setVisible(false);
                adminPasswordField.setVisible(false);
                adminClick.setVisible(false);

                adminErrorLabel.setVisible(false);
            }
        });

        // MouseAdapter for the adminIconLabel
        adminIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                adminPasswordField.setText(null);
                adminPLabel.setVisible(true);
                adminPasswordField.setVisible(true);

                adminClick.setVisible(true);
                customerClick.setVisible(false);
            }
        });

        // MouseAdapter for the adminClick label
        adminClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (adminPasswordField.getPassword().length <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter Admin Password First...");
                } else {
                    char[] adminP = adminPasswordField.getPassword();
                    String adminStrP = new String(adminP);
                    String password = "shiven129";

                    try {
                        if (adminStrP.equals(password)) {
                            JOptionPane.showMessageDialog(null, "Admin login success!!!");
                            frame.dispose();
                            new crudEmployee();
                        } else {
                            adminErrorLabel.setText("Invalid Password for Admin...\nPlease Enter Valid Password and try again.");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });

        // MouseAdapter for the teamLabelIcon
        teamLabelIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                adminPLabel.setVisible(false);
                adminPasswordField.setVisible(false);
                customerClick.setVisible(true);
                adminClick.setVisible(false);
                adminErrorLabel.setVisible(false);
            }
        });

        // MouseAdapter for the customerClick label
        customerClick.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new roomType();
            }
        });

        // ActionListener for the xButton
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new startupPage();
            }
        });
    }

    // Main method to run the User and Admin page
    public static void main(String[] args) {
        new userAdmin();
    }
}

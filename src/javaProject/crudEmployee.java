package javaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Class representing the CRUD (Create, Read, Update, Delete) operations for employees
public class crudEmployee {

    // Components for the CRUD employee form
    private JPanel crudPanel;
    private JLabel insertBtn;
    private JLabel updateBtn;
    private JLabel searchBtn;
    private JLabel deleteBtn;
    private JButton crossBtn;
    private JLabel backBtn;

    // Reset label (assuming it's for some reset functionality)
    private JLabel resetLabel;

    // Constructor for the CRUD employee page
    public crudEmployee() {
        // Creating the main frame for the CRUD employee page
        JFrame frame = new JFrame("Crud Page for Hotel Management System");
        frame.pack();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Adding the CRUD employee panel to the frame
        frame.add(crudPanel);

        // MouseListener for the "Back" button to go back to the user admin page
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new userAdmin();
            }
        });

        // MouseListener for the "Close" button to go back to the user admin page
        crossBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new userAdmin();
            }
        });

        // MouseListener for the "Insert" button to navigate to the employee creation page
        insertBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                new employee();
            }
        });

        // MouseListener for the "Search" button to navigate to the employee search/update page
        searchBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new updateEmployee();
            }
        });

        // MouseListener for the "Update" button to navigate to the employee update page
        updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new updateEmployee();
            }
        });

        // MouseListener for the "Delete" button to navigate to the employee deletion page
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new DeleteEmployee();
            }
        });
    }

    // Main method to run the CRUD employee page
    public static void main(String[] args) {
        new crudEmployee();
    }
}

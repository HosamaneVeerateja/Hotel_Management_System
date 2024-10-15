

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JButton customerButton, employeeButton;

    public Main() {
        setTitle("Hotel Management System");
        setLayout(new GridLayout(2, 1));

        customerButton = new JButton("Customer Login");
        customerButton.setFont(new Font("Arial", Font.BOLD, 20));

        employeeButton = new JButton("Employee Login");
        employeeButton.setFont(new Font("Arial", Font.BOLD, 20));

        add(customerButton);
        add(employeeButton);

        // Action listener for Customer login
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CustomerLogin().setVisible(true);
                dispose(); // Close main window
            }
        });

        // Action listener for Employee login
        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EmployeeLogin().setVisible(true);
                dispose(); // Close main window
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}

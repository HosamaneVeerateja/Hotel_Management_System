

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeHome extends JFrame {
    public EmployeeHome() {
        setTitle("Employee Home");
        setLayout(new GridLayout(3, 1));

        JButton manageRoomsButton = new JButton("Manage Rooms");
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton logoutButton = new JButton("Logout");

        add(manageRoomsButton);
        add(manageEmployeesButton);
        add(logoutButton);

        manageRoomsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the room management functionality here
                new RoomManagement().setVisible(true);
                dispose();
            }
        });

        manageEmployeesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the employee management functionality here
                new EmployeeManagement().setVisible(true);
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logout action
                new Main().setVisible(true);
                dispose();
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new EmployeeHome().setVisible(true);
    }
}

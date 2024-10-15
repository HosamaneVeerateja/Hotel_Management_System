

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeManagement extends JFrame {
    private JTextField nameField, emailField, usernameField, passwordField, roleField;
    private JButton addEmployeeButton;

    public EmployeeManagement() {
        setTitle("Employee Management");
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Role:"));
        roleField = new JTextField();
        add(roleField);

        addEmployeeButton = new JButton("Add Employee");
        add(addEmployeeButton);

        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(((JPasswordField) passwordField).getPassword());
        String role = roleField.getText();

        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "INSERT INTO Employee (name, email, username, password, role) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, role);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Employee added successfully!");
            con.close();
            dispose(); // Close the management window after adding the employee
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding employee: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagement());
    }
}

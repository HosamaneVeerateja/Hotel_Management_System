

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerSignup extends JFrame {
    private JTextField nameField, usernameField, emailField;
    private JPasswordField passwordField;
    private JButton signupButton;

    public CustomerSignup() {
        setTitle("Customer Signup");
        setLayout(new GridLayout(5, 2));

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

        signupButton = new JButton("Signup");
        add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    Connection con = DatabaseConnection.getConnection();
                    String query = "INSERT INTO Customer (name, email, username, password) VALUES (?, ?, ?, ?)";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, name);
                    pst.setString(2, email);
                    pst.setString(3, username);
                    pst.setString(4, password);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Signup successful!");
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new CustomerSignup().setVisible(true);
    }
}

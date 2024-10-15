
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public CustomerLogin() {
        setTitle("Customer Login");
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginCustomer();
            }
        });

        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loginCustomer() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        try {
            Connection con = DatabaseConnection.getConnection();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
                return; // Exit the method if the connection is null
            }

            String query = "SELECT * FROM Customer WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                // Proceed to the customer dashboard or main application
                new CustomerHome(); // Example dashboard
                dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during login: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerLogin());
    }
}

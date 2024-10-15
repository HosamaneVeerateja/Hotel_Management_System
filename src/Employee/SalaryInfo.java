import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SalaryInfo extends JFrame {
    private JLabel salaryLabel;

    public SalaryInfo() {
        setTitle("Salary Information");

        salaryLabel = new JLabel("Your Salary: ");
        JButton fetchButton = new JButton("Fetch Salary");

        setLayout(new GridLayout(2, 1));
        add(salaryLabel);
        add(fetchButton);

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = DatabaseConnection.getConnection();
                    String query = "SELECT salary FROM Employee WHERE username=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    // Assuming username is passed as a parameter here
                    pst.setString(1, "employee_username"); // Placeholder, change as necessary

                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        salaryLabel.setText("Your Salary: $" + rs.getString("salary"));
                    }

                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SalaryInfo().setVisible(true);
    }
}

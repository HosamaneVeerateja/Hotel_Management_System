

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodOrdering extends JFrame {
    private JComboBox<String> foodComboBox;
    private JButton orderButton;

    public FoodOrdering() {
        setTitle("Food Ordering");
        setLayout(new FlowLayout());

        foodComboBox = new JComboBox<>();
        loadFoodMenu();
        add(foodComboBox);

        orderButton = new JButton("Order Food");
        add(orderButton);

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFood = (String) foodComboBox.getSelectedItem();
                orderFood(selectedFood);
            }
        });

        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loadFoodMenu() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT food_name FROM Food";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                foodComboBox.addItem(rs.getString("food_name"));
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void orderFood(String foodName) {
        // Here you can implement logic for saving the order to a database or displaying a success message
        JOptionPane.showMessageDialog(null, "Food " + foodName + " ordered successfully!");
        dispose();
    }

    public static void main(String[] args) {
        new FoodOrdering().setVisible(true);
    }
}

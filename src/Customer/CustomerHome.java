

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerHome extends JFrame {
    public CustomerHome() {
        setTitle("Customer Home");
        setLayout(new GridLayout(3, 1));

        JButton bookRoomButton = new JButton("Book Room");
        JButton orderFoodButton = new JButton("Order Food");
        JButton logoutButton = new JButton("Logout");

        add(bookRoomButton);
        add(orderFoodButton);
        add(logoutButton);

        bookRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the room booking functionality here
                new RoomBooking().setVisible(true);
                dispose();
            }
        });

        orderFoodButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the food ordering functionality here
                new FoodOrdering().setVisible(true);
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
        new CustomerHome().setVisible(true);
    }
}

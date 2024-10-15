

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomManagement extends JFrame {
    private JTextField roomNumberField, roomTypeField, priceField;
    private JButton addRoomButton;

    public RoomManagement() {
        setTitle("Room Management");
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        add(roomNumberField);

        add(new JLabel("Room Type:"));
        roomTypeField = new JTextField();
        add(roomTypeField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        addRoomButton = new JButton("Add Room");
        add(addRoomButton);

        addRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addRoom() {
        String roomNumber = roomNumberField.getText();
        String roomType = roomTypeField.getText();
        String price = priceField.getText();

        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "INSERT INTO Room (room_number, room_type, price) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, roomNumber);
            pst.setString(2, roomType);
            pst.setString(3, price);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room added successfully!");
            con.close();
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RoomManagement().setVisible(true);
    }
}

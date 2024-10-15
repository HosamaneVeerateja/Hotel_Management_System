

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomBooking extends JFrame {
    private JComboBox<String> roomComboBox;
    private JButton bookButton;

    public RoomBooking() {
        setTitle("Room Booking");
        setLayout(new FlowLayout());

        roomComboBox = new JComboBox<>();
        loadAvailableRooms();
        add(roomComboBox);

        bookButton = new JButton("Book Room");
        add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRoom = (String) roomComboBox.getSelectedItem();
                bookRoom(selectedRoom);
            }
        });

        setSize(300, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loadAvailableRooms() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "SELECT room_number FROM Room WHERE available = TRUE";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                roomComboBox.addItem(rs.getString("room_number"));
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void bookRoom(String roomNumber) {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "UPDATE Room SET available = FALSE WHERE room_number = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, roomNumber);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " booked successfully!");
            con.close();
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RoomBooking().setVisible(true);
    }
}

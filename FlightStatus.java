import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FlightStatus extends JPanel {
    private JTextField bookingIdField;
    private JLabel nameValue, emailValue, originValue, destinationValue, departValue, returnValue, priceValue, routeValue, statusMessage;

    public FlightStatus() {
        setLayout(null);
        JLabel title = new JLabel("Check Flight Status");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setBounds(300, 20, 400, 40);
        add(title);

        JPanel detailsPanel = new JPanel(null);
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));
        detailsPanel.setBounds(100, 80, 400, 300);
        add(detailsPanel);

        int y = 20;
        nameValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Passenger Name:", 30, y)); detailsPanel.add(nameValue); y+=35;
        emailValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Email:", 30, y)); detailsPanel.add(emailValue); y+=35;
        originValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Origin:", 30, y)); detailsPanel.add(originValue); y+=35;
        destinationValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Destination:", 30, y)); detailsPanel.add(destinationValue); y+=35;
        departValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Departure:", 30, y)); detailsPanel.add(departValue); y+=35;
        returnValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Return:", 30, y)); detailsPanel.add(returnValue); y+=35;
        priceValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Price:", 30, y)); detailsPanel.add(priceValue); y+=35;
        routeValue = createValueLabel("", 180, y);
        detailsPanel.add(createFieldLabel("Route:", 30, y)); detailsPanel.add(routeValue);

        JPanel inputPanel = new JPanel(null);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search Booking"));
        inputPanel.setBounds(530, 120, 270, 180);
        add(inputPanel);

        JLabel bookingLabel = new JLabel("Booking ID:");
        bookingLabel.setBounds(20, 40, 100, 25);
        inputPanel.add(bookingLabel);

        bookingIdField = new JTextField();
        bookingIdField.setBounds(20, 65, 220, 30);
        inputPanel.add(bookingIdField);

        JButton searchBtn = new JButton("Check Status");
        searchBtn.setBackground(new Color(78, 159, 229));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));
        searchBtn.setBounds(20, 110, 220, 35);
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inputPanel.add(searchBtn);

        statusMessage = new JLabel("", SwingConstants.CENTER);
        statusMessage.setBounds(250, 380, 400, 30);
        add(statusMessage);

        searchBtn.addActionListener(e -> fetchBookingDetails());
    }

    private JLabel createFieldLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBounds(x, y, 130, 25);
        return label;
    }

    private JLabel createValueLabel(String text, int x, int y) {
        JLabel value = new JLabel(text);
        value.setFont(new Font("Arial", Font.PLAIN, 14));
        value.setBounds(x, y, 200, 25);
        return value;
    }

    private void fetchBookingDetails() {
        String bookingIdText = bookingIdField.getText().trim();
        if (bookingIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a booking ID.");
            return; 
        }

        clearLabels();
        statusMessage.setText("Searching...");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "");
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT b.booking_id, b.origin, b.destination, b.depart_date, b.return_date, " +
                             "b.depart_time, b.return_time, b.price, b.route, p.name, p.email " +
                             "FROM bookings b INNER JOIN passenger p ON b.passenger_id = p.passenger_id " +
                             "WHERE b.booking_id = ?")) {

            ps.setString(1, bookingIdText);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameValue.setText(rs.getString("name"));
                emailValue.setText(rs.getString("email"));
                originValue.setText(rs.getString("origin"));
                destinationValue.setText(rs.getString("destination"));
                departValue.setText(rs.getDate("depart_date") + " " + rs.getString("depart_time"));
                returnValue.setText(rs.getDate("return_date") + " " + rs.getString("return_time"));
                priceValue.setText("₱" + String.format("%.2f", rs.getDouble("price")));
                routeValue.setText(rs.getString("route"));
                statusMessage.setText("Booking found!");
                statusMessage.setForeground(new Color(0, 128, 0));
            } else {
                statusMessage.setText("No booking found for ID: " + bookingIdText);
                statusMessage.setForeground(Color.RED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    private void clearLabels() {
        nameValue.setText("");
        emailValue.setText("");
        originValue.setText("");
        destinationValue.setText("");
        departValue.setText("");
        returnValue.setText("");
        priceValue.setText("");
        routeValue.setText("");
        statusMessage.setForeground(Color.GRAY);
    }

}

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class BookFlight extends JFrame {

    private static final String[] ALL_TIMES = { "06:00 AM", "09:30 AM", "01:00 PM", "04:15 PM", "07:45 PM" };
    private JList<String> departTimesList, returnTimesList;
    private JTextField nameField, ageField, emailField, contactField;
    private JLabel idValueLabel;
    private JButton confirmButton, fetchButton;
    private double price;

    public BookFlight(String from, String to, String route, Date depart, Date ret, double price) {
        this.price = price;

        setTitle("Flight Selection");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // === TOP PANEL ===
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        topPanel.add(new JLabel("Origin: " + from));
        topPanel.add(new JLabel("Destination: " + to));
        topPanel.add(new JLabel("Departure: " + sdf.format(depart)));
        topPanel.add(new JLabel("Return: " + sdf.format(ret)));

        JLabel priceLabel = new JLabel(String.format("Price: ₱%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(priceLabel);

        JLabel routeLabel = new JLabel(String.format("Route: %s", route));
        routeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        routeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(routeLabel);

        add(topPanel, BorderLayout.NORTH);

        // === CENTER PANEL ===
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // LEFT SIDE: Time selectors
        JPanel timePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        timePanel.setBorder(BorderFactory.createTitledBorder("Select Flight Times"));

        // === Departure Panel ===
        JPanel departPanel = new JPanel(new BorderLayout(5, 5));
        JLabel departTitle = new JLabel("Available Departure Times", SwingConstants.CENTER);
        departTitle.setFont(new Font("Arial", Font.BOLD, 16));

        departTimesList = new JList<>(ALL_TIMES);
        departTimesList.setFont(new Font("Arial", Font.PLAIN, 14));
        departTimesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        departPanel.add(departTitle, BorderLayout.NORTH);
        departPanel.add(new JScrollPane(departTimesList), BorderLayout.CENTER);
        

        // === Return Panel ===
        JPanel returnPanel = new JPanel(new BorderLayout(5, 5));
        JLabel returnTitle = new JLabel("Available Return Times", SwingConstants.CENTER);
        returnTitle.setFont(new Font("Arial", Font.BOLD, 16));

        returnTimesList = new JList<>(ALL_TIMES);
        returnTimesList.setFont(new Font("Arial", Font.PLAIN, 14));
        returnTimesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        returnPanel.add(returnTitle, BorderLayout.NORTH);
        returnPanel.add(new JScrollPane(returnTimesList), BorderLayout.CENTER);

        timePanel.add(departPanel);
        timePanel.add(returnPanel);

        // === Passenger Info Panel ===
        JPanel userPanel = new JPanel();
        userPanel.setLayout(null);
        userPanel.setBorder(BorderFactory.createTitledBorder("Passenger Details"));

        JLabel idLabel = new JLabel("Passenger ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel contactLabel = new JLabel("Contact:");

        idValueLabel = new JLabel();
        idValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        idValueLabel.setForeground(new Color(0, 100, 0));
        idValueLabel.setBounds(130, 30, 120, 25);
        userPanel.add(idValueLabel);

        fetchButton = new JButton("Fetch Info");
        fetchButton.setBounds(260, 30, 100, 25);

        JButton editButton = new JButton("New Passenger");
        editButton.setBounds(20, 250, 140, 30);
        editButton.setVisible(false);

        nameField = new JTextField();
        ageField = new JTextField();
        emailField = new JTextField();
        contactField = new JTextField();
        confirmButton = new JButton("Confirm Booking");

        idLabel.setBounds(20, 30, 100, 25);
        nameLabel.setBounds(20, 70, 100, 25);
        ageLabel.setBounds(20, 110, 100, 25);
        emailLabel.setBounds(20, 150, 100, 25);
        contactLabel.setBounds(20, 190, 100, 25);

        nameField.setBounds(130, 70, 180, 25);
        ageField.setBounds(130, 110, 180, 25);
        emailField.setBounds(130, 150, 180, 25);
        contactField.setBounds(130, 190, 180, 25);
        confirmButton.setBounds(210, 250, 140, 30);

        userPanel.add(idLabel);
        userPanel.add(fetchButton);
        userPanel.add(editButton);
        userPanel.add(nameLabel);
        userPanel.add(ageLabel);
        userPanel.add(emailLabel);
        userPanel.add(contactLabel);
        userPanel.add(nameField);
        userPanel.add(ageField);
        userPanel.add(emailField);
        userPanel.add(contactField);
        userPanel.add(confirmButton);

        userPanel.setPreferredSize(new Dimension(430, 300));

        // === Generate Passenger ID ===
        String nextPassengerID = getNextPassengerID();
        idValueLabel.setText(nextPassengerID);

        // === Button Listeners ===
        fetchButton.addActionListener(e -> {
            String inputID = JOptionPane.showInputDialog(null, "Enter Passenger ID:", "Fetch Passenger", JOptionPane.PLAIN_MESSAGE);
            if (inputID != null && !inputID.trim().isEmpty()) {
                fetchPassengerInfo(inputID.trim());
                editButton.setVisible(true);
            }
        });

        confirmButton.addActionListener(e -> confirmAndSaveBooking(from, to, route, depart, ret));

        editButton.addActionListener(e -> {
            String newID = getNextPassengerID();
            idValueLabel.setText(newID);
            nameField.setText("");
            ageField.setText("");
            emailField.setText("");
            contactField.setText("");
            nameField.setEditable(true);
            ageField.setEditable(true);
            emailField.setEditable(true);
            contactField.setEditable(true);
            editButton.setVisible(false);
        });

        departTimesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedDepartTime = departTimesList.getSelectedValue();
                if (selectedDepartTime != null) {
                    try {
                        // Check if depart and return dates are the same (ignoring time)
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                        if (sdfDate.format(depart).equals(sdfDate.format(ret))) {
                            Date departTime = parseTime(selectedDepartTime);
                            // Filter return times at least 6 hours after departTime
                            filterReturnTimes(departTime);
                        } else {
                            // Dates differ, show all return times
                            resetReturnTimes();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        

        centerPanel.add(timePanel);
        centerPanel.add(userPanel);
        add(centerPanel, BorderLayout.CENTER);

        setResizable(false);
        setVisible(true);
    }

    private Date parseTime(String time) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.parse(time);
    }

    private void filterReturnTimes(Date departTime) throws Exception {
        String[] allReturnTimes = ALL_TIMES;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        java.util.List<String> filteredTimes = new java.util.ArrayList<>();
    
        long hours = 6 * 60 * 60 * 1000;
    
        for (String timeStr : allReturnTimes) {
            Date returnTime = sdf.parse(timeStr);
            // Check if returnTime is at least 6 hours after departTime
            if (returnTime.getTime() - departTime.getTime() >= hours) {
                filteredTimes.add(timeStr);
            }
        }
    
        if (filteredTimes.isEmpty()) {
            // No available return times meet the condition, notify user or disable returnTimesList
            returnTimesList.setListData(new String[] { "No available return times" });
            returnTimesList.setEnabled(false);
        } else {
            returnTimesList.setListData(filteredTimes.toArray(new String[0]));
            returnTimesList.setEnabled(true);
        }
    }

    private void resetReturnTimes() {
        String[] allReturnTimes = ALL_TIMES;
        returnTimesList.setListData(allReturnTimes);
        returnTimesList.setEnabled(true);
    }
    
    // === Fetch Passenger Info ===
    private void fetchPassengerInfo(String passengerId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM passenger WHERE passenger_id = ?")) {

            stmt.setString(1, passengerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                idValueLabel.setText(rs.getString("passenger_id"));
                nameField.setText(rs.getString("name"));
                ageField.setText(String.valueOf(rs.getInt("age")));
                emailField.setText(rs.getString("email"));
                contactField.setText(rs.getString("contact"));

                nameField.setEditable(false);
                ageField.setEditable(false);
                emailField.setEditable(false);
                contactField.setEditable(false);

                JOptionPane.showMessageDialog(this, "Passenger found and loaded!");
            } else {
                JOptionPane.showMessageDialog(this, "Passenger ID not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching passenger info: " + ex.getMessage());
        }
    }

    // === Confirmation Dialog + Save ===
    private void confirmAndSaveBooking(String from, String to, String route, Date depart, Date ret) {
        String pid = idValueLabel.getText();
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();

        String departTime = departTimesList.getSelectedValue();
        String returnTime = returnTimesList.getSelectedValue();

        if (name.isEmpty() || ageText.isEmpty() || email.isEmpty() || contact.isEmpty() ||
            departTime == null || returnTime == null) {
            JOptionPane.showMessageDialog(this, "Please complete all fields and time selections.");
            return;
        }

        // === Confirmation popup ===
        String bookingID = getNextBookingID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String message =
            "Booking ID: " + bookingID +
            "\nPassenger: " + name +
            "\nEmail: " + email +
            "\nFrom: " + from + "  →  To: " + to +
            "\nDepart: " + sdf.format(depart) + "  (" + departTime + ")" +
            "\nReturn: " + sdf.format(ret) + "  (" + returnTime + ")" +
            "\nRoute: " + route +
            "\nPrice: ₱" + price +
            "\n\nConfirm this booking?";

        int confirm = JOptionPane.showConfirmDialog(this, message, "Confirm Booking", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            saveBooking(pid, bookingID, from, to, depart, ret, departTime, returnTime, route);
        }
    }

    // === Save Booking to DB ===
    private void saveBooking(String pid, String bookingID, String from, String to, Date depart, Date ret,
                             String departTime, String returnTime, String route) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "")) {

            // Ensure passenger exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM passenger WHERE passenger_id = ?");
            checkStmt.setString(1, pid);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                PreparedStatement insertStmt = conn.prepareStatement(
                    "INSERT INTO passenger (passenger_id, name, age, email, contact) VALUES (?, ?, ?, ?, ?)");
                insertStmt.setString(1, pid);
                insertStmt.setString(2, nameField.getText().trim());
                insertStmt.setInt(3, Integer.parseInt(ageField.getText().trim()));
                insertStmt.setString(4, emailField.getText().trim());
                insertStmt.setString(5, contactField.getText().trim());
                insertStmt.executeUpdate();
            }

            // Insert booking
            PreparedStatement bookStmt = conn.prepareStatement(
                "INSERT INTO bookings (booking_id, passenger_id, origin, destination, depart_date, return_date, depart_time, return_time, price, route) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            bookStmt.setString(1, bookingID);
            bookStmt.setString(2, pid);
            bookStmt.setString(3, from);
            bookStmt.setString(4, to);
            bookStmt.setDate(5, new java.sql.Date(depart.getTime()));
            bookStmt.setDate(6, new java.sql.Date(ret.getTime()));
            bookStmt.setString(7, departTime);
            bookStmt.setString(8, returnTime);
            bookStmt.setDouble(9, price);
            bookStmt.setString(10, route);
            bookStmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Booking Confirmed!\nYour Booking ID is: " + bookingID);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving booking: " + ex.getMessage());
        }
    }

    // === Generate Passenger ID ===
    private String getNextPassengerID() {
        String nextID = "P0001";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT passenger_id FROM passenger ORDER BY passenger_id DESC LIMIT 1")) {

            if (rs.next()) {
                String lastID = rs.getString("passenger_id");
                int num = Integer.parseInt(lastID.substring(1)) + 1;
                nextID = String.format("P%04d", num);
            }
        } catch (Exception e) {
            System.out.println("Error generating ID: " + e.getMessage());
        }
        return nextID;
    }

    // === Generate Booking ID ===
    private String getNextBookingID() {
        String nextID = "B0001";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT booking_id FROM bookings ORDER BY booking_id DESC LIMIT 1")) {

            if (rs.next()) {
                String lastID = rs.getString("booking_id");
                int num = Integer.parseInt(lastID.substring(1)) + 1;
                nextID = String.format("B%04d", num);
            }
        } catch (Exception e) {
            System.out.println("Error generating booking ID: " + e.getMessage());
        }
        return nextID;
    }
}


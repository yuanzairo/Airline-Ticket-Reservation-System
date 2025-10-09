import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Login() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setResizable(false);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 44, 52)); // dark background

        // Title label
        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        inputPanel.setBackground(new Color(40, 44, 52));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.LIGHT_GRAY);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(60, 63, 65));
        usernameField.setForeground(Color.WHITE);
        usernameField.setCaretColor(Color.WHITE);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usernameField.setPreferredSize(new Dimension(200, 35)); // bigger box
        inputPanel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.LIGHT_GRAY);
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(60, 63, 65));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(200, 35)); // bigger box
        inputPanel.add(passwordField);

        panel.add(inputPanel, BorderLayout.CENTER);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(100, 40));
        loginButton.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(40, 44, 52));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));
        buttonPanel.add(loginButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Action listener for login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(null,
                            "Login successful! Welcome, " + username,
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new DashBoard();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid username or password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/airline"; // replace with your DB name
        String dbUser = "root"; // replace with your MySQL username
        String dbPassword = ""; // replace with your MySQL password

        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try {
            // Load the JDBC driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            boolean found = rs.next();

            rs.close();
            stmt.close();
            conn.close();

            return found;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}

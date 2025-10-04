package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LogIn extends JFrame {
    private JPanel signInPanel; // overlay sign-in form
    private JButton closeButton;
    private JLayeredPane layeredPane;

    public LogIn() {
        setTitle("Davao Airlines");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // background overlay
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 500));
        setContentPane(layeredPane);

        // Background with dark overlay para kita ang text
        JPanel bgPic = new JPanel() {
            Image bg = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\bg.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        bgPic.setLayout(new BorderLayout());
        bgPic.setBounds(0, 0, 900, 500);

        // Navigation bar
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
        navBar.setOpaque(false);
        JLabel bookFlight = new JLabel("Book a Flight");
        JLabel travelInfo = new JLabel("Travel Info");
        JLabel flightStatus = new JLabel("Flight Status");
        for (JLabel lbl : new JLabel[]{bookFlight, travelInfo, flightStatus}) {
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            navBar.add(lbl);
        }
        bgPic.add(navBar, BorderLayout.NORTH);

        // Content section
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setOpaque(false);

        // Logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setOpaque(false);
        ImageIcon logo = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\logo.png");
        Image logoImg = logo.getImage().getScaledInstance(450, 400, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoPanel.add(logoLabel);

        // Right content
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setOpaque(false);

        JLabel title = new JLabel("Take off to Baguio!");
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);

        //kamo na bahala sa paragraph, change it as you see fit
        JLabel paragraph = new JLabel("Fly nonstop from Davao to Baguio starting October 3!");
        paragraph.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph.setForeground(Color.WHITE);

        JButton logIn = new JButton("LOG IN / SIGN UP");
        logIn.setBackground(new Color(78, 159, 229));
        logIn.setForeground(Color.WHITE);
        logIn.setFocusPainted(false);
        logIn.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Action: Show sign-in
        logIn.addActionListener(e -> signInPanel.setVisible(true));

        right.add(Box.createVerticalGlue());
        right.add(title);
        right.add(Box.createRigidArea(new Dimension(0, 10)));
        right.add(paragraph);
        right.add(Box.createRigidArea(new Dimension(0, 20)));
        right.add(logIn);
        right.add(Box.createVerticalGlue());

        contentPanel.add(logoPanel);
        contentPanel.add(right);
        bgPic.add(contentPanel, BorderLayout.CENTER);

        layeredPane.add(bgPic, JLayeredPane.DEFAULT_LAYER);

        createSignInPanel();
        layeredPane.add(signInPanel, JLayeredPane.PALETTE_LAYER);

        setVisible(true);
    }

    private void createSignInPanel() {
        signInPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(240, 240, 240, 230)); // light bg with opacity
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        signInPanel.setLayout(new BorderLayout());
        signInPanel.setOpaque(false);

        // chatgpt ning close button kay wa koy idea unsaon
        closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(false);
        closeButton.setForeground(Color.BLACK);
        closeButton.setBorder(new LineBorder(Color.BLACK, 1, true));
        closeButton.setPreferredSize(new Dimension(40, 25));
        closeButton.addActionListener(e -> signInPanel.setVisible(false));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        headerPanel.setOpaque(false);
        headerPanel.add(closeButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(10, 40, 20, 40));
        centerPanel.setOpaque(false);

        JLabel header = new JLabel("Sign-in/Register");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(header);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JTextField emailField = new JTextField("Enter Email Address");
        emailField.setMaximumSize(new Dimension(300, 30));
        centerPanel.add(emailField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPasswordField passwordField = new JPasswordField("Password");
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);  
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton continueBtn = new JButton("Continue");
        continueBtn.setBackground(new Color(180, 180, 180));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setMaximumSize(new Dimension(300, 35));
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(continueBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel or = new JLabel("Or use another method");
        or.setFont(new Font("Arial", Font.PLAIN, 12));
        or.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(or);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton fbBtn = new JButton("Facebook");
        fbBtn.setBackground(new Color(59, 89, 152));
        fbBtn.setForeground(Color.WHITE);
        fbBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        fbBtn.setMaximumSize(new Dimension(300, 35));

        JButton googleBtn = new JButton("Google");
        googleBtn.setBackground(Color.WHITE);
        googleBtn.setForeground(Color.BLACK);
        googleBtn.setBorder(new LineBorder(Color.BLACK, 1, true));
        googleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        googleBtn.setMaximumSize(new Dimension(300, 35));

        JButton appleBtn = new JButton("Apple");
        appleBtn.setBackground(Color.BLACK);
        appleBtn.setForeground(Color.WHITE);
        appleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        appleBtn.setMaximumSize(new Dimension(300, 35));

        centerPanel.add(fbBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(googleBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(appleBtn);

        JLabel footer = new JLabel("Don't have an Account? Sign-up");
        footer.setFont(new Font("Arial", Font.PLAIN, 12));
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(footer);

        
        signInPanel.add(headerPanel, BorderLayout.NORTH);
        signInPanel.add(centerPanel, BorderLayout.CENTER);

        signInPanel.setBounds(200, 30, 500, 400);
        signInPanel.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LogIn::new);
    }
}

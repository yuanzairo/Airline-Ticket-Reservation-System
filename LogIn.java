package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LogIn extends JFrame {
    private JPanel signInPanel; // overlay sign-in form
    private JPanel signInForm;
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
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        navBar.setOpaque(false);

        JButton bookFlight = createNavButton("Book a Flight");
        JButton travelInfo = createNavButton("Travel Info");
        JButton flightStatus = createNavButton("Flight Status");

        // Action listeners for the buttons
        bookFlight.addActionListener(e -> signInPanel.setVisible(true));
        travelInfo.addActionListener(e -> signInPanel.setVisible(true));
        flightStatus.addActionListener(e -> signInPanel.setVisible(true));

        navBar.add(bookFlight);
        navBar.add(travelInfo);
        navBar.add(flightStatus);

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
        createSignInForm(); 
        layeredPane.add(signInPanel, JLayeredPane.PALETTE_LAYER);

        setVisible(true);
    }
    
    private JButton createNavButton(String text) {
    	// Remove the bg and border of the buttons
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);

        btn.setContentAreaFilled(false);  // no background
        btn.setBorderPainted(false);      // no border
        btn.setFocusPainted(false);       // no focus outline
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // hand cursor

        // Optional: hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(78, 159, 229)); // change color on hover
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE); // reset color
            }
        });

        return btn;
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
        emailField.setMargin(new Insets(5, 10, 5, 10));
        centerPanel.add(emailField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JPasswordField passwordField = new JPasswordField("");
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setMargin(new Insets(5, 10, 5, 10));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(passwordField);  
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton continueBtn = new JButton("Continue");
        continueBtn.setBackground(new Color(78, 159, 229));
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
        
        ImageIcon fbIcon = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\communication.png");
        Image fbImg = fbIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        fbIcon = new ImageIcon(fbImg);

        JButton fbBtn = new JButton("Facebook", fbIcon);
        fbBtn.setBackground(Color.WHITE);
        fbBtn.setForeground(Color.BLACK);
        fbBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        fbBtn.setMaximumSize(new Dimension(300, 35));
        fbBtn.setFocusPainted(false);
        fbBtn.setIconTextGap(10);
        
        ImageIcon gIcon = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\google.png");
        Image gImg = gIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        gIcon = new ImageIcon(gImg);

        JButton googleBtn = new JButton("Google", gIcon);
        googleBtn.setBackground(Color.WHITE);
        googleBtn.setForeground(Color.BLACK);
        googleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        googleBtn.setMaximumSize(new Dimension(300, 35));
        googleBtn.setFocusPainted(false);
        googleBtn.setIconTextGap(10);
        
        ImageIcon appleIcon = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\apple.png");
        Image appleImg = appleIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        appleIcon = new ImageIcon(appleImg);

        JButton appleBtn = new JButton("IOS", appleIcon);
        appleBtn.setBackground(Color.WHITE);
        appleBtn.setForeground(Color.BLACK);
        appleBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        appleBtn.setMaximumSize(new Dimension(300, 35));
        appleBtn.setFocusPainted(false);
        appleBtn.setIconTextGap(10);

        centerPanel.add(fbBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(googleBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(appleBtn);

        JLabel footerText = new JLabel("Don't have an Account? ");
        footerText.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton signupBtn = new JButton("Sign-up");
        signupBtn.setBorderPainted(false);
        signupBtn.setContentAreaFilled(false);
        signupBtn.setFocusPainted(false);
        signupBtn.setOpaque(false);
        signupBtn.setForeground(Color.BLUE);
        signupBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        signupBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Action: show the signup panel
        signupBtn.addActionListener(e -> {
        	signInPanel.setVisible(false);
        	signInForm.setVisible(true);
        });

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        footerPanel.setOpaque(false);
        footerPanel.add(footerText);
        footerPanel.add(signupBtn);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(footerPanel);

        signInPanel.add(headerPanel, BorderLayout.NORTH);
        signInPanel.add(centerPanel, BorderLayout.CENTER);

        signInPanel.setBounds(200, 30, 400, 400);
        signInPanel.setVisible(false);
    }
    
    private void createSignInForm() {
        signInForm = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(240, 240, 240, 230)); // light bg with opacity
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        signInForm.setLayout(new BorderLayout());
        signInForm.setOpaque(false);

        closeButton = new JButton("X");
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(false);
        closeButton.setForeground(Color.BLACK);
        closeButton.setBorder(new LineBorder(Color.BLACK, 1, true));
        closeButton.setPreferredSize(new Dimension(40, 25));
        closeButton.addActionListener(e -> signInForm.setVisible(false));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        headerPanel.setOpaque(false);
        headerPanel.add(closeButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 8, 5); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        JLabel header = new JLabel("Create Your Account", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(header, gbc);

        gbc.gridwidth = 1; 

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST; 
        JLabel email = new JLabel("Enter your email address");
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        centerPanel.add(email, gbc);

        gbc.gridy++;
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(250, 30));
        centerPanel.add(emailField, gbc);

        gbc.gridy++;
        JLabel pass = new JLabel("Enter your password");
        pass.setFont(new Font("Arial", Font.PLAIN, 12));
        centerPanel.add(pass, gbc);

        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        centerPanel.add(passwordField, gbc);

        gbc.gridy++;
        JLabel fn = new JLabel("Enter your full name");
        fn.setFont(new Font("Arial", Font.PLAIN, 12));
        centerPanel.add(fn, gbc);

        gbc.gridy++;
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 30));
        centerPanel.add(nameField, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton continueBtn = new JButton("Continue");
        continueBtn.setBackground(new Color(78, 159, 229));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setPreferredSize(new Dimension(250, 35));
        centerPanel.add(continueBtn, gbc);

        signInForm.add(headerPanel, BorderLayout.NORTH);
        signInForm.add(centerPanel, BorderLayout.CENTER);

        signInForm.setBounds(200, 30, 400, 400);
        signInForm.setVisible(false);

        layeredPane.add(signInForm, JLayeredPane.PALETTE_LAYER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LogIn::new);
    }
}

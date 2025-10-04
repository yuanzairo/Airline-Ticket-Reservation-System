package project;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;

public class BookFlight extends JFrame {
    private JLayeredPane layeredPane;

    public BookFlight() {
    	JFrame bookFlightFrame = new JFrame("Airline Booking");
        setSize(900, 445);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 445));
        setContentPane(layeredPane);

        JPanel bgPic = new JPanel() {
            Image bg = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\bg3.jpg").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.10f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        bgPic.setLayout(new BorderLayout());
        bgPic.setBounds(0, 0, 900, 445);

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        navBar.setOpaque(false);

        JButton bookFlight = reusableButton("Book a flight");
        JButton travelInfo = reusableButton("Travel Info");
        JButton flightStatus = reusableButton("Flight status");

        navBar.add(bookFlight);
        navBar.add(travelInfo);
        navBar.add(flightStatus);
        bgPic.add(navBar, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 255, 255, 150));
        mainPanel.setBounds(50, 60, 800, 330);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\logo.png"); // change path
        Image logoImg = logoIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(0, 0, 150, 100);
        mainPanel.add(logoLabel);

        JLabel title = new JLabel("BOOK NOW AND GET THE BEST FLIGHT OFFERS!");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        title.setBounds(370, 20, 600, 30);
        mainPanel.add(title);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 30, 20));
        gridPanel.setOpaque(false);
        gridPanel.setBounds(100, 80, 680, 250);

        String[] times = { "08:00 AM", "10:30 AM", "01:15 PM", "04:45 PM" };

        for (int i = 0; i < 4; i++) {
            JPanel card = new JPanel(null);
            card.setBackground(new Color(78, 159, 229));
            card.setPreferredSize(new Dimension(300, 120));

            JLabel route = new JLabel("Davao  ➜  Manila");
            route.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
            route.setBounds(30, 20, 200, 20);
            card.add(route);

            JLabel date = new JLabel("04 Oct 2025");
            date.setFont(new Font("Arial", Font.PLAIN, 14));
            date.setBounds(30, 45, 200, 20);
            card.add(date);

            // para lahi-lahi ang time
            JLabel time = new JLabel(times[i]);
            time.setFont(new Font("Arial", Font.PLAIN, 14));
            time.setBounds(30, 70, 100, 20);
            card.add(time);

            JButton selectBtn = new JButton("SELECT");
            selectBtn.setFont(new Font("Arial", Font.BOLD, 12));
            selectBtn.setBackground(Color.BLACK);
            selectBtn.setForeground(Color.WHITE);
            selectBtn.setFocusPainted(false);
            selectBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            selectBtn.setBounds(200, 70, 100, 30);
            selectBtn.setBorderPainted(false);

            card.add(selectBtn);

            gridPanel.add(card);
        }
        mainPanel.add(gridPanel);
        bgPic.add(mainPanel, BorderLayout.CENTER);

        layeredPane.add(bgPic, JLayeredPane.DEFAULT_LAYER);
        setVisible(true);
    }

    private JButton reusableButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	button.setForeground(new Color(78, 159, 229));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	button.setForeground(Color.WHITE);
            }
        });
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookFlight::new);
    }
}

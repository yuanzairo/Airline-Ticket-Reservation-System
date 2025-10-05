package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    private PriceCard selectedCard = null;

    public Main() {
    	setTitle("Davao Airlines");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon bgImage = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\bg3.jpg");
        Image bgScaled = bgImage.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgScaled));
        bgLabel.setBounds(0, 0, 900, 500);
        setContentPane(bgLabel);
        bgLabel.setLayout(null);

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Pictures\\projectCCE\\logo.png");
        Image logoScaled = logoIcon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoScaled));
        logoLabel.setBounds(30, 10, 150, 100);
        bgLabel.add(logoLabel);

        //panel
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(255, 255, 255, 220));
        mainPanel.setBounds(20, 80, 840, 360);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bgLabel.add(mainPanel);

        JButton backBtn = new JButton("← Back");
        backBtn.setBounds(10, 10, 80, 30);
        backBtn.setBackground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setForeground(Color.BLACK);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setForeground(new Color(0, 120, 215));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setForeground(Color.BLACK);
            }
        });
        mainPanel.add(backBtn);

        JLabel title = new JLabel("Davao  ➜  Manila", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
        title.setBounds(250, 10, 320, 30);
        mainPanel.add(title);

        JButton continueBtn = new JButton("CONTINUE");
        continueBtn.setBackground(new Color(0, 180, 255));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setBounds(720, 10, 100, 30);
        continueBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        continueBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	continueBtn.setForeground(new Color(0, 120, 215));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	continueBtn.setForeground(Color.WHITE);
            }
        });
        mainPanel.add(continueBtn);

        JPanel gridPanel = new JPanel(new GridLayout(5, 6, 5, 5));
        gridPanel.setBounds(30, 50, 780, 290);
        mainPanel.add(gridPanel);

        // Headers row
        gridPanel.add(new JLabel(""));
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        int startDate = 6;

        for (String day : days) {
            String dateText = "Oct " + startDate + " 2025";
            JLabel lbl = new JLabel(
                "<html><center>Departure<br>" + dateText + "<br>" + day + "</center></html>",
                SwingConstants.CENTER
            );
            lbl.setOpaque(true);
            lbl.setBackground(new Color(0, 180, 255));
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            lbl.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            gridPanel.add(lbl);

            startDate++; // increment the date
        }

        String[] arrivals = {"Mon", "Tue", "Wed", "Thu"};
        String[][] fares = {
            {"Not available", "From USD 752.46", "From USD 844.36", "Not available", "Not available"},
            {"Not available", "From USD 788.98", "From USD 956.36", "Not available", "Not available"},
            {"Not available", "From USD 656.33", "From USD 1005.36", "Not available", "Not available"},
            {"Not available", "From USD 745.87", "From USD 846.36", "Not available", "Not available"}
        };
        for (int i = 0; i < arrivals.length; i++) {
            // Dynamic date text for each arrival label
            String dateText = "Sept " + startDate + " 2025";
            JLabel arrivalLbl = new JLabel(
                "<html><center>Arrival<br>" + dateText + "<br>" + arrivals[i] + "</center></html>",
                SwingConstants.CENTER
            );

            arrivalLbl.setOpaque(true);
            arrivalLbl.setBackground(new Color(0, 180, 255));
            arrivalLbl.setForeground(Color.WHITE);
            arrivalLbl.setFont(new Font("Arial", Font.BOLD, 12));
            gridPanel.add(arrivalLbl);

            startDate++;
            
            for (int j = 0; j < fares[i].length; j++) {
                boolean available = !fares[i][j].equals("Not available");
                PriceCard card = new PriceCard(fares[i][j], available);

                card.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (available) {
                            if (selectedCard != null && selectedCard != card) {
                                selectedCard.deselect();
                            }
                            card.toggleSelection();
                            selectedCard = card.isSelected() ? card : null;
                        }
                    }
                });
                gridPanel.add(card);
            }
        }
        setVisible(true);
    }

    class PriceCard extends JPanel {
        private boolean available;
        private boolean selected = false;
        private JLabel label;
        private Color normalColor = Color.WHITE;
        private Color selectedColor = new Color(78, 159, 229);
        private Color disabledColor = new Color(230, 230, 230);

        public PriceCard(String text, boolean available) {
            this.available = available;
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            setBackground(available ? normalColor : disabledColor);
            setCursor(available ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor());
            label = new JLabel(formatLabel(text));
            add(label);
        }

        private String formatLabel(String text) {
            if (available) {
                return "<html><center>" + text + "<br><span style='color:green;'>Lowest Fare</span></center></html>";
            } else {
                return "<html><center><span style='color:gray;'>" + text + "</span></center></html>";
            }
        }

        public void toggleSelection() {
            if (!available) return;
            selected = !selected;
            setBackground(selected ? selectedColor : normalColor);
            repaint();
        }

        public void deselect() {
            selected = false;
            setBackground(normalColor);
            repaint();
        }

        public boolean isSelected() {
            return selected;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class FlightFareUpdated {


    public void createAndShowGUI() {
        JFrame frame = new JFrame("Flight Schedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);


        JLabel logoLabel = new JLabel("");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 22));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));

        mainPanel.add(logoLabel, BorderLayout.NORTH);


        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

     
        JPanel topNav = new JPanel(new BorderLayout());
        topNav.setOpaque(false);

        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 16));

        JButton continueBtn = new JButton("CONTINUE");
        continueBtn.setBackground(Color.DARK_GRAY);
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFont(new Font("Arial", Font.BOLD, 14));

        topNav.add(backBtn, BorderLayout.WEST);
        topNav.add(continueBtn, BorderLayout.EAST);

        JLabel routeLabel = new JLabel("Current  ⟷  Destination", SwingConstants.LEFT);
        routeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        routeLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

      
        String[] columns = {"Flight No.", "Departure Time", "Arrival Time", "Price"};
        Object[][] data = {
                {"", "7:00 am", "8:00 am", ""},
                {"", "9:00 am", "11:00 am", ""},
                {"", "11:00 am", "1:00 pm", ""},
                {"", "3:00 pm", "4:00 pm", ""},
                {"", "6:00 pm", "7:30 pm", ""},
                {"", "10:00 pm", "12:00 am", ""}
        };

        JTable table = new JTable(new DefaultTableModel(data, columns)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(150, 150, 150));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(routeLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(topNav, BorderLayout.NORTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FlightFareUpdated().createAndShowGUI());
    }
}

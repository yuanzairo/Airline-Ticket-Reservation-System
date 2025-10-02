import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class FlightFareGUI extends JPanel {
    private JButton selectedButton = null;
    private String selectedFare = null;

    public FlightFareGUI(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("← Back");
        JLabel title = new JLabel("Current <--> Destination", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(new Color(120, 120, 120));
        continueButton.setForeground(Color.WHITE);

        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(continueButton, BorderLayout.EAST);


        JPanel gridPanel = new JPanel(new GridLayout(5, 6, 2, 2));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        gridPanel.add(new JLabel(""));
        String[] headers = {
                "Departure Sept MON",
                "Departure Sept TUE",
                "Departure Sept WED",
                "Departure Sept THURS",
                "Departure Sept FRI"
        };
        for (String h : headers) {
            JLabel lbl = new JLabel("<html><center>" + h + "</center></html>", SwingConstants.CENTER);
            lbl.setOpaque(true);
            lbl.setBackground(new Color(180, 180, 180));
            lbl.setFont(new Font("Arial", Font.BOLD, 12));
            gridPanel.add(lbl);
        }


        String[] arrivals = {"Arrival Sept MON", "Arrival Sept TUE", "Arrival Sept WED", "Arrival Sept THURS"};
        String[][] fares = {
                {"Not available", "USD 752.46<br> (Lowest Fare)", "USD 844.36", "Not available", "Not available"},
                {"Not available", "USD 788.98<br> (Lowest Fare)", "USD 956.36", "Not available", "Not available"},
                {"Not available", "USD 656.33<br> (Lowest Fare)", "USD 1005.36", "Not available", "Not available"},
                {"Not available", "USD 745.87<br> (Lowest Fare)", "USD 846.36", "Not available", "Not available"}
        };

        for (int i = 0; i < arrivals.length; i++) {
            JLabel arrivalLabel = new JLabel("<html><center>" + arrivals[i] + "</center></html>", SwingConstants.CENTER);
            arrivalLabel.setOpaque(true);
            arrivalLabel.setBackground(new Color(120, 120, 120));
            arrivalLabel.setForeground(Color.WHITE);
            gridPanel.add(arrivalLabel);

            for (int j = 0; j < fares[i].length; j++) {
                String fareText = fares[i][j];
                JButton fareButton = new JButton("<html><center>" + fareText + "</center></html>");
                fareButton.setFocusPainted(false);
                fareButton.setBackground(Color.WHITE);
                fareButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                if (fareText.equals("Not available")) {
                    fareButton.setEnabled(false);
                    fareButton.setBackground(new Color(240, 240, 240));
                }


                fareButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (fareButton == selectedButton) {

                            fareButton.setBackground(Color.WHITE);
                            selectedButton = null;
                            selectedFare = null;
                        } else {

                            if (selectedButton != null) {
                                selectedButton.setBackground(Color.WHITE);
                            }
                            selectedButton = fareButton;
                            selectedFare = fareText;
                            fareButton.setBackground(new Color(173, 216, 230)); // light blue
                        }
                    }
                });

                gridPanel.add(fareButton);
            }
        }


        continueButton.addActionListener(e -> {
            if (selectedFare != null) {
                JOptionPane.showMessageDialog(frame,
                        "Proceeding with schedule: " + selectedFare,
                        "Continue",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Please select a schedule first!",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }
}


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Flight Fares");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 445);
            frame.setLocationRelativeTo(null);

            frame.setContentPane(new FlightFareGUI(frame));
            frame.setVisible(true);
        });
    }
}

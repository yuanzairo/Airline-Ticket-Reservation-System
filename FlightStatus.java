import javax.swing.; 
import javax.swing.table.DefaultTableModel;
import java.awt.;

/**

JFrame 900x445 with a scrollable JTable for flight information. */
public class FlightStatus extends JFrame {

public FlightStatus() {
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setSize(900, 445);
 setLocationRelativeTo(null); // center on screen

// Column names for flight info
 String[] columnNames = {
     "Flight ID", "Passenger Name", "Email", "Origin", "Destination", "Departure Time", "Arrival Time"
 };

 // Sample data (rows)
 Object[][] data = {
     {"FL001", "Ava Garcia", "ava.garcia@example.com", "New York", "London", "2025-10-05 08:30", "2025-10-05 20:10"},
     {"FL002", "Liam Smith", "liam.smith@example.com", "Los Angeles", "Tokyo", "2025-10-06 09:45", "2025-10-07 14:30"},
     {"FL003", "Olivia Brown", "olivia.brown@example.com", "Paris", "Rome", "2025-10-07 06:00", "2025-10-07 08:15"},
     {"FL004", "Noah Johnson", "noah.johnson@example.com", "Berlin", "Madrid", "2025-10-08 11:20", "2025-10-08 14:05"},
     {"FL005", "Emma Jones", "emma.jones@example.com", "Sydney", "Singapore", "2025-10-09 17:00", "2025-10-09 21:30"},
     {"FL006", "Lucas Garcia", "lucas.garcia@example.com", "Dubai", "Mumbai", "2025-10-10 13:10", "2025-10-10 17:55"},
     {"FL007", "Mia Martinez", "mia.martinez@example.com", "Toronto", "Chicago", "2025-10-11 08:45", "2025-10-11 10:30"},
     {"FL008", "Ethan Davis", "ethan.davis@example.com", "Hong Kong", "Bangkok", "2025-10-12 12:30", "2025-10-12 15:50"},
     {"FL009", "Sophia Lopez", "sophia.lopez@example.com", "San Francisco", "Seattle", "2025-10-13 07:20", "2025-10-13 09:10"},
     {"FL010", "Isabella Wilson", "isabella.wilson@example.com", "Chicago", "Miami", "2025-10-14 14:40", "2025-10-14 18:30"}
 };

 DefaultTableModel model = new DefaultTableModel(data, columnNames) {
     @Override
     public boolean isCellEditable(int row, int column) {
         return false; // make table read-only
     }
 };

 JTable table = new JTable(model);
 table.setAutoCreateRowSorter(true);
 table.setFillsViewportHeight(true);
 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

 // Adjust column widths
 table.getColumnModel().getColumn(0).setPreferredWidth(80);  // Flight ID
 table.getColumnModel().getColumn(1).setPreferredWidth(140); // Passenger Name
 table.getColumnModel().getColumn(2).setPreferredWidth(200); // Email
 table.getColumnModel().getColumn(3).setPreferredWidth(100); // Origin
 table.getColumnModel().getColumn(4).setPreferredWidth(100); // Destination
 table.getColumnModel().getColumn(5).setPreferredWidth(120); // Departure Time
 table.getColumnModel().getColumn(6).setPreferredWidth(120); // Arrival Time

 JScrollPane scrollPane = new JScrollPane(table);
 table.setPreferredScrollableViewportSize(new Dimension(860, 380));

 getContentPane().setLayout(new BorderLayout());
 getContentPane().add(scrollPane, BorderLayout.CENTER);

}

public static void main(String[] args) {
SwingUtilities.invokeLater(() -> { 
 FlightStatus frame = new FlightStatus(); 
 frame.setVisible(true); });
 }
}



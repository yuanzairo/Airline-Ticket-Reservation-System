import java.awt.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class DashBoard extends JFrame {
    private JLayeredPane layeredPane;
    private JPanel contentPanel;
    JPanel bgPic;
    int algoType = 1;

    static String[] cities = {
            "Bacolod (BCD)",
            "Butuan (BXU)",
            "Cagayan de Oro (CGY)",
            "Cebu (CEB)",
            "Davao (DVO)",
            "General Santos (GES)",
            "Iloilo (ILO)",
            "Legazpi (LGP)",
            "Manila (MNL)",
            "Puerto Princesa (PPS)",
            "Tacloban (TAC)",
            "Tagbilaran (TAG)",
            "Zamboanga (ZAM)"
    };

    public DashBoard() {
        setTitle("Davao Airlines");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(900, 500));
        setContentPane(layeredPane);

        bgPic = new JPanel() {
            Image bg = new ImageIcon("images\\background.jpg").getImage();

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
        bgPic.setBounds(0, 0, 900, 500);

        // ===== Navigation bar =====
        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        navBar.setOpaque(false);

        JButton bookFlight = createNavButton("Book a Flight");
        JButton travelInfo = createNavButton("Travel Info");
        JButton flightStatus = createNavButton("Flight Status");

        navBar.add(bookFlight);
        navBar.add(travelInfo);
        navBar.add(flightStatus);
        bgPic.add(navBar, BorderLayout.NORTH);

        // ===== Content panel =====
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        bgPic.add(contentPanel, BorderLayout.CENTER);
        layeredPane.add(bgPic, JLayeredPane.DEFAULT_LAYER);

        // Show dashboard content by default
        showDashboardContent();

        // ===== Navigation button actions =====
        bookFlight.addActionListener(e -> showDashboardContent());
        travelInfo.addActionListener(e -> showTravelInfoContent());
        flightStatus.addActionListener(e -> showFlightStatusContent());

        setVisible(true);
    }

    private void showDashboardContent() {
        contentPanel.removeAll();
        contentPanel.add(createDashboardPanel(), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showTravelInfoContent() {
        contentPanel.removeAll();
        JPanel travelPanel = new JPanel() {
            Image bg = new ImageIcon("images\\map.jpg").getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH);;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.drawImage(bg, 0, -30, getWidth(), getHeight(), this);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.10f));
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        contentPanel.add(travelPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showFlightStatusContent() {
        contentPanel.removeAll();
        contentPanel.add(new FlightStatusPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createDashboardPanel() {
        JPanel content = new JPanel(null);
        content.setOpaque(false);

        JPanel logoPanel = new JPanel(null);
        logoPanel.setOpaque(false);
        logoPanel.setBounds(0, 0, 900, 320);

        ImageIcon logo = new ImageIcon("images\\logo.png");
        Image logoImg = logo.getImage().getScaledInstance(300, 350, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(20, 20, 250, 200);
        logoPanel.add(logoLabel);

        JLabel title = new JLabel("Time for a new");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        title.setBounds(320, 30, 600, 130);
        logoPanel.add(title);

        JLabel title2 = new JLabel("Journey!");
        title2.setFont(new Font("Arial", Font.BOLD, 50));
        title2.setForeground(Color.WHITE);
        title2.setBounds(400, 80, 600, 130);
        logoPanel.add(title2);

        JLabel paragraph = new JLabel("Enjoy seamless connectivity to cities across the Philippines.");
        paragraph.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph.setForeground(Color.WHITE);
        paragraph.setBounds(310, 150, 740, 110);
        logoPanel.add(paragraph);

        JLabel paragraph2 = new JLabel("Fly with Davao Airlines and experience comfort, convenience,");
        paragraph2.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph2.setForeground(Color.WHITE);
        paragraph2.setBounds(310, 170, 740, 110);

        JLabel paragraph3 = new JLabel("and unbeatable service on every trip.");
        paragraph3.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph3.setForeground(Color.WHITE);
        paragraph3.setBounds(380, 190, 740, 110);

        JLabel paragraph4 = new JLabel("Book your adventure today and discover the Philippines like never before!");
        paragraph4.setFont(new Font("Arial", Font.PLAIN, 14));
        paragraph4.setForeground(Color.WHITE);
        paragraph4.setBounds(260, 210, 740, 110);

        logoPanel.add(paragraph);
        logoPanel.add(paragraph2);
        logoPanel.add(paragraph3);
        logoPanel.add(paragraph4);

        content.add(logoPanel);

        JPanel flightPanel = new JPanel(null);
        flightPanel.setBackground(new Color(255, 255, 255));
        flightPanel.setBounds(35, 280, 830, 110);

        JLabel fromLabel = new JLabel("From");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        fromLabel.setBounds(20, 10, 100, 20);
        flightPanel.add(fromLabel);

        JComboBox<String> fromCombo = new JComboBox<>(cities);
        fromCombo.setFont(new Font("Arial", Font.BOLD, 14));
        fromCombo.setBounds(20, 30, 180, 35);
        fromCombo.setSelectedItem(cities[0]);
        flightPanel.add(fromCombo);

        JLabel route = new JLabel("Route: ");
        route.setFont(new Font("Arial", Font.BOLD, 16));
        route.setBounds(20, 70, 500, 25);
        flightPanel.add(route);

        JLabel toLabel = new JLabel("To");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        toLabel.setBounds(210, 10, 100, 20);
        flightPanel.add(toLabel);

        JComboBox<String> toCombo = new JComboBox<>(cities);
        toCombo.setFont(new Font("Arial", Font.BOLD, 14));
        toCombo.setBounds(210, 30, 180, 35);
        toCombo.setSelectedItem(cities[1]);
        flightPanel.add(toCombo);

        JLabel departLabel = new JLabel("Depart");
        departLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        departLabel.setBounds(400, 10, 100, 20);
        flightPanel.add(departLabel);

        JDateChooser departChooser = new JDateChooser();
        departChooser.setDateFormatString("dd MMM yyyy");
        departChooser.getJCalendar().setWeekOfYearVisible(false);
        departChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        departChooser.setBounds(400, 30, 130, 35);
        departChooser.setDate(new Date());
        departChooser.setMinSelectableDate(new Date());
        flightPanel.add(departChooser);

        JButton algo1 = new JButton("Dijkstra 1");
        algo1.setFont(new Font("Arial", Font.BOLD, 14));
        algo1.setBounds(420, 75, 100, 25);
        flightPanel.add(algo1);
        algo1.setEnabled(false);

        JButton algo2 = new JButton("Dijkstra 2");
        algo2.setFont(new Font("Arial", Font.BOLD, 14));
        algo2.setBounds(540, 75, 100, 25);
        flightPanel.add(algo2);

        JLabel returnLabel = new JLabel("Return");
        returnLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        returnLabel.setBounds(540, 10, 100, 20);
        flightPanel.add(returnLabel);

        JDateChooser returnChooser = new JDateChooser();
        returnChooser.setDateFormatString("dd MMM yyyy");
        returnChooser.getJCalendar().setWeekOfYearVisible(false);
        returnChooser.setFont(new Font("Arial", Font.PLAIN, 14));
        returnChooser.setBounds(540, 30, 130, 35);
        flightPanel.add(returnChooser);
        returnChooser.setMinSelectableDate(new Date());

        JLabel priceLabel = new JLabel("Price: ₱0");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(new Color(0, 100, 0));
        priceLabel.setBounds(680, 5, 200, 25);
        flightPanel.add(priceLabel);

        JLabel runtime = new JLabel("Runtime: ");
        runtime.setFont(new Font("Arial", Font.BOLD, 16));
        runtime.setBounds(660, 80, 200, 25);
        flightPanel.add(runtime);

        JButton searchBtn = new JButton("Book a flight");
        searchBtn.setBackground(new Color(78, 159, 229));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 14));
        searchBtn.setBounds(680, 28, 130, 38);
        flightPanel.add(searchBtn);

        // update handlers
        fromCombo.addActionListener(
                e -> updatePrice(departChooser, returnChooser, priceLabel, fromCombo, toCombo, route));
        toCombo.addActionListener(
                e -> updatePrice(departChooser, returnChooser, priceLabel, fromCombo, toCombo, route));

        departChooser.getDateEditor().addPropertyChangeListener("date", e -> {
            Date departDate = departChooser.getDate();
            if (departDate != null) {
                returnChooser.setMinSelectableDate(departDate);
            }
            updatePrice(departChooser, returnChooser, priceLabel, fromCombo, toCombo, route);
        });
        returnChooser.getDateEditor().addPropertyChangeListener("date", e -> {
            Date returnDate = returnChooser.getDate();
            if (returnDate != null) {
                departChooser.setMaxSelectableDate(returnDate);
            }
            updatePrice(departChooser, returnChooser, priceLabel, fromCombo, toCombo, route);
        });

        searchBtn.addActionListener(e -> {
            Date departDate = departChooser.getDate();
            Date returnDate = returnChooser.getDate();
            if (departDate == null || returnDate == null) {
                JOptionPane.showMessageDialog(null, "Please select both departure and return dates.");
                return;
            }
            String from = (String) fromCombo.getSelectedItem();
            String to = (String) toCombo.getSelectedItem();
            String priceText = priceLabel.getText().replace("Price: ₱", "").trim();
            String routeText = route.getText().replace("Route: ", "").trim();
            double price = Double.parseDouble(priceText);
            new BookFlight(from, to, routeText, departDate, returnDate, price);
        });

        algo1.addActionListener(e -> {
            algo1.setEnabled(false);
            algo2.setEnabled(true);
            algoType = 1;
        });

        algo2.addActionListener(e -> {
            algo2.setEnabled(false);
            algo1.setEnabled(true);
            algoType = 2;
        });

        content.add(flightPanel);
        return content;
    }

    // ===== City coordinates =====
    private static final Map<String, double[]> cityCoords = new HashMap<>();
    private static final double[][] coordinates = {
            { 10.7764, 123.0150 }, // BCD
            { 8.9494, 125.5130 }, // BXU
            { 8.4156, 124.6110 }, // CGY
            { 10.3075, 123.9790 }, // CEB
            { 7.1255, 125.6460 }, // DVO
            { 6.1064, 125.2350 }, // GES
            { 10.8330, 122.4934 }, // ILO
            { 13.1580, 123.7390 }, // LGP
            { 14.5086, 121.0200 }, // MNL
            { 9.7422, 118.7520 }, // PPS
            { 11.2320, 125.0270 }, // TAC
            { 9.6489, 124.0420 }, // TAG
            { 6.9224, 122.0600 } // ZAM
    };

    static {
        for (int i = 0; i < cities.length; i++) {
            cityCoords.put(cities[i], coordinates[i]);
        }
    }

    // --- Haversine formula ---
    private static double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // ===== Realistic flight network =====
    private static Map<String, Map<String, Double>> buildGraph() {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (String city : cities)
            graph.put(city, new HashMap<>());

        connect(graph, "Manila (MNL)", "Iloilo (ILO)");
        connect(graph, "Manila (MNL)", "Cebu (CEB)");
        connect(graph, "Manila (MNL)", "Legazpi (LGP)");
        connect(graph, "Manila (MNL)", "Puerto Princesa (PPS)");

        connect(graph, "Legazpi (LGP)", "Tacloban (TAC)");
        connect(graph, "Tacloban (TAC)", "Butuan (BXU)");
        connect(graph, "Iloilo (ILO)", "Bacolod (BCD)");
        connect(graph, "Bacolod (BCD)", "Tagbilaran (TAG)");

        connect(graph, "Tagbilaran (TAG)", "Cagayan de Oro (CGY)");

        connect(graph, "Cebu (CEB)", "Cagayan de Oro (CGY)");
        connect(graph, "Cebu (CEB)", "Tacloban (TAC)");

        connect(graph, "Cagayan de Oro (CGY)", "Zamboanga (ZAM)");
        connect(graph, "Cagayan de Oro (CGY)", "Davao (DVO)");
        connect(graph, "Cagayan de Oro (CGY)", "Butuan (BXU)");

        connect(graph, "Davao (DVO)", "General Santos (GES)");
        connect(graph, "General Santos (GES)", "Zamboanga (ZAM)");
        connect(graph, "Zamboanga (ZAM)", "Iloilo (ILO)");

        connect(graph, "Puerto Princesa (PPS)", "Zamboanga (ZAM)");
        connect(graph, "Puerto Princesa (PPS)", "Iloilo (ILO)");
        connect(graph, "Butuan (BXU)", "Davao (DVO)");
        return graph;
    }

    private static void connect(Map<String, Map<String, Double>> graph, String a, String b) {
        double[] c1 = cityCoords.get(a);
        double[] c2 = cityCoords.get(b);
        double dist = haversineDistance(c1[0], c1[1], c2[0], c2[1]);
        graph.get(a).put(b, dist);
        graph.get(b).put(a, dist);
    }

    // ===== Dijkstra =====
    private static Map<String, Object> dijkstra(Map<String, Map<String, Double>> graph, String start,
            String end) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String city : graph.keySet())
            dist.put(city, Double.MAX_VALUE);
        dist.put(start, 0.0);

        while (visited.size() < graph.size()) {
            String u = null;
            double min = Double.MAX_VALUE;
            for (String city : graph.keySet()) {
                if (!visited.contains(city) && dist.get(city) < min) {
                    u = city;
                    min = dist.get(city);
                }
            }
            if (u == null)
                break;
            visited.add(u);

            for (Map.Entry<String, Double> entry : graph.get(u).entrySet()) {
                String v = entry.getKey();
                double alt = dist.get(u) + entry.getValue();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                }
            }
        }

        LinkedList<String> path = new LinkedList<>();
        for (String at = end; at != null; at = prev.get(at))
            path.addFirst(at);

        Map<String, Object> result = new HashMap<>();
        result.put("distance", dist.get(end));
        result.put("path", path);
        return result;
    }

    private static Map<String, Object> dijkstra2(
            Map<String, Map<String, Double>> graph,
            String start,
            String end) {

        Map<String, Double> distForward = new HashMap<>();
        Map<String, Double> distBackward = new HashMap<>();
        Map<String, String> prevForward = new HashMap<>();
        Map<String, String> prevBackward = new HashMap<>();
        Set<String> visitedForward = new HashSet<>();
        Set<String> visitedBackward = new HashSet<>();

        for (String city : graph.keySet()) {
            distForward.put(city, Double.MAX_VALUE);
            distBackward.put(city, Double.MAX_VALUE);
        }
        distForward.put(start, 0.0);
        distBackward.put(end, 0.0);

        PriorityQueue<CityDistance> pqForward = new PriorityQueue<>(Comparator.comparingDouble(cd -> cd.distance));
        PriorityQueue<CityDistance> pqBackward = new PriorityQueue<>(Comparator.comparingDouble(cd -> cd.distance));

        pqForward.add(new CityDistance(start, 0.0));
        pqBackward.add(new CityDistance(end, 0.0));

        String meetingNode = null;
        double bestDistance = Double.MAX_VALUE;

        while (!pqForward.isEmpty() && !pqBackward.isEmpty()) {
            // Forward step
            if (!pqForward.isEmpty()) {
                CityDistance currentF = pqForward.poll();
                String uF = currentF.city;
                if (visitedForward.contains(uF))
                    continue;
                visitedForward.add(uF);

                if (visitedBackward.contains(uF)) {
                    double totalDist = distForward.get(uF) + distBackward.get(uF);
                    if (totalDist < bestDistance) {
                        bestDistance = totalDist;
                        meetingNode = uF;
                    }
                }

                for (Map.Entry<String, Double> neighbor : graph.get(uF).entrySet()) {
                    String v = neighbor.getKey();
                    double weight = neighbor.getValue();
                    double alt = distForward.get(uF) + weight;
                    if (alt < distForward.get(v)) {
                        distForward.put(v, alt);
                        prevForward.put(v, uF);
                        pqForward.add(new CityDistance(v, alt));
                    }
                }
            }

            // Backward step
            if (!pqBackward.isEmpty()) {
                CityDistance currentB = pqBackward.poll();
                String uB = currentB.city;
                if (visitedBackward.contains(uB))
                    continue;
                visitedBackward.add(uB);

                if (visitedForward.contains(uB)) {
                    double totalDist = distForward.get(uB) + distBackward.get(uB);
                    if (totalDist < bestDistance) {
                        bestDistance = totalDist;
                        meetingNode = uB;
                    }
                }

                for (Map.Entry<String, Double> neighbor : graph.get(uB).entrySet()) {
                    String v = neighbor.getKey();
                    double weight = neighbor.getValue();
                    double alt = distBackward.get(uB) + weight;
                    if (alt < distBackward.get(v)) {
                        distBackward.put(v, alt);
                        prevBackward.put(v, uB);
                        pqBackward.add(new CityDistance(v, alt));
                    }
                }
            }
        }

        // Reconstruct path
        LinkedList<String> path = new LinkedList<>();
        if (meetingNode != null) {
            String node = meetingNode;
            LinkedList<String> forwardPath = new LinkedList<>();
            while (node != null) {
                forwardPath.addFirst(node);
                node = prevForward.get(node);
            }

            node = prevBackward.get(meetingNode);
            LinkedList<String> backwardPath = new LinkedList<>();
            while (node != null) {
                backwardPath.addLast(node);
                node = prevBackward.get(node);
            }

            path.addAll(forwardPath);
            path.addAll(backwardPath);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("distance", bestDistance);
        result.put("path", path);
        return result;

    }

    // Helper class to store city name + distance
    private static class CityDistance {
        String city;
        double distance;

        CityDistance(String city, double distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    // ===== Update price and route =====
    private void updatePrice(JDateChooser departChooser, JDateChooser returnChooser, JLabel priceLabel,
            JComboBox<String> fromCombo, JComboBox<String> toCombo, JLabel routeLabel) {
        long startTime = System.nanoTime();

        String from = (String) fromCombo.getSelectedItem();
        String to = (String) toCombo.getSelectedItem();
        if (from.equals(to))
            return;

        Map<String, Map<String, Double>> graph = buildGraph();
        Map<String, Object> result = dijkstra(graph, from, to);
        ;
        switch (algoType) {
            case 1:
                result = dijkstra(graph, from, to);
                break;
            case 2:
                result = dijkstra2(graph, from, to);
                break;
        }
        double distance = (double) result.get("distance");
        @SuppressWarnings("unchecked")
        java.util.List<String> path = (java.util.List<String>) result.get("path");

        String routeText = String.join(" → ",
                path.stream()
                        .map(city -> city.substring(city.indexOf("(") + 1, city.indexOf(")")))
                        .toArray(String[]::new));
        routeLabel.setText("Route: " + routeText);

        double baseFare = 1000;
        double price = baseFare + distance * 8;
        priceLabel.setText("Price: ₱" + String.format("%.2f", price));

        long endTime = System.nanoTime();
        double runtimeMs = (endTime - startTime) / 1_000_000.0;
        JLabel runtimeLabel = findRuntimeLabel(priceLabel.getParent());
        if (runtimeLabel != null)
            runtimeLabel.setText(String.format("Runtime: %.6f ms", runtimeMs));
    }

    private JLabel findRuntimeLabel(Container container) {
        for (Component c : container.getComponents())
            if (c instanceof JLabel && ((JLabel) c).getText().startsWith("Runtime:"))
                return (JLabel) c;
        return null;
    }

    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashBoard::new);
    }
}

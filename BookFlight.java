package airportTest;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookFlight{

	public static JButton reusableButton(String text) {
	    JButton button = new JButton(text);
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    return button;
	} 
	
    public static void main(String[] args) {
    	
        JFrame bookFlightFrame = new JFrame("Airline Booking");
        bookFlightFrame.setSize(900, 445);
        bookFlightFrame.setLocationRelativeTo(null);
        bookFlightFrame.setResizable(false);
        bookFlightFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookFlightFrame.setLayout(null);

        Image bgImage = new ImageIcon("images/bg.jpg").getImage().getScaledInstance(900, 445, Image.SCALE_SMOOTH);
        JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
        bgLabel.setBounds(0, 0, 900, 445);
        bgLabel.setLayout(null);
        bookFlightFrame.add(bgLabel); 

        JPanel frontPanel = new JPanel(null);
        frontPanel.setOpaque(false);
        frontPanel.setBounds(0, 0, 900, 445);
        bgLabel.add(frontPanel);   
        
        Image icon = new ImageIcon("images/logo.png").getImage().getScaledInstance(200, 90, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(icon));
        iconLabel.setBounds(20, 10, 300, 75);
        frontPanel.add(iconLabel);

        // Button
        JButton buttonBookFlight = reusableButton("Book a flight");
        buttonBookFlight.setBounds(350, 40, 120, 40);     
        frontPanel.add(buttonBookFlight);

        JButton buttonTravelInfo = reusableButton("Travel Info");
        buttonTravelInfo.setBounds(490, 40, 120, 40);
        buttonTravelInfo.setBorderPainted(false);
        buttonTravelInfo.setFocusPainted(false);
        frontPanel.add(buttonTravelInfo);

        JButton buttonFlightStatus = reusableButton("Flight Status");
        buttonFlightStatus.setBounds(630, 40, 120, 40);
        frontPanel.add(buttonFlightStatus);
        
        JPanel flights = new JPanel(null);
        flights.setBounds(0, 0, 900, 445);
        flights.setBackground(Color.WHITE);
        flights.setBounds(40, 90, 810, 300);
        bgLabel.add(flights);
        
        // flight 1
        JPanel flight1 = new JPanel(null);
        flight1.setBackground(Color.LIGHT_GRAY);
        flight1.setBounds(40, 40, 300, 100);
        flights.add(flight1);

        JLabel flightInfo1 = new JLabel("Current ➝ Destination");
        flightInfo1.setBounds(20, 10, 200, 20);
        flight1.add(flightInfo1);

        JLabel date1 = new JLabel("Week, Day-Month");
        date1.setBounds(20, 40, 200, 20);
        flight1.add(date1);

        JButton select1 = reusableButton("SELECT");
        select1.setBounds(180, 60, 100, 30);
        flight1.add(select1);

        // flight 2
        JPanel flight2 = new JPanel(null);
        flight2.setBackground(Color.LIGHT_GRAY);
        flight2.setBounds(430, 40, 300, 100);
        flights.add(flight2);

        JLabel flightInfo2 = new JLabel("Current ➝ Destination");
        flightInfo2.setBounds(20, 10, 200, 20);
        flight2.add(flightInfo2);

        JLabel date2 = new JLabel("Week, Day-Month");
        date2.setBounds(20, 40, 200, 20);
        flight2.add(date2);

        JButton select2 = reusableButton("SELECT");
        select2.setBounds(180, 60, 100, 30);
        flight2.add(select2);

        // flight 3
        JPanel flight3 = new JPanel(null);
        flight3.setBackground(Color.LIGHT_GRAY);
        flight3.setBounds(40, 160, 300, 100);
        flights.add(flight3);

        JLabel flightInfo3 = new JLabel("Current ➝ Destination");
        flightInfo3.setBounds(20, 10, 200, 20);
        flight3.add(flightInfo3);

        JLabel date3 = new JLabel("Week, Day-Month");
        date3.setBounds(20, 40, 200, 20);
        flight3.add(date3);

        JButton select3 = reusableButton("SELECT");
        select3.setBounds(180, 60, 100, 30);
        flight3.add(select3);

        // flight 4
        JPanel flight4 = new JPanel(null);
        flight4.setBackground(Color.LIGHT_GRAY);
        flight4.setBounds(430, 160, 300, 100);
        flights.add(flight4);

        JLabel flightInfo4 = new JLabel("Current ➝ Destination");
        flightInfo4.setBounds(20, 10, 200, 20);
        flight4.add(flightInfo4);

        JLabel date4 = new JLabel("Week, Day-Month");
        date4.setBounds(20, 40, 200, 20);
        flight4.add(date4);

        JButton select4 = reusableButton("SELECT");
        select4.setBounds(180, 60, 100, 30);
        flight4.add(select4);

        bookFlightFrame.setVisible(true);
    }
}

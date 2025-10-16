# ✈️ Airline Ticket Reservation System

A desktop-based Airline Ticket Reservation System developed in Java Swing. This project demonstrates the design of a graphical user interface that allows users to manage flight schedules, passengers, and ticket bookings. The system integrates two versions of Dijkstra’s Algorithm (dijkstra() and dijkstra2()) to determine the shortest and most efficient routes between destinations. Additionally, HashMaps are used to store and retrieve data quickly, reducing search time and improving system performance.

---

## 🧭 Project Overview
Airline Reservation System allows its users to:

- Search and display available flights between major cities.
- Reserve and view flight tickets.
- Manage passenger information.
- Calculate and display the shortest or most efficient travel route using Dijkstra’s Algorithm.
- Utilize HashMap structures for efficient data storage and retrieval.

All data (such as bookings, routes, and passengers) is stored in MySql.

## 👥 Team Members and Roles 
| Name | Role | 
|------|------| 
| Gilbert Isaac A. Busano | Testing Lead / technical support | 
| Yuan Zairo G. Caranto | Backend / Database and Algorithm | 
| Neilsen M. Ilaga | Backend / System Analyst / Documentation | 
| Khiana Marie T. Enoc | Frontend / UI designer / Documentation | 
| Kobe R. Tenorio | Backend / Algorithm / Documentation |

---

## 🧩 Features 
- Add and view flights and destinations
- Add and view passenger records
- Book flight reservations
- Route optimization using Dijkstra’s Algorithm
- Comparison between standard and optimized runtime performance
- Store and retrieve data efficiently using HashMap
- User-friendly GUI built with Java Swing

## ⚙️ How to Run the Project
1. Clone the repository: https://github.com/yuanzairo/Airline-Ticket-Reservation-System.git
2. Open the project in your preferred IDE (e.g., NetBeans, IntelliJ IDEA).
3. Run the AirlineTicketReservationSystem.java file.

**Application Flow:**
1. Launch the system.
2. Login window for authentication (User: Admin | Pass: 12345).
3. Add flights and destinations to populate the list.
4. Use the booking module to reserve a flight for a passenger.
5. View existing reservations.
6. Use the route optimization feature to compute the shortest path between cities.
7. Compare algorithm performance in the dashboard panel.

---

## 🧠 Algorithm Design 
| Algorithm Type | Base Implementation | Improved Implementation | Expected Time Complexity Change | 
|----------------|---------------------|--------------------------|--------------------------------| 
| Pathfinding | Standard Dijkstra’s Algorithm | Optimized Dijkstra’s with Priority Queue (Min-Heap) | O(V²) → O((V + E) log V) | 
| Data Access | Array or List Lookup | HashMap Key-Based Lookup | O(n) → O(1) |

---

## 🧩 UML Diagram
| Diagram Type | Description | Diagram |
|---------------|--------------|----------|
| Activity Diagram | An Activity Diagram is a UML behavioral diagram that shows the flow of activities or actions in a system or process. It visually represents the workflow, including decisions, loops, and parallel actions. | <img width="543" height="677" alt="image" src="https://github.com/user-attachments/assets/ff80cea9-360a-45c4-b8d5-916041ee8d7e" />
 |

---

## 🖼️ Screenshots

| **Panel** | **Description** | **Screenshot** |
|------------|------------------|----------------|
| **Login Page** | Starting page for authentication | <img width="396" height="250" alt="Login Page" src="https://github.com/user-attachments/assets/661527d2-4d5a-447d-83d7-d4657bdd2ef2" /> |
| **Dashboard** | Displays all flights and options for management | <img width="892" height="506" alt="Dashboard" src="https://github.com/user-attachments/assets/c0af1bc6-598a-4c67-bc22-32362d2d2055" /> |
| **Flight Selection** | Interface for ticket reservation | <img width="890" height="500" alt="Flight Selection" src="https://github.com/user-attachments/assets/15b90838-4d2f-4a9e-abbf-568fa0042a4a" /> |
| **Flight Status** | Interface for checking flight status | <img width="891" height="498" alt="Flight Status" src="https://github.com/user-attachments/assets/0f3ebf5e-4b54-4f83-b7fa-7abd9b587867" /> |
| **Travel Info** | Panel that shows the map of the Philippines | <img width="892" height="504" alt="Travel Info" src="https://github.com/user-attachments/assets/4d81e2e5-dbef-4301-8f2e-a5a3efa4be29" /> |

## Enhancement Recommendations
- Specify 13 cities
- Comparison of running time
- Table of comparison between Dijkstra 1 and 2 in the Results and Discussion
  

--DDL
CREATE DATABASE airline;

CREATE TABLE admins (
    username varchar(50) PRIMARY KEY,
    password varchar(255)
):
    
CREATE TABLE passenger (
    passenger_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL,
    contact VARCHAR(50) NOT NULL
);

CREATE TABLE bookings (
    booking_id VARCHAR(10) PRIMARY KEY,
    passenger_id VARCHAR(10) NOT NULL,
    origin VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    depart_date DATE NOT NULL,
    return_date DATE NOT NULL,
    depart_time VARCHAR(10) NOT NULL,
    return_time VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    route varchar(255),
    FOREIGN KEY (passenger_id) REFERENCES passenger(passenger_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

--DML
SELECT * FROM admins WHERE username = ? AND password = ?
SELECT * FROM passenger WHERE passenger_id = ?
SELECT passenger_id FROM passenger ORDER BY passenger_id DESC LIMIT 1
SELECT booking_id FROM bookings ORDER BY booking_id DESC LIMIT 1
  
INSERT INTO passenger (passenger_id, name, age, email, contact) VALUES (?, ?, ?, ?, ?)
INSERT INTO bookings (booking_id, passenger_id, origin, destination, depart_date, return_date, depart_time, return_time, price, route) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

SELECT b.booking_id, b.origin, b.destination, b.depart_date, b.return_date, " +
"b.depart_time, b.return_time, b.price, b.route, p.name, p.email " +
"FROM bookings b INNER JOIN passenger p ON b.passenger_id = p.passenger_id " +
"WHERE b.booking_id = ?


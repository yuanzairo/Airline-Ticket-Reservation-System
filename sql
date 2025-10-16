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

CREATE TABLE cities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    latitude DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL
);

DELIMITER $$

CREATE PROCEDURE InsertCities()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE city_name VARCHAR(100);
    DECLARE lat DECIMAL(10,8);
    DECLARE lon DECIMAL(11,8);

    WHILE i < 1000 DO
        SET city_name = CONCAT('City (', LPAD(i, 3, '0'), ')');
        SET lat = ROUND((-90 + (RAND() * 180)), 8);
        SET lon = ROUND((-180 + (RAND() * 360)), 8);

        INSERT INTO cities (name, latitude, longitude)
        VALUES (city_name, lat, lon);

        SET i = i + 1;
    END WHILE;
END$$

DELIMITER;

CALL InsertCities();

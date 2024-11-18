-- Create Database
CREATE DATABASE IF NOT EXISTS hotel_management;
USE hotel_management;

-- 1. Users Table
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) UNIQUE,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,  -- Store hashed password
    email VARCHAR(50) UNIQUE,
    role ENUM('user', 'admin') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Rooms Table
CREATE TABLE Rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_type ENUM('1bed', '2bed', '4bed', '6bed') NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    availability BOOLEAN DEFAULT TRUE,
    check_in_date DATE,
    check_out_date DATE
);

-- 3. Food Table
CREATE TABLE Food (
    food_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(50) NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    availability BOOLEAN DEFAULT TRUE
);

-- 4. Employees Table
CREATE TABLE Employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    designation ENUM('waiter', 'cook', 'employee', 'driver') NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

-- 5. Bookings Table (Room Bookings)
CREATE TABLE Bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2),
    status ENUM('confirmed', 'cancelled') DEFAULT 'confirmed',
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (room_id) REFERENCES Rooms(room_id) ON DELETE SET NULL
);

-- 6. FoodOrders Table
CREATE TABLE FoodOrders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    food_id INT NOT NULL,
    quantity INT DEFAULT 1,
    total_amount DECIMAL(8, 2) NOT NULL,
    status ENUM('preparing', 'delivering', 'delivered') DEFAULT 'preparing',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (food_id) REFERENCES Food(food_id) ON DELETE SET NULL
);

-- Insert Sample Data

-- Insert Users (Admin & User)
INSERT INTO Users (name, phone_number, username, password, email, role) 
VALUES 
('Admin User', '1234567890', 'admin', 'adminpass123', 'admin@example.com', 'admin'),
('John Doe', '9876543210', 'johndoe', 'userpass123', 'john@example.com', 'user');

-- Insert Room Data
INSERT INTO Rooms (room_type, price, availability) 
VALUES 
('1bed', 1500.00, TRUE),
('2bed', 3000.00, TRUE),
('4bed', 5000.00, TRUE);

-- Insert Food Data
INSERT INTO Food (item_name, price, availability) 
VALUES 
('Pizza', 300.00, TRUE),
('Pasta', 250.00, TRUE),
('Burger', 150.00, TRUE);

-- Insert Employee Data
INSERT INTO Employees (name, dob, designation, salary) 
VALUES 
('Alice', '1990-05-15', 'waiter', 25000.00),
('Bob', '1985-07-20', 'cook', 30000.00);

-- Insert Bookings Data
INSERT INTO Bookings (user_id, room_id, total_amount, status) 
VALUES 
(2, 1, 1500.00, 'confirmed');

-- Insert Food Orders Data
INSERT INTO FoodOrders (user_id, food_id, quantity, total_amount, status) 
VALUES 
(2, 1, 2, 600.00, 'preparing');

-- Queries for Testing

-- Fetch all available rooms
SELECT * FROM Rooms WHERE availability = TRUE;

-- Check User Login
SELECT * FROM Users WHERE username = 'johndoe' AND password = 'userpass123';

-- Update Room Availability After Booking
UPDATE Rooms SET availability = FALSE WHERE room_id = 1;

-- Fetch User Booking History
SELECT * FROM Bookings WHERE user_id = 2;

-- Fetch Ordered Food Details
SELECT * FROM FoodOrders WHERE user_id = 2;

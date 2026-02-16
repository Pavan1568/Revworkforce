RevWorkForce – Console Based HRM System

RevWorkForce is a console-based Human Resource Management (HRM) application developed using Java and JDBC. The system is designed to manage employees, leave requests, and role-based access for Employees, Managers, and Admins.

The application follows a layered architecture using DAO–Service–Controller design pattern and connects to a MySQL database for data persistence.

This project demonstrates practical implementation of backend business logic, database integration, and role-based authorization in a real-world HR management scenario.

🏗️ Architecture

The application follows a clean layered architecture:

Controller Layer → Handles user interaction (Console menus)

Service Layer → Contains business logic

DAO Layer → Handles database operations using JDBC

Model Layer → Represents entities like Employee and LeaveRequest

Utility Layer → Database connection management

Exception Handling → Custom exceptions for validation and business rules

👥 User Roles & Features
👤 Employee Features

Login with email and password

View profile details

Edit personal information (phone, address, emergency contact)

Apply for leave

View leave history

Cancel pending leave requests

View leave balance (Casual, Sick, Paid)

Change password

👨‍💼 Manager Features

(All employee features + additional privileges)

View team members

View leave requests from team

View all leave requests

Approve or reject leave requests with comments

Manage team leave workflow

🛡️ Admin Features

Add new employees

Update employee details

Assign reporting managers

Search employees by name/email/ID

View all employees

Activate or deactivate employees (Soft Delete)

🔐 Business Logic Implemented

Role-based menu access (Employee / Manager / Admin)

Soft delete using status (ACTIVE / INACTIVE)

Only ACTIVE users can log in

Leave request approval workflow

Leave cancellation restricted to pending requests

Manager-based team filtering

Password validation and secure update process

🛠️ Technologies Used

Java

JDBC

MySQL

Maven

Log4j2 (Logging)

JUnit (Unit Testing)

Git & GitHub

🗄️ Database Design

Main tables used:

employee

leave_request

notification (if implemented)

Key relationships:

Self-referencing foreign key (manager_id)

Leave linked to employee via employee_id

Soft delete using status column

📂 Project Structure
com.revworkforce
│
├── controller
├── service
├── dao
├── model
├── util
├── exception
├── validation

🎯 Key Learning Outcomes

Implementation of layered architecture

JDBC integration with MySQL

PreparedStatement usage for secure queries

Role-based access control

Soft delete implementation

Handling foreign key constraints

Debugging real-world integration issues

🚀 Future Enhancements

Web-based UI (Spring Boot + REST APIs)

Role-based dashboard interface

Leave calendar view

Notification system improvement

Performance review module

Microservices architecture migration

👨‍💻 Author

Developed as part of backend Java training project to demonstrate practical HR management workflow implementation.


🏢 RevWorkForce – Human Resource Management System

📌 Project Overview

RevWorkForce is a console-based Human Resource Management (HRM) application developed using Java, JDBC, and MySQL.

The system is designed to streamline:

Employee management

Leave tracking

Manager approval workflows

Role-based access control

Administrative configuration

The application follows a clean DAO–Service–Controller layered architecture and is structured in a way that it can be extended into a web-based or microservices-based HRM system in future phases.

🎯 Project Objective

The objective of this project is to simulate a real-world HRM backend system that supports three primary roles:

👤 Employee

👨‍💼 Manager

🛡️ Admin

Each role has specific permissions and responsibilities based on business requirements.

🏗️ Architecture Design

The system is built using a layered architecture:

Controller Layer → Handles console-based user interaction

Service Layer → Contains business logic and validations

DAO Layer → Performs database operations using JDBC

Model Layer → Represents entities like Employee and LeaveRequest

Utility Layer → Database connection handling

Exception & Validation Layer → Custom exceptions and input validations

This design ensures:

Separation of concerns

Maintainability

Scalability for future web migration

👥 Functional Scope
👤 Employee Module

An employee can:

Login using email and password

View profile details

Edit profile (phone, address, emergency contact)

View reporting manager details

Apply for leave (Casual / Sick / Paid)

View leave history with status

Cancel pending leave requests

View leave balance

Change password

👨‍💼 Manager Module

Managers inherit all employee features plus:

View team members (based on manager_id)

View leave requests of direct reportees

View all leave requests

Approve or reject leave requests with comments

Manage team leave workflow

🛡️ Admin Module

Admin users can:

Add new employees

Update employee information

Assign or change reporting managers

Search employees by name, email, or ID

View all employees

Activate / Deactivate employees (Soft Delete)

Manage role assignments

Soft deletion is implemented using a status column (ACTIVE / INACTIVE), ensuring data integrity while preventing inactive users from logging in.

🔐 Business Logic Implemented

Role-based console menus

Soft delete mechanism using status column

Only ACTIVE employees can log in

Leave workflow validation

Leave cancellation restricted to pending requests

Manager-based filtering of team members

Secure password change validation

Foreign key constraint handling

Manager–Employee self-referencing relationship

🗄️ Database Design
Main Tables:

employee

leave_request

notification (if applicable)

Relationships:

manager_id → Self-referencing foreign key in employee table

employee_id → Foreign key in leave_request

Leave workflow tied to employee and manager roles

Status-based soft deletion logic

🛠️ Technologies Used

Java

JDBC

MySQL

Maven

Log4j2 (Logging)

JUnit (Unit Testing)

Git & GitHub

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

🚀 Professional Scope (As per Project Description)

The system is designed with extensibility in mind and can be expanded to include:

🔔 Notification System

In-app notifications stored in database

Leave approval/rejection alerts

Performance feedback notifications

Company announcements

📊 Performance Management Module

Employee self-assessment submission

Goal setting and tracking

Manager feedback and ratings

Performance review cycles

🗓 Leave Management Enhancements

Team leave calendar

Leave statistics & reports

Configurable leave policies

Holiday calendar management

🏢 Organizational Management

Department and designation management

Team hierarchy visualization

Attendance tracking

Anniversary and birthday reminders

🌐 Future Migration

REST API development

Spring Boot integration

Web-based UI

Microservices architecture

🎓 Learning Outcomes

This project demonstrates:

Layered backend architecture implementation

JDBC-based database integration

Role-based access control

Soft delete implementation

Business rule validation

Foreign key constraint handling

Error debugging and system stabilization

Real-world workflow simulation

📌 Conclusion

RevWorkForce represents a backend-focused HRM system that simulates real organizational processes including employee management, leave approval workflow, and administrative control.

The system is modular, extensible, and structured to evolve into a full-scale enterprise HR platform.

👨‍💻 Developed As

Backend Java training project demonstrating real-world HR workflow implementation using core Java technologies.

👨‍💻 Author

Developed as part of backend Java training project to demonstrate practical HR management workflow implementation.

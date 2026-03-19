# 🏢 RevWorkForce – Console-Based HRM System

**RevWorkForce** is a sophisticated, backend-driven Human Resource Management (HRM) platform designed to simulate enterprise-grade organizational workflows within a console environment. Built with the **DAO–Service–Controller** pattern, it demonstrates robust architectural principles and database management mastery.

---

## 🛠 Tech Stack

![Java](https://img.shields.io/badge/Java-17-black?labelColor=ED8B00&logo=java&logoColor=white) ![JDBC](https://img.shields.io/badge/JDBC-Database-black?labelColor=4479A1&logo=oracle&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-8.x-black?labelColor=4479A1&logo=mysql&logoColor=white) ![Maven](https://img.shields.io/badge/Maven-Build-black?labelColor=C71A36&logo=apache-maven&logoColor=white)

![JUnit](https://img.shields.io/badge/JUnit-Testing-black?labelColor=25A162&logo=junit5&logoColor=white) ![Log4j2](https://img.shields.io/badge/Log4j2-Logging-black?labelColor=D22128&logo=apache&logoColor=white) ![Git](https://img.shields.io/badge/Git-Control-black?labelColor=F05032&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-Remote-black?labelColor=181717&logo=github&logoColor=white)

---

## 🏗 Modular Architecture Design

The system is engineered for maximum maintainability and future scalability through a strict layered approach.

```mermaid
graph LR
    User((User Console)) --> Controller[Controller Layer]
    Controller --> Service[Service Layer]
    Service --> DAO[DAO Layer]
    DAO --> DB[(MySQL Database)]
    Service -.-> Models[Models / Utils / Exceptions]
```

---

## 👥 Role-Based Capabilities

### 🧑‍💻 **Employee Module**
- 👤 **Profile Control**: View and update contact details and emergency info.
- 🗓 **Leave Lifecycle**: Apply for Sick, Casual, or Paid leaves with real-time status tracking.
- 💹 **Balance Overview**: Monitor remaining leave quotas dynamically.
- 🔐 **Security**: Self-service password updates for account safety.

### 👨‍💼 **Manager Module**
- 🦸 **Team Oversight**: Manage direct reports based on organizational hierarchy.
- ⚡ **Rapid Approvals**: Process leave requests with comments and mandatory justifications.
- 🔍 **Real-Time Visibility**: Filter and monitor team-wide leave calendars.

### 🛡️ **Admin Module**
- 🏗 **Enterprise Management**: Onboard new hires and update core employee records.
- 🖇 **Hierarchy Orchestration**: Define reporting managers and departmental structures.
- 💾 **Safe Deletion**: Implement **Soft Delete** logic via status-based filtering (Active/Inactive).

---

## 🔐 Core Business Intelligence
- 🛡 **Access Security**: Advanced Role-Based Access Control (RBAC) across all modules.
- 💎 **Data Integrity**: Foreign key constraints and self-referencing relationship management.
- 🚦 **Workflow Guardrails**: strict validation for leave applications and password changes.
- 🪵 **Strategic Logging**: Comprehensive **Log4j2** integration for audit trails and debugging.

---

## 📂 Project Anatomy
```text
com.revworkforce
├── 🎮 controller  # User interaction & console menus
├── 🧠 service     # Business logic & core validations
├── 💾 dao         # JDBC-driven database operations
├── 📦 model       # Entity representations (Employee, Leave)
├── 🛠 util        # Database connectivity & helpers
├── ⚠️ exception   # Custom error handling
└── 📊 validation  # Input integrity check layers
```

---

## 🎯 Development Roadmap
This foundational core is designed for seamless transition to modern web ecosystems:
- 🌐 **Web Integration**: Migration path to **Spring Boot** and RESTful APIs.
- 🔔 **In-App Messaging**: Real-time DB-driven alerts and announcements.
- 📈 **Talent Management**: Performance reviews, goal tracking, and OKRs.
- 🧩 **Scale-Out**: Future refactoring into a **Microservices** architecture.

---

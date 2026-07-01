# Hospital Appointment Management System

A Java-based console application that simulates the workflow of a hospital appointment management system. The project was developed using Object-Oriented Programming (OOP) principles to provide separate functionalities for administrators, doctors, and patients while ensuring data persistence through file handling.

The system demonstrates the implementation of user authentication, appointment scheduling, patient management, doctor assignment, reporting, and data validation within a structured object-oriented architecture.

---

## Table of Contents

* Project Overview
* System Roles
* Key Features
* Object-Oriented Design
* Project Structure
* Data Persistence
* Validation Rules
* Reports
* Technologies Used
* Getting Started
* Future Enhancements
* Author

---

## Project Overview

The Hospital Appointment Management System is designed to simplify hospital operations by organizing doctors, patients, and appointments within a single application.

The system stores all data using text files, allowing information to persist between program executions. It focuses on applying Java Object-Oriented Programming principles while maintaining a clean and modular architecture.

---

## System Roles

### Administrator

The administrator is responsible for managing the entire system and can:

* Add new doctors
* Register new patients
* Assign patients to doctors
* Create appointments
* View all doctors
* View all patients
* View all appointments
* Search for doctors by ID
* Search for patients by ID
* Generate system reports
* Save all system data

### Doctor

Doctors can:

* Log into the system
* View their profile
* View assigned patients
* View scheduled appointments
* Update appointment status (Confirmed, Completed, or Cancelled)

### Patient

Patients can:

* Log into the system
* View their profile
* View their assigned doctor
* View appointment history
* Book appointments
* Cancel appointments

---

## Key Features

* Multi-user authentication
* Doctor management
* Patient management
* Appointment scheduling
* Doctor assignment
* Appointment status management
* Search functionality
* Report generation
* Persistent data storage using text files
* Input validation
* Appointment conflict detection

---

## Object-Oriented Design

The project applies the core principles of Object-Oriented Programming.

### Abstraction

The abstract `User` class defines the common properties and behaviors shared by all system users.

### Inheritance

The `Admin`, `Doctor`, and `Patient` classes inherit from the `User` class.

### Encapsulation

All class attributes are protected through access modifiers and accessed using getter and setter methods.

### Polymorphism

Method overriding is used to provide different implementations of methods such as `displayInfo()` and `toFileString()`.

---

## Project Structure

```text
Hospital-Appointment-Management-System
│
├── .gitignore
├── Admin.java
├── Appointment.java
├── Doctor.java
├── FileManager.java
├── HospitalSystem.java
├── Main.java
├── Patient.java
├── README.md
├── User.java
│
├── appointments.txt
├── doctors.txt
├── patients.txt
└── users.txt
```

---

## Data Persistence

The application stores all records using text files.

| File             | Description            |
| ---------------- | ---------------------- |
| users.txt        | Administrator accounts |
| doctors.txt      | Doctor information     |
| patients.txt     | Patient information    |
| appointments.txt | Appointment records    |

When the application starts, previously saved data is automatically loaded into the system. All updates can be saved before exiting, ensuring data persistence across different sessions.

---

## Validation Rules

The system performs several validation checks to maintain data consistency.

* Prevents duplicate doctor IDs.
* Prevents duplicate patient IDs.
* Ensures a patient is assigned to a doctor before booking an appointment.
* Prevents scheduling multiple appointments for the same doctor at the same date and time.
* Prevents completed appointments from being cancelled.
* Prevents cancelled appointments from being marked as completed.
* Rejects empty appointment dates or times.
* Validates appointment status updates.

---

## Reports

The reporting module provides:

* Total number of doctors
* Total number of patients
* Total number of appointments
* Appointment statistics by status
* Top three doctors based on the number of completed and confirmed appointments

---

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections (ArrayList)
* File Handling
* Exception Handling
* NetBeans IDE

---

## Getting Started

### Prerequisites

* Java JDK 8 or later
* NetBeans IDE (or any Java IDE)

### Installation

1. Clone the repository.
2. Open the project in NetBeans.
3. Build the project.
4. Run `Main.java`.

### Default Administrator Credentials

| Username | Password |
| -------- | -------- |
| admin    | 123      |

---

## Future Enhancements

Possible improvements for future versions include:

* Developing a graphical user interface (GUI).
* Integrating a MySQL database.
* Encrypting user passwords.
* Editing existing appointments.
* Managing doctor availability.
* Sending email notifications.
* Searching doctors by specialization.
* Creating an analytics dashboard.
* Developing a REST API for future integration.

---

## Author

**Elham Mosaad**

Computer Science Student

Interested in Java Development, Object-Oriented Programming, Data Structures.

---



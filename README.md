# Hospital Patient Management System (HPMS)

[![Java Version](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Student](https://img.shields.io/badge/Author-Anshika%20Jain-green.svg)](https://github.com/anshika24bec10146-svg)
[![RegNo](https://img.shields.io/badge/Reg%20No-24BEC10146-lightgrey.svg)](https://github.com/anshika24bec10146-svg)
[![Department](https://img.shields.io/badge/Department-SEEE-blue.svg)](https://vitbhopal.ac.in/)

An object-oriented Java application designed to manage hospital patient records, doctor allocations, appointment scheduling, and diagnosis tracking. Developed as part of the **VITyarthi Flipped Course Project — Programming in Java** at **Vellore Institute of Technology (VIT), Bhopal**.

---

## 📌 Project Overview

The **Hospital Patient Management System** models the essential workflow of a hospital or outpatient department (OPD). It manages core entities—**Patients**, **Doctors**, and **Appointments**—using fundamental Object-Oriented Programming (OOP) concepts such as inheritance, encapsulation, polymorphism, and object association.

Each patient maintains their **Patient ID, Name, Age, Diagnosis, and Appointment Information**. The system avoids unnecessary static variables for object-specific data, ensuring proper object state management and lifecycle tracking.

---

## ✨ Features

- **Patient Registration & Management**:
  - Auto-generated unique patient IDs (`PAT-101`, `PAT-102`, etc.).
  - Tracks demographics, contact numbers, blood group, diagnosis, and patient type (OPD/Emergency/Inpatient).
- **Doctor Directory**:
  - Doctor profiles categorized by department, specialization, room number, and consultation fee.
- **Dynamic Doctor Assignment**:
  - Assigns or reassigns primary attending physicians to patients through object references.
- **Diagnosis Tracking**:
  - Update and record ongoing clinical diagnosis observations during patient visits.
- **Appointment Scheduling**:
  - Book consultations between specific patients and doctors with date, time slot, and clinical purpose.
  - Appointment lifecycle states (`Scheduled`, `Completed`, `Cancelled`).
- **Comprehensive Patient Summary Report**:
  - Generates a multi-relational report displaying patient demographics, attending doctor details, and scheduled appointments.
- **Hospital Audit & Analytics**:
  - High-level overview of total patients, active doctors, and pending appointments.
- **File Persistence**:
  - Reads and archives patient records and appointment details to local text files (`data/patients.txt`, `data/appointments.txt`).
- **Built-in Automated Testing**:
  - Self-contained validation test suite verifying all core methods and business logic without external dependencies.

---

## 🛠️ Technologies & Tools Used

- **Language**: Java (SE 8 or higher)
- **Course**: Programming in Java
- **Paradigm**: Object-Oriented Programming (OOP)
- **Core Concepts Applied**:
  - *Encapsulation*: Private member variables with getters/setters and validation.
  - *Inheritance*: `Person` abstract base class extended by `Patient` and `Doctor`.
  - *Polymorphism*: Overridden `displayInfo()` and `toString()` implementations.
  - *Object Association*: Association between `Patient`, `Doctor`, and `Appointment` objects.
  - *State Management*: Mutable diagnosis and appointment status transitions.
  - *File I/O*: `BufferedReader`, `BufferedWriter`, and `FileReader`/`FileWriter`.
- **Tools**: VS Code, IntelliJ IDEA / Eclipse, Git, Windows Command Prompt / PowerShell.

---

## 📁 Project Folder Structure

```
HospitalPatientManagementSystem/
│
├── src/
│   ├── model/
│   │   ├── Person.java          # Abstract base class (id, name, age, phone, gender)
│   │   ├── Patient.java         # Extends Person; maintains diagnosis, doctor, appointments
│   │   ├── Doctor.java          # Extends Person; specialization, fee, room
│   │   └── Appointment.java     # Consultation link between Patient and Doctor
│   │
│   ├── service/
│   │   ├── HospitalService.java # Business logic controller & data coordinator
│   │   └── FileStorage.java     # Local file persistence handler (I/O)
│   │
│   ├── test/
│   │   └── ValidationTest.java  # Self-contained automated test suite
│   │
│   └── Main.java                # Interactive console user interface (CLI)
│
├── data/
│   ├── patients.txt             # Saved patient records
│   └── appointments.txt         # Saved appointment records
│
├── statement.md                 # Project statement, scope & requirements (VITyarthi Sec 5.2)
├── PROJECT_REPORT.md            # Complete project documentation report for submission
├── .gitignore                   # Standard gitignore for Java binaries
└── README.md                    # Project documentation (this file)
```

---

## 🚀 Steps to Install & Run the Project

### Prerequisites
Make sure **Java JDK (version 8 or higher)** is installed on your computer. You can check your version using:
```bash
java -version
javac -version
```

### Option 1: Running with Included Script (Easiest)
In File Explorer, open `HospitalPatientManagementSystem` and double-click **`run.bat`**.

---

### Option 2: Running from Terminal / Command Prompt

1. **Clone or navigate to the project directory**:
   ```bash
   cd HospitalPatientManagementSystem
   ```

2. **Compile the Java source files**:
   ```bash
   # Create a bin folder for compiled class files
   mkdir bin

   # Compile all Java source files
   javac -d bin -sourcepath src src/model/*.java src/service/*.java src/test/*.java src/Main.java
   ```

3. **Run the Application**:
   ```bash
   java -cp bin Main
   ```

4. **Run the Automated Test Suite Directly**:
   ```bash
   java -cp bin test.ValidationTest
   ```

---

### Option 3: Running in VS Code / Eclipse / IntelliJ IDEA

1. Open the `HospitalPatientManagementSystem` folder in your IDE.
2. Ensure the `src` folder is marked as a **Source Folder**.
3. Open `src/Main.java`.
4. Click **Run** or press `F5` / `Ctrl + F5`.

---

## 🧪 Instructions for Testing

The project includes an automated test class (`ValidationTest.java`) that verifies:
1. Data initialization and model instantiation.
2. Patient registration with input validation.
3. Updating patient diagnosis.
4. Assigning doctors to patients (object association).
5. Scheduling appointments and verifying relationship links.
6. Generating patient summary reports.
7. File saving and persistence.

To execute the tests:
- Select option `12` from the Main Menu in the CLI, **OR**
- Run `java -cp bin test.ValidationTest` directly in terminal, **OR**
- Double-click **`test.bat`**.

---

## 💻 Sample Input and Output

### 1. Main Navigation Menu
```text
=================================================================
          HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)              
      Course: Programming in Java | Flipped Course Project       
      Author: Anshika Jain | Reg No: 24BEC10146 (SEEE)          
      Institution: Vellore Institute of Technology, Bhopal       
      GitHub: https://github.com/anshika24bec10146-svg          
=================================================================

================== MAIN NAVIGATION MENU ==================
 1.  Register New Patient
 2.  View All Registered Patients
 3.  View Doctor Directory
 4.  Assign Doctor to Patient (Object Association)
 5.  Update Patient Diagnosis
 6.  Schedule Doctor Appointment
 7.  View Scheduled Appointments
 8.  Generate Patient Consultation Summary Report
 9.  Generate Hospital Operational Audit Report
 10. Save All Records to Local File (Persistence)
 11. View Saved Records File Content
 12. Run Automated Verification / Test Cases
 0.  Exit System
==========================================================
Enter your choice [0-12]:
```

### 2. Patient Summary Report Output
```text
=======================================================
               PATIENT CONSULTATION SUMMARY            
=======================================================
 Patient ID      : PAT-101
 Patient Name    : Rohan Mehta
 Age / Gender    : 34 yrs / Male
 Blood Group     : B+
 Contact Phone   : 9123456780
 Patient Category: OPD
 Current Diagnosis: Hypertension & Stage 1 Arrhythmia
-------------------------------------------------------
 ASSIGNED DOCTOR INFORMATION:
 Doctor Name    : Dr. Rajesh Sharma
 Specialization : Cardiology
 Department     : Cardiovascular Sciences
 Room Number    : Room 102
 Consultation Fee: Rs. 800.00
-------------------------------------------------------
 APPOINTMENT HISTORY & SCHEDULE:
  [1] Appt ID: APT-501 | Date: 2025-04-12 | Slot: 10:00 AM
      Purpose: Cardiac Evaluation | Status: Scheduled
      Attending Doctor: Dr. Rajesh Sharma (Cardiology)
=======================================================
```

---

## 🌐 Submission & Author Details

- **Student Name**: Anshika Jain
- **Registration Number**: 24BEC10146
- **Department**: School of Electrical and Electronics Engineering (SEEE)
- **Institution**: Vellore Institute of Technology (VIT), Bhopal
- **Course**: Programming in Java
- **Evaluation**: Flipped Course Project
- **Submission Date**: 16 October 2026
- **GitHub Profile**: [https://github.com/anshika24bec10146-svg](https://github.com/anshika24bec10146-svg)
- **Repository URL**: [https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem](https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem)

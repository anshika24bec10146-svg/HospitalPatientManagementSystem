# PROJECT REPORT
# HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)

**Course Name**: Programming in Java  
**Evaluation**: Flipped Course Project  
**Student Name**: Anshika Jain  
**Registration Number**: 24BEC10146  
**Department**: School of Electrical and Electronics Engineering (SEEE)  
**Institution**: Vellore Institute of Technology (VIT), Bhopal  
**Submission Date**: 16 October 2026  
**GitHub Profile**: [https://github.com/anshika24bec10146-svg](https://github.com/anshika24bec10146-svg)  
**Project Repository**: [https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem](https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem)  

---

## 1. Cover Page

```text
========================================================================================
                          VELLORE INSTITUTE OF TECHNOLOGY, BHOPAL
                   School of Electrical and Electronics Engineering (SEEE)
                       
                                      PROJECT REPORT
                                            ON
                         HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)

                          Course Name   : Programming in Java
                          Academic Year : 2026-2027
                          Evaluation    : Flipped Course Project
                          
                          Submitted By  : Anshika Jain (Reg No: 24BEC10146)
                          Affiliation   : Vellore Institute of Technology, Bhopal
                          Submission    : 16 October 2026
========================================================================================
```

---

## 2. Introduction
Hospitals and modern outpatient clinics experience a steady influx of patients requiring consultation, diagnosis, and treatment scheduling daily. Managing patient registrations, doctor directories, medical diagnoses, and appointment allocations demands an organized, error-free system. 

The **Hospital Patient Management System (HPMS)** is an object-oriented software solution implemented in Java. The project accurately models real-world healthcare entities—**Patients**, **Doctors**, and **Appointments**—and their interactions. It eliminates manual overheads, avoids redundant data entries, and provides transparent visibility into patient histories and clinical consultations.

---

## 3. Problem Statement
In traditional hospital reception and clinic environments, patient records and doctor schedules are frequently maintained using physical registers or unlinked spreadsheets. This manual practice leads to significant operational challenges:
1. **Inefficient Cross-Referencing**: Inability to quickly link a patient's historical diagnoses with their attending doctor.
2. **Consultation & Scheduling Conflicts**: Misplaced appointments, overlapping consultation slots, and lack of doctor schedule visibility.
3. **Redundant Data Entry**: Repetitive recording of demographic information across multiple files.
4. **Delayed Summaries**: Time-consuming manual generation of patient consultation summaries during return visits.

HPMS resolves these challenges by providing a modular, object-oriented Java system that enforces encapsulation, class inheritance, defensive state validation, and clean object associations.

---

## 4. Functional Requirements
The system is divided into three primary functional modules:

### 4.1 Patient Registration & Record Management
- **Patient Registration**: Capture patient demographics (Name, Age, Gender, Phone, Blood Group, Patient Category) with automated ID allocation (`PAT-101`, `PAT-102`).
- **Diagnosis Tracking**: Dynamically update and record clinical diagnoses over multiple visits.
- **Patient Lookup & Registry Listing**: Retrieve detailed individual records or view the entire active patient directory.

### 4.2 Doctor & Department Management
- **Doctor Directory**: Maintain comprehensive doctor profiles containing ID, name, specialization, department, room/cabin number, and consultation fee.
- **Dynamic Doctor Assignment**: Bind an attending specialist to a patient through direct object association.

### 4.3 Appointment Scheduling & Summary Reporting
- **Appointment Booking**: Schedule date-and-time slots connecting patients with specific attending physicians.
- **Appointment Status Lifecycle**: Track consultation states (`Scheduled`, `Completed`, `Cancelled`).
- **Patient Consultation Summary**: Generate a consolidated multi-relational report showing patient vitals, attending doctor details, and all linked appointments.
- **Hospital Audit & Analytics**: High-level statistical report covering total patient count, active medical staff, and pending consultations.

---

## 5. Non-Functional Requirements
1. **Performance**: All lookup and object manipulation operations execute with near-instantaneous console response.
2. **Reliability & State Integrity**: Object-specific variables are strictly maintained per instance. Object-specific state is never leaked into static fields.
3. **Usability**: Interactive, numbered console menu with clear input prompts and input validation to prevent crashes from invalid inputs.
4. **Maintainability**: High cohesion and low coupling through clean package separation (`model`, `service`, `test`).
5. **Persistence**: Basic file-based archival (`patients.txt`, `appointments.txt`) ensuring data survival across program restarts.

---

## 6. System Architecture

The project follows a standard 3-Tier Layered Architecture:

```
+-------------------------------------------------------------+
|                   Presentation Layer (CLI)                  |
|                        Main.java                            |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                     Service / Logic Layer                   |
|           HospitalService.java  |  FileStorage.java          |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                      Domain Model Layer                     |
|  Person.java (Abstract)                                     |
|    ^                                                        |
|    +--- Patient.java <=====> Appointment.java               |
|    +--- Doctor.java  <=====> Appointment.java               |
+-------------------------------------------------------------+
                              |
                              v
+-------------------------------------------------------------+
|                     Storage / File Layer                    |
|        data/patients.txt   |   data/appointments.txt        |
+-------------------------------------------------------------+
```

---

## 7. Design Diagrams

### 7.1 Use Case Diagram

```text
               +------------------------------------------------+
               |        Hospital Patient Management System      |
               +------------------------------------------------+
               |                                                |
 (Front-Desk)  |   (1) Register Patient                         |
    [User] ----+---> (2) View Patients                          |
      |        |   (3) View Doctors                             |
      |        |   (4) Assign Doctor to Patient                 |
      +--------+---> (5) Schedule Appointment                   |
      |        |   (6) Update Patient Diagnosis                 |
      |        |   (7) Generate Patient Consultation Summary    |
      |        |   (8) Save & Load Records                      |
      |        |   (9) Run Automated Validation Tests           |
               |                                                |
               +------------------------------------------------+
```

### 7.2 Process Flow / Workflow Diagram

```text
 [Start Application]
          |
          v
 [Initialize HospitalService & Load Pre-Seeded Records]
          |
          v
 [Display Main Menu Options (1 to 12)]
          |
          +---> 1: Enter Patient Details -----> [Create Patient Object & Add to Registry]
          |
          +---> 4: Select Patient & Doctor ---> [patient.assignDoctor(doctor)]
          |
          +---> 5: Enter New Diagnosis -------> [patient.setDiagnosis(newDiagnosis)]
          |
          +---> 6: Input Appt Date & Slot ----> [Create Appointment & Link to Patient]
          |
          +---> 8: Request Patient ID --------> [Generate Multi-Relational Summary Report]
          |
          +---> 10: Trigger File Save --------> [Write Data to text files]
          |
          +---> 0: Exit Program --------------> [Auto-Save Session & Terminate]
```

### 7.3 Class Diagram

```text
+-----------------------------------+
|        <<abstract>> Person        |
+-----------------------------------+
| # id: String                      |
| # name: String                    |
| # age: int                        |
| # phone: String                   |
| # gender: String                  |
+-----------------------------------+
| + displayInfo(): void (abstract)  |
| + toString(): String              |
+-----------------------------------+
          ^                 ^
          |                 |
+-------------------+     +-------------------------+
|      Patient      |     |         Doctor          |
+-------------------+     +-------------------------+
| - diagnosis: Str  |     | - specialization: String|
| - bloodGroup: Str |     | - department: String    |
| - patientType: Str|     | - consultationFee: double|
| - assignedDoctor  |     | - roomNumber: String    |
| - appointments: []|     +-------------------------+
+-------------------+     | + displayInfo(): void   |
| + assignDoctor()  |     | + toString(): String    |
| + updateDiagnosis()     +-------------------------+
| + generateSummary()                  ^
+-------------------+                  |
          | 1                          | 1
          |                            |
          | *                          | *
+-----------------------------------------------+
|                  Appointment                  |
+-----------------------------------------------+
| - appointmentId: String                       |
| - patientId: String                           |
| - patientName: String                         |
| - doctor: Doctor                              |
| - date: String                                |
| - timeSlot: String                            |
| - status: String                              |
| - purpose: String                             |
+-----------------------------------------------+
| + completeAppointment(): void                 |
| + cancelAppointment(): void                   |
| + displayAppointmentDetails(): void           |
+-----------------------------------------------+
```

### 7.4 Sequence Diagram (Scheduling Appointment & Summary Generation)

```text
User                  Main               HospitalService          Patient            Appointment
 |                      |                       |                    |                    |
 |-- 1. Select Appt --->|                       |                    |                    |
 |   (patientId, docId) |                       |                    |                    |
 |                      |-- 2. scheduleAppt() ->|                    |                    |
 |                      |                       |-- 3. findPatient()->|                    |
 |                      |                       |-- 4. findDoctor() ->|                    |
 |                      |                       |-- 5. new Appt() --->|------------------->|
 |                      |                       |-- 6. addAppt() ---->|                    |
 |                      |                       |                    |                    |
 |-- 7. Request Summary>|                       |                    |                    |
 |                      |-- 8. getSummary() --->|                    |                    |
 |                      |                       |-- 9. generateSum()->|                    |
 |                      |                       |                    |-- 10. read appts ->|
 |                      |<-- 11. Formatted Rep -|                    |                    |
 |<-- 12. Display Rep --|                       |                    |                    |
```

### 7.5 Entity-Relationship & Storage Design (ER Diagram)

```text
+-----------------------+              +-----------------------+
|        PATIENT        |  1        *  |      APPOINTMENT      |
+-----------------------+--------------+-----------------------+
| PK  id (PAT-xxx)      |              | PK  appointmentId     |
|     name              |              | FK  patientId         |
|     age               |              |     patientName       |
|     gender            |              | FK  doctorId          |
|     phone             |              |     date              |
|     bloodGroup        |              |     timeSlot          |
|     diagnosis         |              |     status            |
|     patientType       |              |     purpose           |
| FK  assignedDoctorId  |              +-----------------------+
+-----------------------+                          *
            |                                      |
            | *                                    | 1
            |          +-----------------------+   |
            +--------> |        DOCTOR         | <-+
                     1 +-----------------------+
                       | PK  id (DOC-xx)       |
                       |     name              |
                       |     specialization    |
                       |     department        |
                       |     consultationFee   |
                       |     roomNumber        |
                       +-----------------------+
```

---

## 8. Design Decisions & Rationale
1. **Object-Oriented Inheritance (`Person -> Patient, Doctor`)**:
   - Both patients and doctors share personal identity fields (name, age, phone, gender). Creating an abstract base class `Person` eliminates code redundancy and adheres to the Don't Repeat Yourself (DRY) principle.
2. **Avoidance of Static Variables for Object State**:
   - As required by the course project guidelines, all patient attributes (ID, name, diagnosis, appointments) and doctor attributes are declared as instance variables. Static variables are only used for sequence counters (e.g., `patientCounter`) and constants.
3. **Direct Object References vs. Foreign Keys**:
   - Rather than merely storing string IDs for doctors inside `Patient`, `Patient` holds a direct `Doctor` object reference (`assignedDoctor`). This demonstrates true object association in OOP.
4. **Package Modularity**:
   - Separating classes into `model`, `service`, and `test` makes the codebase clean, readable, and easy to grade for faculty evaluators.

---

## 9. Implementation Details

| Class Name | Package | Primary Responsibility |
|---|---|---|
| `Person.java` | `model` | Abstract base class establishing inheritance and encapsulation. |
| `Doctor.java` | `model` | Represents hospital doctors with departmental specialization and fee attributes. |
| `Patient.java` | `model` | Encapsulates patient details, diagnosis updates, and summary generation. |
| `Appointment.java` | `model` | Represents scheduled consultations and tracks consultation status. |
| `HospitalService.java` | `service` | Business logic manager handling registrations, assignments, and queries. |
| `FileStorage.java` | `service` | Handles I/O operations to read and write records to local files. |
| `ValidationTest.java` | `test` | Automated testing suite with 7 test cases checking project integrity. |
| `Main.java` | Default | Interactive console UI handling user navigation and input validation. |

---

## 10. Results & Execution Output

### Sample 1: Patient Consultation Summary
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

### Sample 2: Hospital Audit Report
```text
=======================================================
            HOSPITAL OPERATIONAL AUDIT REPORT          
=======================================================
 Total Registered Patients     : 3
 Total Active Doctors          : 3
 Total Scheduled Appointments  : 2
-------------------------------------------------------
 Active Doctor Roster:
   * DOC-01 | Dr. Rajesh Sharma   | Cardiology       | Room 102
   * DOC-02 | Dr. Priya Patel     | Neurology        | Room 205
   * DOC-03 | Dr. Anand Verma     | General Medicine | Room 014
=======================================================
```

---

## 11. Testing Approach

Testing was conducted using both **Manual Exploratory Testing** and an **Automated Test Suite (`ValidationTest.java`)**.

### Automated Test Cases Summary

| Test Case | Module | Description | Expected Outcome | Status |
|---|---|---|---|---|
| TC-01 | Initialization | Seed default doctors and patients | Size >= 3 for both lists | **PASS** |
| TC-02 | Patient Module | Register patient with valid parameters | Stored & retrievable by ID | **PASS** |
| TC-03 | Patient Module | Update patient diagnosis | Updated string reflected in object | **PASS** |
| TC-04 | Doctor Module | Assign doctor to patient | Patient's `assignedDoctor` reference updated | **PASS** |
| TC-05 | Appointment | Schedule appointment linking Patient & Doctor | Appointment added to patient's list | **PASS** |
| TC-06 | Reporting | Generate patient summary report | Report contains patient, doctor & appt info | **PASS** |
| TC-07 | Persistence | Save records to text file | Files created and written successfully | **PASS** |

**Overall Result**: 19/19 Assertions Passed (100.0% Success Rate).

---

## 12. Challenges Faced
1. **Handling Scanner Line Breaking in Console**:
   - In Java, using `scanner.nextInt()` followed by `scanner.nextLine()` skips the newline token, causing empty input reads.
   - *Solution*: Parsed entire lines using `Integer.parseInt(scanner.nextLine().trim())` with `try-catch` blocks to prevent input skipping and crashes.
2. **Avoiding Static State Leakage**:
   - Ensuring each patient maintained their own distinct list of appointments rather than sharing a global list.
   - *Solution*: Instantiated an independent `ArrayList<Appointment>` within each `Patient` object constructor.
3. **Cross-Referencing Without Circular Dependency**:
   - Linking `Patient`, `Doctor`, and `Appointment` cleanly without creating recursive infinite loops in `toString()`.
   - *Solution*: Structured references hierarchically and designed tailored summary formatting methods.

---

## 13. Learnings & Key Takeaways
- Strengthened understanding of **Inheritance** and **Polymorphism** using abstract classes.
- Learned how to model real-world domain relationships (**Association and Aggregation**) in Java.
- Understood the importance of **encapsulation** and defensive state validation in class designs.
- Gained hands-on experience structuring a GitHub-ready repository with modular architecture and version control best practices.

---

## 14. Future Enhancements
1. **Database Connectivity (JDBC)**: Connect to MySQL or PostgreSQL database for relational persistent storage.
2. **Graphical User Interface (GUI)**: Implement a JavaFX or Swing desktop interface for user interaction.
3. **Prescription & Billing Module**: Add detailed medication tracking and automated PDF invoice generation.
4. **Role-Based Access Control (RBAC)**: Distinguish login credentials and privileges for Receptionists, Doctors, and Administrators.

---

## 15. References
1. Herbert Schildt, *Java: The Complete Reference*, 12th Edition, McGraw-Hill Education.
2. Oracle Java Documentation: [https://docs.oracle.com/javase/8/docs/api/](https://docs.oracle.com/javase/8/docs/api/)
3. VIT Course Curriculum & Lab Manual for Programming in Java.
4. GitHub Documentation & Markdown Guide: [https://docs.github.com/](https://docs.github.com/)

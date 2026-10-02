# PROJECT REPORT
## HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)

**Course**: Object-Oriented Programming (Java)  
**Evaluation**: Flipped Course Project / Experiment 13  
**Student Name**: Anshika  
**Registration Number**: 24BEC10146  
**Department**: School of Electronics Engineering (SENSE) / School of Computer Science & Engineering (SCSE)  
**Institution**: Vellore Institute of Technology (VIT)  
**GitHub Profile**: [https://github.com/anshika24bec10146-svg](https://github.com/anshika24bec10146-svg)  
**Project Repository**: [https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem](https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem)  
**Submission Date**: October 2026  

---

## 1. Cover Page
- **Project Title**: Hospital Patient Management System (HPMS)
- **Course Name**: Object-Oriented Programming with Java
- **Academic Year**: 2025-2026
- **Submitted By**: Anshika (Reg No: 24BEC10146)
- **Affiliation**: Vellore Institute of Technology, India

---

## 2. Introduction
Hospitals and healthcare centers handle a significant influx of patients on a daily basis. Coordinating patient registrations, medical appointments, attending doctor allocations, and diagnosis records requires an efficient and reliable software system.

The **Hospital Patient Management System (HPMS)** is an object-oriented software system developed in Java. The project models real-world hospital entities (`Patient`, `Doctor`, and `Appointment`) and their interactions. It eliminates manual overheads, maintains accurate records without redundant data, and provides clear visibility into patient care histories.

---

## 3. Problem Statement
In traditional clinic environments, patient records and doctor schedules are frequently recorded using paper-based registers or fragmented spreadsheets. This approach presents several challenges:
1. **Inefficient Data Cross-Referencing**: Inability to quickly link a patient's historical diagnoses with their attending specialist.
2. **Scheduling Conflicts**: Overlapping appointments or misplaced appointment slots.
3. **Redundant Data Entry**: Repeating patient demographics across multiple registers.
4. **Lack of Instant Summaries**: Delay in generating a consolidated patient consultation summary during follow-up visits.

To resolve these challenges, HPMS provides a structured, modular Java solution enforcing clean encapsulation, inheritance, and object associations.

---

## 4. Functional Requirements
The system implements three primary functional modules:

### 4.1 Patient Registration & Record Management
- **Registration**: Register new patients with unique auto-generated IDs (`PAT-101`, `PAT-102`), capturing name, age, contact, blood group, diagnosis, and category (OPD/Emergency/Inpatient).
- **Diagnosis Tracking**: Update patient diagnosis dynamically over subsequent consultations.
- **Search & View**: Search patients by unique ID and display detailed patient vitals.

### 4.2 Doctor & Department Management
- **Directory**: Maintain doctor profiles containing ID, name, specialization, department, consultation fee, and room number.
- **Doctor Assignment**: Assign a primary attending physician to a patient through object referencing.

### 4.3 Appointment Scheduling & Reporting (Experiment 13 Output)
- **Appointment Booking**: Schedule appointments linking a specific patient and doctor with date, time slot, and clinical purpose.
- **Patient Summary Report**: Generate a unified consultation summary displaying patient details, assigned doctor details, and scheduled appointments.
- **Hospital Audit Report**: Generate aggregate metrics on total patients, active doctors, and scheduled consultations.

---

## 5. Non-Functional Requirements
1. **Performance**: All lookup and object management operations execute in near-constant time (\(O(1)\) to \(O(N)\) within internal collections) with instantaneous console response.
2. **Reliability & Data Integrity**: Object-specific variables are strictly managed per instance (no static variable leakage for patient-specific state).
3. **Usability**: Interactive, numbered console menu with clear input prompts and input validation to prevent crashes from invalid types.
4. **Maintainability**: High cohesion and low coupling through distinct packages (`model`, `service`, `test`).
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
      |        |   (7) Generate Patient Summary (Exp 13)        |
      |        |   (8) Save & Load Data Records                 |
      |        |   (9) Run Automated Validation Tests           |
               |                                                |
               +------------------------------------------------+
```

### 7.2 Process Flow / Workflow Diagram

```text
 [Start]
    |
    v
 [Initialize HospitalService & Load Seed Data]
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
    +---> 6: Input Appt Date & Slot ----> [Create Appointment Object & Link to Patient]
    |
    +---> 8: Request Patient ID --------> [Generate Multi-Relational Summary Report]
    |
    +---> 10: Trigger File Save --------> [Write Data to text files]
    |
    +---> 0: Exit Program --------------> [Save Session & Terminate]
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

### 7.4 Sequence Diagram (Scheduling Appointment & Generating Summary)

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

---

## 8. Design Decisions & Rationale
1. **Object-Oriented Inheritance (`Person -> Patient, Doctor`)**:
   - Both patients and doctors share personal identity fields (name, age, phone, gender). Creating an abstract base class `Person` eliminates code redundancy and adheres to the Don't Repeat Yourself (DRY) principle.
2. **Avoidance of Static Variables for Object State**:
   - As explicitly required by Experiment 13 guidelines, all patient attributes (ID, name, diagnosis, appointments) and doctor attributes are declared as instance variables. Static variables are only used for sequence counters (e.g., `patientCounter`) and constants.
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

### Sample 1: Patient Consultation Summary (Experiment 13 Expected Output)
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

**Overall Result**: 7/7 Tests Passed (100.0% Success Rate).

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
3. VIT Course Curriculum & Lab Manual for Object-Oriented Programming (Java).
4. GitHub Documentation & Markdown Guide: [https://docs.github.com/](https://docs.github.com/)

# Project Statement - Hospital Patient Management System (HPMS)

**Course**: Programming in Java  
**Evaluation**: Flipped Course Project  
**Student Name**: Anshika Jain  
**Registration Number**: 24BEC10146  
**Department**: School of Electrical and Electronics Engineering (SEEE)  
**Institution**: Vellore Institute of Technology (VIT), Bhopal  
**Submission Date**: 16 October 2026  
**GitHub Profile**: [anshika24bec10146-svg](https://github.com/anshika24bec10146-svg)  
**Repository**: [https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem](https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem)

---

## 1. Problem Statement
In conventional clinics and hospital outpatient departments (OPDs), patient registration, doctor assignment, diagnosis tracking, and appointment scheduling are often handled either through fragmented paper registers or disjointed spreadsheet entries. This manual approach leads to significant operational bottlenecks, including:
- Inaccurate cross-referencing between patient clinical history and assigned doctors.
- Missing or delayed consultation summaries during follow-up visits.
- Lack of centralized visibility into doctor availability, consultation charges, and patient appointments.
- High risk of data inconsistency and redundant administrative paperwork.

There is a clear need for an organized, modular, object-oriented software system that structures hospital entities (Patients, Doctors, Appointments) and models their real-world interactions while maintaining clean data encapsulation.

---

## 2. Scope of the Project
The **Hospital Patient Management System (HPMS)** is a desktop-based console application developed in Java. It models core OPD hospital operations with high fidelity:

- **In-Scope**:
  - Managing distinct hospital entities (`Person`, `Patient`, `Doctor`, `Appointment`) using core Object-Oriented Programming (OOP) principles.
  - Registering new patients and capturing demographic, contact, and medical indicators (e.g., blood group, diagnosis, category).
  - Maintaining an active directory of medical doctors categorized by specialization and department.
  - Dynamic assignment of attending doctors to patients without data duplication.
  - In-place diagnosis updates reflecting the evolving condition of a patient.
  - Scheduling, status tracking, and logging of doctor-patient consultation appointments.
  - Generating formatted single-patient consultation summaries and hospital-wide operational audit reports.
  - Local file archiving for session data persistence.
  - Self-contained automated validation test suites.

- **Out-of-Scope (Future Enhancements)**:
  - Networked multi-tier client-server architecture.
  - External Relational Database Management System (RDBMS) integration (e.g., MySQL / PostgreSQL).
  - Online payment gateway processing.

---

## 3. Target Users
1. **Hospital Front-Desk Receptionists / OPD Registrars**: To register incoming patients, verify patient identity, and schedule appointments with doctors.
2. **Attending Doctors & Medical Specialists**: To inspect assigned patient lists, review previous appointments, and update clinical diagnoses after consultations.
3. **Hospital Administrators / Clinic Coordinators**: To monitor doctor workloads, audit patient queues, and generate consolidated hospital activity reports.

---

## 4. High-Level Features
- **Patient Registration & Profile Management**: Add new patient records with automatic ID generation, age validation, and medical category categorization.
- **Doctor Directory & Department Management**: View attending doctors with credentials, cabin/room numbers, and consultation fees.
- **Dynamic Doctor Assignment**: Bind a patient to a responsible doctor with object association rather than static references.
- **Diagnosis Tracking**: Modify and log updated patient diagnoses over multiple visits.
- **Appointment Scheduling System**: Schedule date-and-time slots for consultations with status lifecycle management (`Scheduled`, `Completed`, `Cancelled`).
- **Comprehensive Patient Summary**: Multi-relational summary showing patient vitals, attending doctor details, and all linked appointments.
- **Hospital Analytics & Operational Audit**: Instant overview of patient load, doctor distribution, and appointment volume.
- **Data Persistence**: Archive records into human-readable text files (`patients.txt` and `appointments.txt`) for persistent storage across sessions.
- **Automated Validation Suite**: In-built test cases asserting object relationships, state changes, and business rules.

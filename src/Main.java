import model.Appointment;
import model.Doctor;
import model.Patient;
import service.FileStorage;
import service.HospitalService;
import test.ValidationTest;

import java.util.List;
import java.util.Scanner;

/**
 * Main application entry point for the Hospital Patient Management System.
 * Provides an interactive console interface for users to perform patient registration,
 * doctor assignments, appointment booking, diagnosis updates, and summary generation.
 * 
 * Course: Object-Oriented Programming (Java)
 * Project: VITyarthi Course Evaluation / Experiment 13
 * Student: Anshika
 * Registration No: 24BEC10146
 * GitHub: https://github.com/anshika24bec10146-svg
 */
public class Main {
    private static HospitalService hospitalService = new HospitalService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        printWelcomeHeader();

        while (!exit) {
            displayMenu();
            System.out.print("Enter your choice [0-12]: ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(">> Invalid input! Please enter a number between 0 and 12.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    handleRegisterPatient();
                    break;
                case 2:
                    handleViewAllPatients();
                    break;
                case 3:
                    handleViewAllDoctors();
                    break;
                case 4:
                    handleAssignDoctor();
                    break;
                case 5:
                    handleUpdateDiagnosis();
                    break;
                case 6:
                    handleScheduleAppointment();
                    break;
                case 7:
                    handleViewAllAppointments();
                    break;
                case 8:
                    handleGeneratePatientSummary();
                    break;
                case 9:
                    hospitalService.printHospitalAnalyticsReport();
                    break;
                case 10:
                    handleSaveData();
                    break;
                case 11:
                    FileStorage.displayFileRecords();
                    break;
                case 12:
                    System.out.println("\n--- Running Automated Verification Suite ---");
                    ValidationTest.runAllTests();
                    break;
                case 0:
                    System.out.println("\nThank you for using the Hospital Patient Management System.");
                    System.out.println("Saving session records before exit...");
                    FileStorage.savePatients(hospitalService.getAllPatients());
                    FileStorage.saveAppointments(hospitalService.getAllAppointments());
                    System.out.println("Good bye!\n");
                    exit = true;
                    break;
                default:
                    System.out.println(">> Invalid choice! Please select an option from 0 to 12.\n");
            }
        }
    }

    private static void printWelcomeHeader() {
        System.out.println("=================================================================");
        System.out.println("          HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)              ");
        System.out.println("       VIT Course Project - Object-Oriented Programming (Java)   ");
        System.out.println("       Author: Anshika | Reg No: 24BEC10146                      ");
        System.out.println("       GitHub: https://github.com/anshika24bec10146-svg          ");
        System.out.println("=================================================================");
    }

    private static void displayMenu() {
        System.out.println("\n================== MAIN NAVIGATION MENU ==================");
        System.out.println(" 1.  Register New Patient");
        System.out.println(" 2.  View All Registered Patients");
        System.out.println(" 3.  View Doctor Directory");
        System.out.println(" 4.  Assign Doctor to Patient (Object Association)");
        System.out.println(" 5.  Update Patient Diagnosis");
        System.out.println(" 6.  Schedule Doctor Appointment");
        System.out.println(" 7.  View Scheduled Appointments");
        System.out.println(" 8.  Generate Patient Consultation Summary Report (Exp 13)");
        System.out.println(" 9.  Generate Hospital Operational Audit Report");
        System.out.println(" 10. Save All Records to Local File (Persistence)");
        System.out.println(" 11. View Saved Records File Content");
        System.out.println(" 12. Run Automated Verification / Test Cases");
        System.out.println(" 0.  Exit System");
        System.out.println("==========================================================");
    }

    // 1. Register Patient
    private static void handleRegisterPatient() {
        System.out.println("\n--- Patient Registration ---");
        String id = hospitalService.generateNextPatientId();
        System.out.println("Generated Patient ID : " + id);

        System.out.print("Enter Patient Full Name   : ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Age                 : ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format. Registration cancelled.");
            return;
        }

        System.out.print("Enter Gender (Male/Female/Other): ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter Contact Phone       : ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter Blood Group (e.g. A+, O+, B+): ");
        String bloodGroup = scanner.nextLine().trim();

        System.out.print("Enter Initial Diagnosis   : ");
        String diagnosis = scanner.nextLine().trim();

        System.out.print("Enter Patient Category (OPD/Emergency/Inpatient) [Default: OPD]: ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = "OPD";

        Patient patient = new Patient(id, name, age, phone, gender, bloodGroup, diagnosis, category);
        
        // Optional quick doctor assignment during registration
        System.out.print("Would you like to assign an attending doctor now? (y/n): ");
        String assignChoice = scanner.nextLine().trim();
        if (assignChoice.equalsIgnoreCase("y")) {
            System.out.println("\nAvailable Doctors:");
            for (Doctor d : hospitalService.getAllDoctors()) {
                System.out.println("  [" + d.getId() + "] Dr. " + d.getName() + " - " + d.getSpecialization());
            }
            System.out.print("Enter Doctor ID: ");
            String docId = scanner.nextLine().trim();
            Doctor doctor = hospitalService.findDoctorById(docId);
            if (doctor != null) {
                patient.assignDoctor(doctor);
                System.out.println(">> Dr. " + doctor.getName() + " successfully assigned!");
            } else {
                System.out.println(">> Doctor ID not found. Patient registered without assigned doctor.");
            }
        }

        hospitalService.registerPatient(patient);
        System.out.println("\n>> Success! Patient " + name + " (ID: " + id + ") registered successfully.");
    }

    // 2. View All Patients
    private static void handleViewAllPatients() {
        List<Patient> patients = hospitalService.getAllPatients();
        System.out.println("\n----------------- REGISTERED PATIENTS (" + patients.size() + ") -----------------");
        if (patients.isEmpty()) {
            System.out.println("No patients currently in registry.");
            return;
        }
        for (Patient p : patients) {
            p.displayInfo();
        }
    }

    // 3. View All Doctors
    private static void handleViewAllDoctors() {
        List<Doctor> doctors = hospitalService.getAllDoctors();
        System.out.println("\n----------------- DOCTOR DIRECTORY (" + doctors.size() + ") -----------------");
        for (Doctor d : doctors) {
            d.displayInfo();
        }
    }

    // 4. Assign Doctor to Patient
    private static void handleAssignDoctor() {
        System.out.println("\n--- Assign Doctor to Patient ---");
        System.out.print("Enter Patient ID (e.g. PAT-101): ");
        String patientId = scanner.nextLine().trim();
        Patient p = hospitalService.findPatientById(patientId);

        if (p == null) {
            System.out.println(">> Patient with ID '" + patientId + "' not found.");
            return;
        }

        System.out.println("Selected Patient: " + p.getName() + " (Current Doctor: " 
                + (p.getAssignedDoctor() != null ? p.getAssignedDoctor().getName() : "None") + ")");

        System.out.println("\nAvailable Doctors:");
        for (Doctor d : hospitalService.getAllDoctors()) {
            System.out.println("  [" + d.getId() + "] Dr. " + d.getName() + " (" + d.getSpecialization() + " - " + d.getDepartment() + ")");
        }

        System.out.print("Enter Doctor ID to assign (e.g. DOC-01): ");
        String docId = scanner.nextLine().trim();

        if (hospitalService.assignDoctorToPatient(patientId, docId)) {
            Doctor doc = hospitalService.findDoctorById(docId);
            System.out.println(">> Success! Dr. " + doc.getName() + " is now assigned to patient " + p.getName() + ".");
        } else {
            System.out.println(">> Failed! Invalid Doctor ID provided.");
        }
    }

    // 5. Update Diagnosis
    private static void handleUpdateDiagnosis() {
        System.out.println("\n--- Update Patient Diagnosis ---");
        System.out.print("Enter Patient ID (e.g. PAT-101): ");
        String patientId = scanner.nextLine().trim();
        Patient p = hospitalService.findPatientById(patientId);

        if (p == null) {
            System.out.println(">> Patient with ID '" + patientId + "' not found.");
            return;
        }

        System.out.println("Selected Patient : " + p.getName());
        System.out.println("Current Diagnosis: " + p.getDiagnosis());
        System.out.print("Enter New / Updated Diagnosis: ");
        String newDiag = scanner.nextLine().trim();

        if (hospitalService.updatePatientDiagnosis(patientId, newDiag)) {
            System.out.println(">> Success! Diagnosis updated for " + p.getName() + ".");
        } else {
            System.out.println(">> Error updating diagnosis.");
        }
    }

    // 6. Schedule Appointment
    private static void handleScheduleAppointment() {
        System.out.println("\n--- Schedule Doctor Appointment ---");
        System.out.print("Enter Patient ID (e.g. PAT-101): ");
        String patientId = scanner.nextLine().trim();
        Patient p = hospitalService.findPatientById(patientId);

        if (p == null) {
            System.out.println(">> Patient ID not found.");
            return;
        }

        System.out.println("\nSelect Doctor for Appointment:");
        for (Doctor d : hospitalService.getAllDoctors()) {
            System.out.println("  [" + d.getId() + "] Dr. " + d.getName() + " (" + d.getSpecialization() + ", Fee: Rs. " + d.getConsultationFee() + ")");
        }
        System.out.print("Enter Doctor ID: ");
        String docId = scanner.nextLine().trim();
        Doctor d = hospitalService.findDoctorById(docId);

        if (d == null) {
            System.out.println(">> Doctor ID not found.");
            return;
        }

        System.out.print("Enter Appointment Date (YYYY-MM-DD) [e.g. 2025-04-15]: ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter Time Slot (e.g. 10:30 AM, 03:00 PM): ");
        String slot = scanner.nextLine().trim();

        System.out.print("Enter Purpose / Symptoms: ");
        String purpose = scanner.nextLine().trim();

        Appointment appt = hospitalService.scheduleAppointment(patientId, docId, date, slot, purpose);
        if (appt != null) {
            System.out.println("\n>> Appointment successfully scheduled!");
            System.out.println("   Booking Reference: " + appt.getAppointmentId());
            System.out.println("   Patient          : " + p.getName());
            System.out.println("   Doctor           : Dr. " + d.getName() + " (" + d.getSpecialization() + ")");
            System.out.println("   Date & Time      : " + date + " at " + slot);
            System.out.println("   Consultation Fee : Rs. " + d.getConsultationFee());
        } else {
            System.out.println(">> Failed to schedule appointment.");
        }
    }

    // 7. View All Appointments
    private static void handleViewAllAppointments() {
        List<Appointment> appts = hospitalService.getAllAppointments();
        System.out.println("\n---------------- SCHEDULED APPOINTMENTS (" + appts.size() + ") ----------------");
        if (appts.isEmpty()) {
            System.out.println("No appointments scheduled currently.");
            return;
        }
        for (Appointment a : appts) {
            a.displayAppointmentDetails();
        }
    }

    // 8. Generate Patient Summary
    private static void handleGeneratePatientSummary() {
        System.out.println("\n--- Generate Patient Summary Report (Experiment 13) ---");
        System.out.print("Enter Patient ID (e.g. PAT-101): ");
        String patientId = scanner.nextLine().trim();

        String summary = hospitalService.getPatientSummary(patientId);
        System.out.println(summary);
    }

    // 10. Save Data to File
    private static void handleSaveData() {
        System.out.println("\nSaving records to local data directory...");
        boolean pOk = FileStorage.savePatients(hospitalService.getAllPatients());
        boolean aOk = FileStorage.saveAppointments(hospitalService.getAllAppointments());

        if (pOk && aOk) {
            System.out.println(">> Success! Patient and appointment archives written to 'data/' folder.");
        } else {
            System.out.println(">> Warning: Some files could not be saved.");
        }
    }
}

package service;

import model.Patient;
import model.Doctor;
import model.Appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * FileStorage class provides basic text file persistence for hospital records.
 * Demonstrates Java file handling (BufferedReader, BufferedWriter, File)
 * and error handling strategies.
 * 
 * @author Anshika (Reg No: 24BEC10146)
 */
public class FileStorage {
    private static final String DATA_DIR = "data";
    private static final String PATIENTS_FILE = "data/patients.txt";
    private static final String APPOINTMENTS_FILE = "data/appointments.txt";

    // Ensures the data directory exists
    public static void initDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Saves the current list of patients to a plain text file.
     */
    public static boolean savePatients(List<Patient> patients) {
        initDataDirectory();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENTS_FILE))) {
            writer.write("# PATIENT RECORDS ARCHIVE\n");
            writer.write("# Format: ID,Name,Age,Gender,Phone,BloodGroup,Diagnosis,Type,AssignedDoctor\n");
            for (Patient p : patients) {
                String doctorName = (p.getAssignedDoctor() != null) ? p.getAssignedDoctor().getName() : "None";
                String line = String.format("%s,%s,%d,%s,%s,%s,%s,%s,%s",
                        p.getId(),
                        p.getName(),
                        p.getAge(),
                        p.getGender(),
                        p.getPhone(),
                        p.getBloodGroup(),
                        p.getDiagnosis(),
                        p.getPatientType(),
                        doctorName
                );
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving patient data to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Saves scheduled appointments to a plain text file.
     */
    public static boolean saveAppointments(List<Appointment> appointments) {
        initDataDirectory();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENTS_FILE))) {
            writer.write("# APPOINTMENT RECORDS ARCHIVE\n");
            writer.write("# Format: ApptID,PatientID,PatientName,DoctorName,Date,Slot,Status,Purpose\n");
            for (Appointment a : appointments) {
                String docName = (a.getDoctor() != null) ? a.getDoctor().getName() : "Unassigned";
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        a.getAppointmentId(),
                        a.getPatientId(),
                        a.getPatientName(),
                        docName,
                        a.getDate(),
                        a.getTimeSlot(),
                        a.getStatus(),
                        a.getPurpose()
                );
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving appointment data to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Displays raw saved data directly from the text archive.
     */
    public static void displayFileRecords() {
        File file = new File(PATIENTS_FILE);
        if (!file.exists()) {
            System.out.println("No saved records file found yet. Save data first!");
            return;
        }

        System.out.println("\n--- Raw Data from " + PATIENTS_FILE + " ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-------------------------------------------\n");
        } catch (IOException e) {
            System.err.println("Error reading archive file: " + e.getMessage());
        }
    }
}

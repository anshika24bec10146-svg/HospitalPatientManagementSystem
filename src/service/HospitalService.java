package service;

import model.Appointment;
import model.Doctor;
import model.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * HospitalService acts as the core controller / business logic manager
 * for the Hospital Patient Management System.
 * 
 * Manages collections of Patients, Doctors, and Appointments,
 * facilitating object interactions, diagnosis updates, assignments,
 * and comprehensive summary reporting.
 * 
 * @author Anshika (Reg No: 24BEC10146)
 */
public class HospitalService {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    // Sequence counters for ID generation
    private int patientCounter = 100;
    private int doctorCounter = 10;
    private int appointmentCounter = 500;

    public HospitalService() {
        this.patients = new ArrayList<Patient>();
        this.doctors = new ArrayList<Doctor>();
        this.appointments = new ArrayList<Appointment>();
        seedInitialHospitalData();
    }

    /**
     * Seeds initial doctor and patient objects so the user can immediately test
     * assignments, appointments, and report generation without tedious manual typing.
     */
    private void seedInitialHospitalData() {
        // Register standard hospital doctors
        Doctor doc1 = new Doctor("DOC-01", "Rajesh Sharma", 48, "9876543210", "Male", 
                "Cardiology", "Cardiovascular Sciences", 800.0, "Room 102");
        Doctor doc2 = new Doctor("DOC-02", "Priya Patel", 41, "9876543211", "Female", 
                "Neurology", "Neurosciences", 950.0, "Room 205");
        Doctor doc3 = new Doctor("DOC-03", "Anand Verma", 36, "9876543212", "Male", 
                "General Medicine", "Internal Medicine", 500.0, "Room 014");

        doctors.add(doc1);
        doctors.add(doc2);
        doctors.add(doc3);

        // Register initial patients
        Patient p1 = new Patient("PAT-101", "Rohan Mehta", 34, "9123456780", "Male", 
                "B+", "Hypertension & Stage 1 Arrhythmia", "OPD");
        Patient p2 = new Patient("PAT-102", "Sneha Gupta", 28, "9123456781", "Female", 
                "O+", "Chronic Migraine", "OPD");
        Patient p3 = new Patient("PAT-103", "Amit Kumar", 52, "9123456782", "Male", 
                "A+", "Type 2 Diabetes & Routine Checkup", "Inpatient");

        // Object interaction: Assign initial doctors to patients
        p1.assignDoctor(doc1);
        p2.assignDoctor(doc2);
        p3.assignDoctor(doc3);

        patients.add(p1);
        patients.add(p2);
        patients.add(p3);

        // Schedule initial appointments
        Appointment appt1 = new Appointment("APT-501", p1.getId(), p1.getName(), doc1, "2025-04-12", "10:00 AM", "Cardiac Evaluation");
        Appointment appt2 = new Appointment("APT-502", p2.getId(), p2.getName(), doc2, "2025-04-14", "02:30 PM", "Neurological Review");

        appointments.add(appt1);
        appointments.add(appt2);

        p1.addAppointment(appt1);
        p2.addAppointment(appt2);

        // Update ID counter offsets
        this.patientCounter = 104;
        this.doctorCounter = 4;
        this.appointmentCounter = 503;
    }

    // ----------------- PATIENT OPERATIONS -----------------

    public String generateNextPatientId() {
        return "PAT-" + (patientCounter++);
    }

    public boolean registerPatient(Patient patient) {
        if (patient == null) return false;
        patients.add(patient);
        return true;
    }

    public Patient findPatientById(String id) {
        if (id == null) return null;
        for (Patient p : patients) {
            if (p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    /**
     * Updates diagnosis for an existing patient.
     * Complies with Experiment 13 requirement.
     */
    public boolean updatePatientDiagnosis(String patientId, String newDiagnosis) {
        Patient patient = findPatientById(patientId);
        if (patient != null) {
            patient.setDiagnosis(newDiagnosis);
            return true;
        }
        return false;
    }

    /**
     * Assigns a doctor to a patient.
     * Demonstrates object interaction between Patient and Doctor.
     */
    public boolean assignDoctorToPatient(String patientId, String doctorId) {
        Patient patient = findPatientById(patientId);
        Doctor doctor = findDoctorById(doctorId);

        if (patient != null && doctor != null) {
            patient.assignDoctor(doctor);
            return true;
        }
        return false;
    }

    // ----------------- DOCTOR OPERATIONS -----------------

    public String generateNextDoctorId() {
        return String.format("DOC-%02d", doctorCounter++);
    }

    public boolean registerDoctor(Doctor doctor) {
        if (doctor == null) return false;
        doctors.add(doctor);
        return true;
    }

    public Doctor findDoctorById(String id) {
        if (id == null) return null;
        for (Doctor d : doctors) {
            if (d.getId().equalsIgnoreCase(id.trim())) {
                return d;
            }
        }
        return null;
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // ----------------- APPOINTMENT OPERATIONS -----------------

    public String generateNextAppointmentId() {
        return "APT-" + (appointmentCounter++);
    }

    /**
     * Schedules a new appointment connecting a Patient with a Doctor.
     */
    public Appointment scheduleAppointment(String patientId, String doctorId, 
                                           String date, String timeSlot, String purpose) {
        Patient patient = findPatientById(patientId);
        Doctor doctor = findDoctorById(doctorId);

        if (patient == null || doctor == null) {
            return null;
        }

        String apptId = generateNextAppointmentId();
        Appointment appointment = new Appointment(apptId, patient.getId(), patient.getName(), doctor, date, timeSlot, purpose);
        
        appointments.add(appointment);
        patient.addAppointment(appointment);

        // If the patient has no assigned doctor yet, assign this doctor
        if (patient.getAssignedDoctor() == null) {
            patient.assignDoctor(doctor);
        }

        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // ----------------- REPORTING & SUMMARY -----------------

    /**
     * Generates a single patient consultation summary report.
     * Complies with Experiment 13 requirement:
     * "generating a patient summary... report showing relationships between patients, doctors and appointments."
     */
    public String getPatientSummary(String patientId) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            return "Error: Patient with ID '" + patientId + "' not found in system.";
        }
        return patient.generateSummary();
    }

    /**
     * Generates an overview summary of the entire hospital operations.
     */
    public void printHospitalAnalyticsReport() {
        System.out.println("\n=======================================================");
        System.out.println("            HOSPITAL OPERATIONAL AUDIT REPORT          ");
        System.out.println("=======================================================");
        System.out.println(" Total Registered Patients     : " + patients.size());
        System.out.println(" Total Active Doctors          : " + doctors.size());
        System.out.println(" Total Scheduled Appointments  : " + appointments.size());
        System.out.println("-------------------------------------------------------");
        System.out.println(" Active Doctor Roster:");
        for (Doctor d : doctors) {
            System.out.printf("   * %s | Dr. %-15s | %-16s | %s\n", 
                    d.getId(), d.getName(), d.getSpecialization(), d.getRoomNumber());
        }
        System.out.println("-------------------------------------------------------");
        System.out.println(" Patient Registry & Assignment Status:");
        for (Patient p : patients) {
            String docStr = (p.getAssignedDoctor() != null) ? "Dr. " + p.getAssignedDoctor().getName() : "Unassigned";
            System.out.printf("   * %s | %-15s | Age: %-2d | Diag: %-25s | Assigned: %s\n",
                    p.getId(), p.getName(), p.getAge(), p.getDiagnosis(), docStr);
        }
        System.out.println("=======================================================\n");
    }
}

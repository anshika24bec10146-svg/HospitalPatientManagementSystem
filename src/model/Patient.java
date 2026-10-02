package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patient class representing patients in the hospital.
 * Inherits common person attributes from Person.java.
 * Maintains diagnosis, assigned doctor reference, and appointment history.
 * 
 * Complies with Experiment 13 requirement:
 * - Patient ID, Name, Age, Diagnosis, Appointment information
 * - Object interaction with Doctor and Appointment classes
 * - No object-specific information stored in static variables
 * 
 * @author Anshika (Reg No: 24BEC10146)
 */
public class Patient extends Person {
    private String diagnosis;
    private String bloodGroup;
    private String patientType; // e.g. "OPD", "Emergency", "Inpatient"
    private Doctor assignedDoctor; // Object association
    private List<Appointment> appointments; // List of appointments for this patient

    // Default constructor
    public Patient() {
        super();
        this.appointments = new ArrayList<Appointment>();
        this.diagnosis = "Pending Examination";
        this.patientType = "OPD";
    }

    // Parameterized constructor
    public Patient(String id, String name, int age, String phone, String gender, 
                   String bloodGroup, String diagnosis, String patientType) {
        super(id, name, age, phone, gender);
        this.bloodGroup = bloodGroup;
        this.diagnosis = (diagnosis != null && !diagnosis.trim().isEmpty()) ? diagnosis : "Pending Examination";
        this.patientType = (patientType != null && !patientType.trim().isEmpty()) ? patientType : "OPD";
        this.appointments = new ArrayList<Appointment>();
        this.assignedDoctor = null;
    }

    // Getters and Setters
    public String getDiagnosis() {
        return diagnosis;
    }

    // Operation to update diagnosis
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    // Operation to assign a doctor (Object interaction)
    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Operation to add an appointment
    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            this.appointments.add(appointment);
        }
    }

    // Overriding abstract method
    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Patient Details:");
        System.out.println("Patient ID      : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Age / Gender    : " + age + " / " + gender);
        System.out.println("Blood Group     : " + bloodGroup);
        System.out.println("Contact Phone   : " + phone);
        System.out.println("Current Status  : " + patientType);
        System.out.println("Diagnosis       : " + diagnosis);
        System.out.println("Assigned Doctor : " + (assignedDoctor != null ? assignedDoctor.getName() + " (" + assignedDoctor.getSpecialization() + ")" : "None"));
        System.out.println("Total Appts     : " + appointments.size());
        System.out.println("--------------------------------------------------");
    }

    /**
     * Generates a patient summary report displaying the relationships
     * between this patient, the assigned doctor, and scheduled appointments.
     */
    public String generateSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=======================================================\n");
        sb.append("               PATIENT CONSULTATION SUMMARY            \n");
        sb.append("=======================================================\n");
        sb.append(String.format(" Patient ID      : %s\n", id));
        sb.append(String.format(" Patient Name    : %s\n", name));
        sb.append(String.format(" Age / Gender    : %d yrs / %s\n", age, gender));
        sb.append(String.format(" Blood Group     : %s\n", bloodGroup));
        sb.append(String.format(" Contact Phone   : %s\n", phone));
        sb.append(String.format(" Patient Category: %s\n", patientType));
        sb.append(String.format(" Current Diagnosis: %s\n", diagnosis));
        sb.append("-------------------------------------------------------\n");
        sb.append(" ASSIGNED DOCTOR INFORMATION:\n");
        if (assignedDoctor != null) {
            sb.append(String.format(" Doctor Name    : Dr. %s\n", assignedDoctor.getName()));
            sb.append(String.format(" Specialization : %s\n", assignedDoctor.getSpecialization()));
            sb.append(String.format(" Department     : %s\n", assignedDoctor.getDepartment()));
            sb.append(String.format(" Room Number    : %s\n", assignedDoctor.getRoomNumber()));
            sb.append(String.format(" Consultation Fee: Rs. %.2f\n", assignedDoctor.getConsultationFee()));
        } else {
            sb.append(" [No primary doctor assigned yet]\n");
        }
        sb.append("-------------------------------------------------------\n");
        sb.append(" APPOINTMENT HISTORY & SCHEDULE:\n");
        if (appointments.isEmpty()) {
            sb.append(" [No appointments scheduled]\n");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment a = appointments.get(i);
                sb.append(String.format("  [%d] Appt ID: %s | Date: %s | Slot: %s\n", 
                        (i + 1), a.getAppointmentId(), a.getDate(), a.getTimeSlot()));
                sb.append(String.format("      Purpose: %s | Status: %s\n", a.getPurpose(), a.getStatus()));
                if (a.getDoctor() != null) {
                    sb.append(String.format("      Attending Doctor: Dr. %s (%s)\n", 
                            a.getDoctor().getName(), a.getDoctor().getSpecialization()));
                }
            }
        }
        sb.append("=======================================================\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + " | " + name + " | Age: " + age + " | Diagnosis: " + diagnosis 
                + " | Doctor: " + (assignedDoctor != null ? assignedDoctor.getName() : "None")
                + " | Appts: " + appointments.size();
    }
}

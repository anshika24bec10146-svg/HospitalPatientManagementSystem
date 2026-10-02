package model;

/**
 * Appointment class to represent a scheduled consultation between a Patient and a Doctor.
 * Demonstrates object association and state management.
 * 
 * @author Anshika (Reg No: 24BEC10146)
 */
public class Appointment {
    private String appointmentId;
    private String patientId;
    private String patientName;
    private Doctor doctor;
    private String date;
    private String timeSlot;
    private String status; // "Scheduled", "Completed", "Cancelled"
    private String purpose;

    // Default constructor
    public Appointment() {
        this.status = "Scheduled";
    }

    // Parameterized constructor
    public Appointment(String appointmentId, String patientId, String patientName, Doctor doctor, 
                       String date, String timeSlot, String purpose) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctor = doctor;
        this.date = date;
        this.timeSlot = timeSlot;
        this.purpose = purpose;
        this.status = "Scheduled";
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void completeAppointment() {
        this.status = "Completed";
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
    }

    public void displayAppointmentDetails() {
        System.out.println("  - [Appointment #" + appointmentId + "] Date: " + date + " " + timeSlot 
                + " | Doctor: " + (doctor != null ? "Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")" : "Not assigned")
                + " | Purpose: " + purpose
                + " | Status: " + status);
    }

    @Override
    public String toString() {
        return "Appt ID: " + appointmentId + " | Patient: " + patientName + " (" + patientId + ")"
                + " | Doctor: " + (doctor != null ? doctor.getName() : "None")
                + " | Date: " + date + " " + timeSlot + " | Status: " + status;
    }
}

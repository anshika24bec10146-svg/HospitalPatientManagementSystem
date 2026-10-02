package test;

import model.Appointment;
import model.Doctor;
import model.Patient;
import service.FileStorage;
import service.HospitalService;

/**
 * ValidationTest provides an automated testing suite to verify all
 * functional requirements, object interactions, state updates, and reporting
 * without requiring external testing dependencies.
 * 
 * Complies with Section 3 ("Testing wherever applicable") of the
 * VITyarthi project guidelines.
 * 
 * Course: Programming in Java
 * @author Anshika Jain (Reg No: 24BEC10146)
 */
public class ValidationTest {

    private static int testsPassed = 0;
    private static int totalTests = 0;

    private static void assertEquals(String testName, Object expected, Object actual) {
        totalTests++;
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("  [FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
        }
    }

    private static void assertTrue(String testName, boolean condition) {
        totalTests++;
        if (condition) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("  [FAIL] " + testName + " | Condition was false");
        }
    }

    public static void runAllTests() {
        System.out.println("=======================================================");
        System.out.println("       HOSPITAL PATIENT MANAGEMENT SYSTEM TEST SUITE   ");
        System.out.println("=======================================================");

        HospitalService service = new HospitalService();

        // Test 1: Verify Initial Seeding
        System.out.println("\nExecuting Test Suite 1: Data Initialization & Model Creation...");
        assertTrue("Doctor list initialized", service.getAllDoctors().size() >= 3);
        assertTrue("Patient list initialized", service.getAllPatients().size() >= 3);

        // Test 2: Register New Patient
        System.out.println("\nExecuting Test Suite 2: Patient Registration...");
        String nextId = service.generateNextPatientId();
        Patient newPatient = new Patient(nextId, "Kavita Rao", 29, "9887766554", "Female", "AB+", "Fever", "OPD");
        boolean regOk = service.registerPatient(newPatient);
        assertTrue("Register patient status", regOk);
        Patient foundPatient = service.findPatientById(nextId);
        assertTrue("Patient lookup by ID", foundPatient != null);
        assertEquals("Patient name matching", "Kavita Rao", foundPatient.getName());
        assertEquals("Patient diagnosis matching", "Fever", foundPatient.getDiagnosis());

        // Test 3: Updating Patient Diagnosis
        System.out.println("\nExecuting Test Suite 3: Diagnosis Update Operation...");
        boolean updateOk = service.updatePatientDiagnosis(nextId, "Viral Dengue Fever - Recovering");
        assertTrue("Diagnosis update execution", updateOk);
        assertEquals("Updated diagnosis check", "Viral Dengue Fever - Recovering", foundPatient.getDiagnosis());

        // Test 4: Assign Doctor & Object Interaction
        System.out.println("\nExecuting Test Suite 4: Doctor-Patient Association...");
        Doctor doc = service.findDoctorById("DOC-01");
        boolean assignOk = service.assignDoctorToPatient(nextId, "DOC-01");
        assertTrue("Doctor assignment execution", assignOk);
        assertEquals("Assigned doctor verification", doc.getName(), foundPatient.getAssignedDoctor().getName());

        // Test 5: Scheduling Appointment
        System.out.println("\nExecuting Test Suite 5: Appointment Scheduling...");
        Appointment appt = service.scheduleAppointment(nextId, "DOC-01", "2025-05-02", "11:00 AM", "Post-Fever Followup");
        assertTrue("Appointment created", appt != null);
        assertEquals("Appointment patient verification", nextId, appt.getPatientId());
        assertTrue("Patient contains new appointment", foundPatient.getAppointments().contains(appt));

        // Test 6: Report Generation
        System.out.println("\nExecuting Test Suite 6: Patient Summary Report...");
        String summary = service.getPatientSummary(nextId);
        assertTrue("Summary contains patient name", summary.contains("Kavita Rao"));
        assertTrue("Summary contains diagnosis", summary.contains("Viral Dengue Fever - Recovering"));
        assertTrue("Summary contains doctor name", summary.contains("Dr. Rajesh Sharma"));
        assertTrue("Summary contains appointment ID", summary.contains(appt.getAppointmentId()));

        // Test 7: Persistence Test
        System.out.println("\nExecuting Test Suite 7: File I/O Persistence...");
        boolean savePatientsOk = FileStorage.savePatients(service.getAllPatients());
        boolean saveApptsOk = FileStorage.saveAppointments(service.getAllAppointments());
        assertTrue("Patients saved to file", savePatientsOk);
        assertTrue("Appointments saved to file", saveApptsOk);

        // Print Final Summary
        System.out.println("\n=======================================================");
        System.out.println(String.format(" Test Results: %d/%d Tests Passed (%.1f%%)", 
                testsPassed, totalTests, ((double) testsPassed / totalTests) * 100.0));
        System.out.println("=======================================================");
    }

    public static void main(String[] args) {
        runAllTests();
    }
}

package model;

/**
 * Doctor class representing medical professionals in the hospital.
 * Inherits common person traits from Person.java.
 * 
 * @author Anshika (Reg No: 24BEC10146)
 */
public class Doctor extends Person {
    private String specialization;
    private String department;
    private double consultationFee;
    private String roomNumber;

    // Default constructor
    public Doctor() {
        super();
    }

    // Parameterized constructor
    public Doctor(String id, String name, int age, String phone, String gender,
                  String specialization, String department, double consultationFee, String roomNumber) {
        super(id, name, age, phone, gender);
        this.specialization = specialization;
        this.department = department;
        this.consultationFee = consultationFee;
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Overriding abstract method from Person
    @Override
    public void displayInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("Doctor Details:");
        System.out.println("ID             : " + id);
        System.out.println("Name           : Dr. " + name);
        System.out.println("Age/Gender     : " + age + " / " + gender);
        System.out.println("Specialization : " + specialization);
        System.out.println("Department     : " + department);
        System.out.println("Room Number    : " + roomNumber);
        System.out.println("Consultation Fee: Rs. " + consultationFee);
        System.out.println("Contact        : " + phone);
        System.out.println("--------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Dr. " + name + " (" + specialization + ", Dept: " + department + ", Room: " + roomNumber + ")";
    }
}

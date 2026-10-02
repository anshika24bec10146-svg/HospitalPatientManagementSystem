package model;

/**
 * Abstract class representing a generic Person in the Hospital Management System.
 * Demonstrates inheritance and encapsulation by providing common attributes and methods
 * for both Patients and Doctors.
 * 
 * Course: Programming in Java
 * @author Anshika Jain (Reg No: 24BEC10146)
 */
public abstract class Person {
    // Common protected attributes accessible to subclasses
    protected String id;
    protected String name;
    protected int age;
    protected String phone;
    protected String gender;

    // Default constructor
    public Person() {
    }

    // Parameterized constructor
    public Person(String id, String name, int age, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0 && age < 130) {
            this.age = age;
        } else {
            System.out.println("Invalid age provided. Age must be between 1 and 130.");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Abstract method to be implemented by child classes (Polymorphism)
    public abstract void displayInfo();

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Gender: " + gender + " | Phone: " + phone;
    }
}

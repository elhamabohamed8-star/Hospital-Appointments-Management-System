
package main;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
public class Main {

    private static HospitalSystem hospitalSystem = new HospitalSystem();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Hospital Appointments Management System ===");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Doctor");
            System.out.println("3. Login as Patient");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1 -> adminLogin();
                case 2 -> doctorLogin();
                case 3 -> patientLogin();
                case 4 -> {
                    hospitalSystem.saveAllData(); System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice.");
            }
        } while (choice!= 4);
        input.close();
    }

    private static int getIntInput() {
        try { return Integer.parseInt(input.nextLine()); } catch (NumberFormatException e) { return -1; }
    }

    private static void adminLogin() {
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        Admin admin = hospitalSystem.findAdminByUsername(user);
        if (admin!= null && admin.getPassword().equals(pass)) {
            System.out.println("Welcome, " + admin.getName()); adminMenu(admin);
        } else System.out.println("Invalid admin credentials.");
    }

    private static void adminMenu(Admin admin) {
        int choice;
        do {
            System.out.println("\n--- Admin Menu --- Logged in as: " + admin.getName());
            System.out.println("1. Add Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. Assign Patient to Doctor");
            System.out.println("4. Create Appointment");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Patients");
            System.out.println("7. View All Appointments");
            System.out.println("8. Search Patient by ID");
            System.out.println("9. Search Doctor by ID");
            System.out.println("10. Generate Reports");
            System.out.println("11. Save Data");
            System.out.println("12. Logout");
            System.out.print("Choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1 -> addDoctor(admin);
                case 2 -> registerPatient(admin);
                case 3 -> assignPatient(admin);
                case 4 -> createAppointment(admin);
                case 5 -> admin.viewAllDoctors(hospitalSystem);
                case 6 -> admin.viewAllPatients(hospitalSystem);
                case 7 -> admin.viewAllAppointments(hospitalSystem);
                case 8 -> searchPatientById(admin);
                case 9 -> searchDoctorById(admin);
                case 10 -> admin.generateReports(hospitalSystem);
                case 11 -> admin.saveData(hospitalSystem);
                case 12 -> System.out.println("Logged out.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice!= 12);
    }

    private static void addDoctor(Admin admin) {
        System.out.print("Doctor ID: "); String id = input.nextLine();
        System.out.print("Name: "); String name = input.nextLine();
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        System.out.print("Specialization: "); String spec = input.nextLine();
        System.out.print("Department: "); String dept = input.nextLine();
        System.out.print("Phone: "); String phone = input.nextLine();
        Doctor newDoc = new Doctor(id, name, user, pass, spec, dept, phone);
        admin.addNewDoctor(hospitalSystem, newDoc);
    }

    private static void registerPatient(Admin admin) {
        System.out.print("Patient ID: "); String id = input.nextLine();
        System.out.print("Name: "); String name = input.nextLine();
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        System.out.print("Age: "); int age = getIntInput();
        System.out.print("Gender: "); String gender = input.nextLine();
        System.out.print("Phone: "); String phone = input.nextLine();
        Patient newPat = new Patient(id, name, user, pass, age, gender, phone, "None");
        admin.registerNewPatient(hospitalSystem, newPat);
    }

    private static void assignPatient(Admin admin) {
        System.out.print("Patient ID: "); String pid = input.nextLine();
        System.out.print("Doctor ID: "); String did = input.nextLine();
        admin.assignPatientToDoctor(hospitalSystem, pid, did);
    }

    private static void createAppointment(Admin admin) {
        System.out.print("Patient ID: "); String pid = input.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = input.nextLine();
        System.out.print("Time (HH:MM): "); String time = input.nextLine();
        admin.createAppointment(hospitalSystem, pid , date, time);
    }

    private static void searchPatientById(Admin admin) {
        System.out.print("Enter Patient ID: "); String pid = input.nextLine();
        admin.searchPatientById(hospitalSystem, pid);
    }

    private static void searchDoctorById(Admin admin) {
        System.out.print("Enter Doctor ID: "); String did = input.nextLine();
        admin.searchDoctorById(hospitalSystem, did);
    }

    private static void doctorLogin() {
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        Doctor doctor = hospitalSystem.findDoctorByUsername(user);
        if (doctor!= null && doctor.getPassword().equals(pass)) {
            System.out.println("Welcome, Dr. " + doctor.getName()); doctorMenu(doctor);
        } else System.out.println("Invalid credentials.");
    }

    private static void doctorMenu(Doctor doctor) {
        int choice;
        do {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View My Profile");
            System.out.println("2. View Assigned Patients");
            System.out.println("3. View My Appointments");
            System.out.println("4. Update Appointment Status");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1 -> doctor.displayInfo();
                case 2 -> doctor.viewAssignedPatients(hospitalSystem);
                case 3 -> doctor.viewAppointments(hospitalSystem);
                case 4 -> updateAppointmentStatus(doctor);
                case 5 -> System.out.println("Logged out.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice!= 5);
    }

    private static void updateAppointmentStatus(Doctor doctor) {
        System.out.print("Appointment ID: "); String aid = input.nextLine();
        System.out.print("New Status (Confirmed/Completed/Cancelled): "); String status = input.nextLine();
        doctor.updateAppointmentStatus(hospitalSystem, aid, status);
    }

    private static void patientLogin() {
        System.out.print("Username: "); String user = input.nextLine();
        System.out.print("Password: "); String pass = input.nextLine();
        Patient patient = hospitalSystem.findPatientByUsername(user);
        if (patient!= null && patient.getPassword().equals(pass)) {
            System.out.println("Welcome, " + patient.getName()); patientMenu(patient);
        } else System.out.println("Invalid credentials.");
    }

    private static void patientMenu(Patient patient) {
        int choice;
        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View My Profile");
            System.out.println("2. View Assigned Doctor");
            System.out.println("3. View My Appointments");
            System.out.println("4. Book Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            choice = getIntInput();
            switch (choice) {
                case 1 -> patient.displayInfo();
                case 2 -> patient.viewAssignedDoctor(hospitalSystem);
                case 3 -> patient.viewAppointments(hospitalSystem);
                case 4 -> bookAppointment(patient);
                case 5 -> cancelAppointment(patient);
                case 6 -> System.out.println("Logged out.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice!= 6);
    }

    private static void bookAppointment(Patient patient) {
        System.out.print("Date (YYYY-MM-DD): "); String date = input.nextLine();
        System.out.print("Time (HH:MM): "); String time = input.nextLine();
        patient.bookAppointment(hospitalSystem, date, time);
    }

    private static void cancelAppointment(Patient patient) {
        System.out.print("Appointment ID: "); String aid = input.nextLine();
        patient.cancelAppointment(hospitalSystem, aid);
    }
}


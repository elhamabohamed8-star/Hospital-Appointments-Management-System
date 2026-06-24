/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author YAHOO COMPUTER
 */
class Admin extends User {
    private String role;

    public Admin(String id, String name, String username, String password, String phoneNumber, String role) {
        super(id, name, username, password, phoneNumber);
        this.role = role;
    }

    public String getRole() { return role; }

    public void addNewDoctor(HospitalSystem system, Doctor doctor) {
        if (system.findDoctorById(doctor.getId())!= null) {
            System.out.println("Error: Doctor ID already exists.");
            return;
        }
        system.getDoctors().add(doctor);
        System.out.println("Doctor added successfully: " + doctor.getName());
    }

    public void registerNewPatient(HospitalSystem system, Patient patient) {
        if (system.findPatientById(patient.getId())!= null) {
            System.out.println("Error: Patient ID already exists.");
            return;
        }
        system.getPatients().add(patient);
        System.out.println("Patient registered successfully: " + patient.getName());
    }

    public void assignPatientToDoctor(HospitalSystem system, String patientId, String doctorId) {
        Patient p = system.findPatientById(patientId);
        Doctor d = system.findDoctorById(doctorId);
        if (p == null) { System.out.println("Error: Patient not found."); return; }
        if (d == null) { System.out.println("Error: Doctor not found."); return; }
        p.setAssignedDoctorId(doctorId);
        d.addPatientId(patientId);
        System.out.println("Patient " + p.getName() + " assigned to Dr. " + d.getName());
    }

    public void createAppointment(HospitalSystem system, String patientId , String date, String time) {
        String result = system.createAppointment(patientId , date, time);
        System.out.println(result);
    }

    public void viewAllAppointments(HospitalSystem system) {
        System.out.println("\n--- All Appointments ---");
        if (system.getAppointments().isEmpty()) System.out.println("No appointments found.");
        else for (Appointment a : system.getAppointments()) a.displayAppointment();
    }

    public void viewAllDoctors(HospitalSystem system) {
        System.out.println("\n--- All Doctors ---");
        if (system.getDoctors().isEmpty()) System.out.println("No doctors found.");
        else for (Doctor d : system.getDoctors()) d.displayInfo();
    }

    public void viewAllPatients(HospitalSystem system) {
        System.out.println("\n--- All Patients ---");
        if (system.getPatients().isEmpty()) System.out.println("No patients found.");
        else for (Patient p : system.getPatients()) p.displayInfo();
    }

    public void searchPatientById(HospitalSystem system, String patientId) {
        Patient p = system.findPatientById(patientId);
        if (p!= null) { System.out.println("\nPatient Found:"); p.displayInfo(); }
        else System.out.println("Patient with ID " + patientId + " not found.");
    }

    public void searchDoctorById(HospitalSystem system, String doctorId) {
        Doctor d = system.findDoctorById(doctorId);
        if (d!= null) { System.out.println("\nDoctor Found:"); d.displayInfo(); }
        else System.out.println("Doctor with ID " + doctorId + " not found.");
    }

    public void saveData(HospitalSystem system) { system.saveAllData(); }
    public void loadData(HospitalSystem system) { system.loadAllData(); }
    public void generateReports(HospitalSystem system) { system.generateReports(); }

    
    @Override
    public void displayInfo() {
        System.out.println("--- Admin Profile ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
        System.out.println("Phone: " + phoneNumber);
    }

   
    @Override
    public String toFileString() {
        return id + "," + name + "," + username + "," + password + "," + phoneNumber + "," + role;
    }
}










 

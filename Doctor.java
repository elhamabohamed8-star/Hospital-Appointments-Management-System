/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author YAHOO COMPUTER
 */
class Doctor extends User {
    private String specialization;
    private String department;
    private ArrayList<String> assignedPatientIds;
    private ArrayList<String> appointmentIds;
    
    public Doctor(String id, String name, String username, String password, 
        String specialization, String department, String phoneNumber) {
        super(id, name, username, password, phoneNumber);
        this.specialization = specialization;
        this.department = department;
        this.assignedPatientIds = new ArrayList<>();
        this.appointmentIds = new ArrayList<>();
    }
    
    public String getSpecialization() {
        return specialization;
    }
    public String getDepartment() { 
        return department; 
    }
    public ArrayList<String> getAssignedPatientIds() {
        return assignedPatientIds; 
    }
    public ArrayList<String> getAppointmentIds() { 
        return appointmentIds; 
    }
    

    public void setSpecialization(String specialization) { 
        this.specialization = specialization; 
    }
    public void setDepartment(String department) { 
        this.department = department; 
    }
    
    public void addPatientId(String patientId) {
        if (!assignedPatientIds.contains(patientId)) {
            assignedPatientIds.add(patientId);
        }
    }
    
    public void addAppointmentId(String appointmentId) {
        if (!appointmentIds.contains(appointmentId)) {
            appointmentIds.add(appointmentId);
        }
    }
    
    
    @Override
    public void displayInfo() {
        System.out.println("--- Doctor Profile ---");
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Specialization: " + specialization);
        System.out.println("Department: " + department);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Total Number Assigned Patients: " + assignedPatientIds.size());
        System.out.println("Total Number Appointments: " + appointmentIds.size());
    }
    
    public void viewAssignedPatients(HospitalSystem system) {
        System.out.println("\n--- Assigned Patients ---");
        if (assignedPatientIds.isEmpty()){ 
            System.out.println("No patients assigned.");
        }
        else {
            for (String patientId : assignedPatientIds) {
            Patient p = system.findPatientById(patientId);
            if (p!= null){ 
                p.displayInfo();
            }
        }
        }
    }
    
    public void viewAppointments(HospitalSystem system) {
        System.out.println("\n--- My Appointments ---");
        if (appointmentIds.isEmpty()) {
            System.out.println("No appointments found.");
        }
        else{
             for (String appId : appointmentIds) {
            Appointment a = system.findAppointmentById(appId);
            if (a!= null) a.displayAppointment();
        }
        }
    }
    
    public void updateAppointmentStatus(HospitalSystem system, String appointmentId, String newStatus) {
        Appointment app = system.findAppointmentById(appointmentId);
        if (app == null) { System.out.println("Error: Appointment not found."); return; }
        if (!app.getDoctorID().equals(this.id)) { System.out.println("Error: This appointment does not belong to you."); return; }
        if (!newStatus.equals("Confirmed") &&!newStatus.equals("Completed") &&!newStatus.equals("Cancelled")) {
            System.out.println("Error: Invalid status. Use Confirmed, Completed, or Cancelled."); return;
        }
        if (app.getStatus().equals("Cancelled") && newStatus.equals("Completed")) {
            System.out.println("Error: A cancelled appointment cannot be marked as completed."); return;
        }
        app.setStatus(newStatus);
        System.out.println("Appointment " + appointmentId + " status updated to: " + newStatus);
    }
    
    
    @Override
    public String toFileString() {
        return id + "," + name + "," + username + "," + password + "," + 
               specialization + "," + department + "," + phoneNumber;
    }
}




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
    class Patient extends User {
    private int age;
    private String gender;
    private String assignedDoctorId;
    private ArrayList<String> appointmentIds;
    
    public Patient(String id, String name, String username, String password, 
                   int age, String gender, String phoneNumber, String assignedDoctorId) {
        super(id, name, username, password, phoneNumber);
        this.age = age;
        this.gender = gender;
        this.assignedDoctorId = assignedDoctorId;
        this.appointmentIds = new ArrayList<>();
    }
    
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getAssignedDoctorId() { return assignedDoctorId; }
    public ArrayList<String> getAppointmentIds() { return appointmentIds; }
    
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAssignedDoctorId(String assignedDoctorId) { this.assignedDoctorId = assignedDoctorId; }
    
    public void addAppointmentId(String appointmentId) {
        if (!appointmentIds.contains(appointmentId)) appointmentIds.add(appointmentId);
    }
    
    @Override
    public void displayInfo() {
        System.out.println("--- Patient Profile ---");
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Assigned Doctor ID: " + assignedDoctorId);
        System.out.println("Total Appointments: " + appointmentIds.size());
    }
    
    public void viewAssignedDoctor(HospitalSystem system) {
        if (assignedDoctorId.equals("None")) { System.out.println("No doctor assigned yet."); return; }
        Doctor doc = system.findDoctorById(assignedDoctorId);
        if (doc!= null) { System.out.println("\n--- Assigned Doctor ---"); doc.displayInfo(); }
        else System.out.println("Assigned doctor not found.");
    }
    
    public void viewAppointments(HospitalSystem system) {
        System.out.println("\n--- My Appointments ---");
        if (appointmentIds.isEmpty()) System.out.println("No appointments found.");
        else for (String appId : appointmentIds) {
            Appointment a = system.findAppointmentById(appId);
            if (a!= null) a.displayAppointment();
        }
    }
    
    public void bookAppointment(HospitalSystem system, String date, String time) {
        String result = system.createAppointment(this.id, date, time);
        System.out.println(result);
    }
    
    public void cancelAppointment(HospitalSystem system, String appointmentId) {
        Appointment app = system.findAppointmentById(appointmentId);
        if (app == null) { System.out.println("Error: Appointment not found."); return; }
        if (!app.getPatientID().equals(this.id)) { System.out.println("Error: This appointment does not belong to you."); return; }
        if (app.getStatus().equals("Completed")) { System.out.println("Error: Cannot cancel a completed appointment."); return; }
        app.setStatus("Cancelled");
        System.out.println("Appointment " + appointmentId + " cancelled successfully.");
    }
    
    @Override
    public String toFileString() {
        return id + "," + name + "," + username + "," + password + "," + 
               age + "," + gender + "," + phoneNumber + "," + assignedDoctorId;
    }
}




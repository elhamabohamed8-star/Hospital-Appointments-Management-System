/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author YAHOO COMPUTER
 */
public class Appointment {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private String date;
    private String time;
    private String status;
    
    public Appointment(String appointmentID, String patientID, String doctorID, 
                       String date, String time, String status) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.date = date;
        this.time = time;
        this.status = status;
    }
    
    public String getAppointmentID() { return appointmentID; }
    public String getPatientID() { return patientID; }
    public String getDoctorID() { return doctorID; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) {
        if (this.status.equals("Cancelled") && status.equals("Completed")) {
            System.out.println("Error: A cancelled appointment cannot be marked as completed.");
            return;
        }
        this.status = status;
    }
    
    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            System.out.println("Error: Appointment date cannot be empty.");
            return;
        }
        this.date = date;
    }
    
    public void setTime(String time) {
        if (time == null || time.trim().isEmpty()) {
            System.out.println("Error: Appointment time cannot be empty.");
            return;
        }
        this.time = time;
    }
    
    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentID + 
                         " | Patient: " + patientID + 
                         " | Doctor: " + doctorID + 
                         " | Date: " + date + 
                         " | Time: " + time + 
                         " | Status: " + status);
    }
    
    public String toFileString() {
        return appointmentID + "," + patientID + "," + doctorID + "," + date + "," + time + "," + status;
    }
}







/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author YAHOO COMPUTER
 */
public class HospitalSystem {
    private ArrayList<Admin> admins;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;
    private int appointmentCounter = 1;

    public HospitalSystem() {
    admins = new ArrayList<>();
    doctors = new ArrayList<>();
    patients = new ArrayList<>();
    appointments = new ArrayList<>();
    
    try {
        if (FileManager.loadFromFile("users.txt").isEmpty()) {
            admins.add(new Admin("A001", "System Admin", "admin", "123", "0100000000", "System"));
        }
        loadAllData();
    } catch (IOException e) {
        System.out.println("Error during initialization: " + e.getMessage());
    }
}

    public ArrayList<Admin> getAdmins() { return admins; }
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public ArrayList<Patient> getPatients() { return patients; }
    public ArrayList<Appointment> getAppointments() { return appointments; }

    public void loadAllData() {
        try {
            ArrayList<String[]> adminData = FileManager.loadFromFile("users.txt");
            for (String[] a : adminData) if (a.length == 6) admins.add(new Admin(a[0], a[1], a[2], a[3], a[4], a[5]));

            ArrayList<String[]> docData = FileManager.loadFromFile("doctors.txt");
            for (String[] d : docData) if (d.length == 7) doctors.add(new Doctor(d[0], d[1], d[2], d[3], d[4], d[5], d[6]));

            ArrayList<String[]> patData = FileManager.loadFromFile("patients.txt");
            for (String[] p : patData) if (p.length == 8) {
                Patient patient = new Patient(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]), p[5], p[6], p[7]);
                patients.add(patient);
                if (!p[7].equals("None")) {
                    Doctor doc = findDoctorById(p[7]);
                    if (doc!= null) doc.addPatientId(p[0]);
                }
            }

            ArrayList<String[]> appData = FileManager.loadFromFile("appointments.txt");
            for (String[] a : appData) if (a.length == 6) {
                Appointment app = new Appointment(a[0], a[1], a[2], a[3], a[4], a[5]);
                appointments.add(app);
                Doctor doc = findDoctorById(a[2]);
                if (doc!= null) doc.addAppointmentId(a[0]);
                Patient pat = findPatientById(a[1]);
                if (pat!= null) pat.addAppointmentId(a[0]);
                appointmentCounter++;
            }
            System.out.println("Data loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void saveAllData() {
        try {
            FileManager.saveAdmins(admins);
            FileManager.saveDoctors(doctors);
            FileManager.savePatients(patients);
            FileManager.saveAppointments(appointments);
            System.out.println("Data saved successfully.");
        } catch (IOException e) { System.out.println("Error saving: " + e.getMessage()); }
    }

    public Admin findAdminByUsername(String username) {
        for (Admin a : admins) if (a.getUsername().equals(username)) return a;
        return null;
    }
    public Doctor findDoctorById(String id) {
        for (Doctor d : doctors) if (d.getId().equals(id)) return d;
        return null;
    }
    public Doctor findDoctorByUsername(String username) {
        for (Doctor d : doctors) if (d.getUsername().equals(username)) return d;
        return null;
    }
    public Patient findPatientById(String id) {
        for (Patient p : patients) if (p.getId().equals(id)) return p;
        return null;
    }
    public Patient findPatientByUsername(String username) {
        for (Patient p : patients) if (p.getUsername().equals(username)) return p;
        return null;
    }
    /*public Appointment findAppointmentById(String id) {
        for (Appointment a : appointments) if (a.getAppointmentID().equals(id)) return a;
        return null;
    }*/
    
    public Appointment findAppointmentById(String id) {
    // حول الـ ID اللي جاي من اليوزر لإنجليزي الأول
    String searchId = id.replace("٠","0").replace("١","1").replace("٢","2")
                        .replace("٣","3").replace("٤","4").replace("٥","5")
                        .replace("٦","6").replace("٧","7").replace("٨","8")
                        .replace("٩","9");
                        
    for (Appointment a : appointments) {
        String currentId = a.getAppointmentID().replace("٠","0").replace("١","1").replace("٢","2")
                            .replace("٣","3").replace("٤","4").replace("٥","5")
                            .replace("٦","6").replace("٧","7").replace("٨","8")
                            .replace("٩","9");
                            
        if (currentId.equals(searchId)) return a;
    }
    return null;
}

    public String createAppointment(String patientId, String date, String time) {
        Patient p = findPatientById(patientId);
        if (p == null) return "Error: Patient not found.";
        if (p.getAssignedDoctorId().equals("None")) return "Error: Patient must be assigned to a doctor first.";
        if (date.trim().isEmpty()) return "Error: Appointment date cannot be empty.";
        if (time.trim().isEmpty()) return "Error: Appointment time cannot be empty.";
        String doctorId = p.getAssignedDoctorId();
        for (Appointment a : appointments) {
            if (a.getDoctorID().equals(doctorId) && a.getDate().equals(date) &&
                a.getTime().equals(time) &&!a.getStatus().equals("Cancelled")) {
                return "Error: Doctor already has an appointment at this date and time.";
            }
        }
        String appId = "A" + String.format("%03d", appointmentCounter++);
        Appointment newApp = new Appointment(appId, patientId, doctorId, date, time, "Confirmed");
        appointments.add(newApp);
        Doctor doc = findDoctorById(doctorId);
        if (doc!= null) doc.addAppointmentId(appId);
        p.addAppointmentId(appId);
        return "Appointment created successfully: " + appId;
    }

    public void generateReports() {
        System.out.println("\n========== Hospital System Reports ==========");
        System.out.println("\n--- Total Numbers ---");
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Patients: " + patients.size());
        System.out.println("\n--- Appointments by Status ---");
        int confirmed = 0, completed = 0, cancelled = 0;
        for (Appointment a : appointments) {
            switch (a.getStatus()) {
                case "Confirmed": confirmed++; break;
                case "Completed": completed++; break;
                case "Cancelled": cancelled++; break;
            }
        }
        System.out.println("Total Appointments: " + appointments.size());
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Completed: " + completed);
        System.out.println("Cancelled: " + cancelled);
        System.out.println("\n--- Top 3 Doctors by Appointments ---");
        if (doctors.isEmpty()) System.out.println("No doctors found.");
        else {
            ArrayList<Doctor> sortedDoctors = new ArrayList<>(doctors);
            sortedDoctors.sort((d1, d2) -> {
                int count1 = 0, count2 = 0;
                for (Appointment a : appointments) {
                    if (a.getDoctorID().equals(d1.getId()) &&!a.getStatus().equals("Cancelled")) count1++;
                    if (a.getDoctorID().equals(d2.getId()) &&!a.getStatus().equals("Cancelled")) count2++;
                }
                return Integer.compare(count2, count1);
            });
            int topCount = Math.min(3, sortedDoctors.size());
            for (int i = 0; i < topCount; i++) {
                Doctor doc = sortedDoctors.get(i);
                int appCount = 0;
                for (Appointment a : appointments) {
                    if (a.getDoctorID().equals(doc.getId()) &&!a.getStatus().equals("Cancelled")) appCount++;
                }
                System.out.println((i + 1) + ". Dr. " + doc.getName() +
                                 " | Specialization: " + doc.getSpecialization() +
                                 " | Appointments: " + appCount);
            }
        }
        System.out.println("==============================================");
    }
}













    


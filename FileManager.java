/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author YAHOO COMPUTER
 */
public class FileManager {
    
    public static void saveAdmins(ArrayList<Admin> admins) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt"))) {
            for (Admin a : admins) writer.println(a.toFileString());
        }
    }
    
    public static void saveDoctors(ArrayList<Doctor> doctors) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("doctors.txt"))) {
            for (Doctor d : doctors) writer.println(d.toFileString());
        }
    }
    
    public static void savePatients(ArrayList<Patient> patients) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("patients.txt"))) {
            for (Patient p : patients) writer.println(p.toFileString());
        }
    }
    
    public static void saveAppointments(ArrayList<Appointment> appointments) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("appointments.txt"))) {
            for (Appointment a : appointments) writer.println(a.toFileString());
        }
    }
    
    public static ArrayList<String[]> loadFromFile(String filename) throws IOException {
        ArrayList<String[]> data = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) return data;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
           while ((line = reader.readLine())!= null) {
                if (!line.trim().isEmpty()) data.add(line.split(","));
          
   
           }
        }
 return data;
    }
    }


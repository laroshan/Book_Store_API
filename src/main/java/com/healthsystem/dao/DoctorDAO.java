package com.healthsystem.dao;

import com.healthsystem.entity.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private static DoctorDAO instance;
    private List<Doctor> doctors;

    // Private constructor to prevent direct instantiation
    private DoctorDAO() {
        doctors = new ArrayList<>();
        // Create some fake doctors with unique IDs
        doctors.add(new Doctor("Doc1", "Dr. Alice Johnson", "123-456-7891", "123 Health St., Wellness City", "Dermatology"));
        doctors.add(new Doctor("Doc2", "Dr. Bob Smith", "234-567-8902", "234 Health St., Wellness City", "Cardiology"));
        doctors.add(new Doctor("Doc3", "Dr. Charlie Brown", "345-678-9013", "345 Health St., Wellness City", "Pediatrics"));
    }

    // Public method to provide access to the singleton instance
    public static DoctorDAO getInstance() {
        if (instance == null) {
            instance = new DoctorDAO();
        }
        return instance;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void updateDoctor(String id, Doctor updatedDoctor) {
        Doctor existing = getDoctorById(id);
        int index = doctors.indexOf(existing);
        doctors.set(index, updatedDoctor);
    }

    public void deleteDoctor(String id) {
        Doctor existing = getDoctorById(id);
        doctors.remove(existing);
    }
}

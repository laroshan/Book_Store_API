package com.healthsystem.dao;

import com.healthsystem.entity.Doctor;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DoctorDAO {
    private List<Doctor> doctors = new ArrayList<>();
    public DoctorDAO() {
        // Create some fake doctors with unique IDs
        doctors.add(new Doctor(UUID.randomUUID().toString(), "Dr. Alice Johnson", "123-456-7891", "123 Health St., Wellness City", "Dermatology"));
        doctors.add(new Doctor(UUID.randomUUID().toString(), "Dr. Bob Smith", "234-567-8902", "234 Health St., Wellness City", "Cardiology"));
        doctors.add(new Doctor(UUID.randomUUID().toString(), "Dr. Charlie Brown", "345-678-9013", "345 Health St., Wellness City", "Pediatrics"));
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(String id) {
        Optional<Doctor> doctor = doctors.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
        if (doctor.isPresent()) {
            return doctor.get();
        } else {
            throw new HealthSystemException("Doctor not found", Response.Status.NOT_FOUND);
        }
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

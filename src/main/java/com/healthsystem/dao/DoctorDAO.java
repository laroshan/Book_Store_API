package com.healthsystem.dao;

import com.healthsystem.entity.Doctor;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDAO {
    private List<Doctor> doctors = new ArrayList<>();

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

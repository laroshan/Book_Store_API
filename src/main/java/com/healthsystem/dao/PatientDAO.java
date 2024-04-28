package com.healthsystem.dao;

import com.healthsystem.entity.Patient;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAO {
    private static PatientDAO instance;
    private List<Patient> patients;

    // Private constructor to prevent instantiation
    private PatientDAO() {
        this.patients = new ArrayList<>();
    }

    // Singleton instance getter
    public static PatientDAO getInstance() {
        if (instance == null) {
            instance = new PatientDAO();
        }
        return instance;
    }

    public void addPatient(Patient patient) {
        if (patients.stream().anyMatch(p -> p.getId().equals(patient.getId()))) {
            throw new HealthSystemException("Duplicate ID: " + patient.getId(), Response.Status.CONFLICT);
        }
        patients.add(patient);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public Patient getPatientById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new HealthSystemException("Patient not found", Response.Status.NOT_FOUND));
    }

    public void updatePatient(String id, Patient updatedPatient) {
        Patient existing = getPatientById(id);
        int index = patients.indexOf(existing);
        patients.set(index, updatedPatient);
    }

    public void deletePatient(String id) {
        Patient existing = getPatientById(id);
        patients.remove(existing);
    }
}
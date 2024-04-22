package com.healthsystem.dao;

import com.healthsystem.entity.Patient;
import com.healthsystem.exception.HealthSystemException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAO {
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientById(String id) {
        Optional<Patient> patient = patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (patient.isPresent()) {
            return patient.get();
        } else {
            throw new HealthSystemException("Patient not found", Response.Status.NOT_FOUND);
        }
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

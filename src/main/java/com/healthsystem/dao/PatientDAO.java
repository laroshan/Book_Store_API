package com.healthsystem.dao;

import com.healthsystem.entity.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientById(int id) {
        return patients.stream()
                .filter(patient -> patient.getName().hashCode() == id)
                .findFirst()
                .orElse(null);
    }

    public void updatePatient(int id, Patient updatedPatient) {
        int index = -1;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getName().hashCode() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            patients.set(index, updatedPatient);
        }
    }

    public void deletePatient(int id) {
        patients.removeIf(patient -> patient.getName().hashCode() == id);
    }
}

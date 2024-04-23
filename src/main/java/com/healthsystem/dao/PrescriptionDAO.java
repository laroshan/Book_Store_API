package com.healthsystem.dao;

import com.healthsystem.entity.Prescription;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrescriptionDAO {
    private  List<Prescription> prescriptions;

    public PrescriptionDAO() {
        this.prescriptions = new ArrayList<>();
    }

    // Add a new prescription
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    // Get all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public Prescription getPrescriptionById(String id) {
        Optional<Prescription> prescription = prescriptions.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (prescription.isPresent()) {
            return prescription.get();
        } else {
            throw new HealthSystemException("Prescription not found", Response.Status.NOT_FOUND);
        }
    }

    public void updatePrescription(String id, Prescription updatedPrescription) {
        Prescription existing = getPrescriptionById(id);
        int index = prescriptions.indexOf(existing);
        prescriptions.set(index, updatedPrescription);
    }

    public void deletePrescription(String id) {
        Prescription existing = getPrescriptionById(id);
        prescriptions.remove(existing);
    }
}

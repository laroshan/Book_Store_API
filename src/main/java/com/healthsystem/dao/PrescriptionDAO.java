package com.healthsystem.dao;

import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {
    private static PrescriptionDAO instance;
    private List<Prescription> prescriptions;

    private PrescriptionDAO() {
        this.prescriptions = new ArrayList<>();
    }

    public static PrescriptionDAO getInstance() {
        if (instance == null) {
            instance = new PrescriptionDAO();
        }
        return instance;
    }

    public void addPrescription(Prescription prescription) {
        if (prescriptions.stream().anyMatch(p -> p.getId().equals(prescription.getId()))) {
            throw new HealthSystemException("Duplicate ID: " + prescription.getId(), Response.Status.CONFLICT);
        }
        prescriptions.add(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        List<Prescription> detailedPrescriptions = new ArrayList<>();

        for (Prescription prescription : prescriptions) {
            Doctor doctor = doctorDAO.getDoctorById(prescription.getDoctorId());
            Patient patient = patientDAO.getPatientById(prescription.getPatientId());

            prescription.setDoctor(doctor);
            prescription.setPatient(patient);

            detailedPrescriptions.add(prescription);
        }

        return detailedPrescriptions;
    }

    public Prescription getPrescriptionById(String id) {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();

        Prescription prescription = prescriptions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new HealthSystemException("Prescription not found", Response.Status.NOT_FOUND));

        prescription.setDoctor(doctorDAO.getDoctorById(prescription.getDoctorId()));
        prescription.setPatient(patientDAO.getPatientById(prescription.getPatientId()));

        return prescription;
    }

    public void updatePrescription(String id, Prescription updatedPrescription) {
        if (!id.equals(updatedPrescription.getId())) {
            throw new HealthSystemException("ID mismatch", Response.Status.BAD_REQUEST);
        }

        Prescription existing = getPrescriptionById(id);
        int index = prescriptions.indexOf(existing); // Corrected
        prescriptions.set(index, updatedPrescription);
    }

    public void deletePrescription(String id) {
        Prescription existing = getPrescriptionById(id);
        prescriptions.remove(existing);
    }
}

package com.healthsystem.dao;

import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    private static BillingDAO instance;
    private List<Billing> billings;

    private BillingDAO() {
        this.billings = new ArrayList<>();
    }

    public static BillingDAO getInstance() {
        if (instance == null) {
            instance = new BillingDAO();
        }
        return instance;
    }

    public void addBilling(Billing billing) {
        if (billings.stream().anyMatch(b -> b.getId().equals(billing.getId()))) {
            throw new HealthSystemException("Duplicate ID: " + billing.getId(), Response.Status.CONFLICT);
        }
        billings.add(billing);
    }

    public List<Billing> getAllBillings() {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        List<Billing> detailedBillings = new ArrayList<>();

        for (Billing billing : billings) {
            Doctor doctor = doctorDAO.getDoctorById(billing.getDoctorId());
            Patient patient = patientDAO.getPatientById(billing.getPatientId());

            billing.setDoctor(doctor);
            billing.setPatient(patient);

            detailedBillings.add(billing);
        }

        return detailedBillings;
    }

    public Billing getBillingById(String id) {

        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        Billing billing = billings.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new HealthSystemException("Billing not found", Response.Status.NOT_FOUND));

        Doctor doctor = doctorDAO.getDoctorById(billing.getDoctorId());
        Patient patient = patientDAO.getPatientById(billing.getPatientId());

        billing.setDoctor(doctor);
        billing.setPatient(patient);

        return billing;
    }

    public void updateBilling(String id, Billing updatedBilling) {
        if (!id.equals(updatedBilling.getId())) {
            throw new HealthSystemException("ID mismatch", Response.Status.BAD_REQUEST);
        }

        Billing existing = getBillingById(id);
        int index = billings.indexOf(existing);
        billings.set(index, updatedBilling);
    }

    public void deleteBilling(String id) {
        Billing existing = getBillingById(id);
        billings.remove(existing);
    }
}

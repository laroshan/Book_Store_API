package com.healthsystem.dao;

import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    private static MedicalRecordDAO instance;
    private List<MedicalRecord> medicalRecords;

    private MedicalRecordDAO() {
        this.medicalRecords = new ArrayList<>();
    }

    public static MedicalRecordDAO getInstance() {
        if (instance == null) {
            instance = new MedicalRecordDAO();
        }
        return instance;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecords.stream().anyMatch(mr -> mr.getId().equals(medicalRecord.getId()))) {
            throw new HealthSystemException("Duplicate ID: " + medicalRecord.getId(), Response.Status.CONFLICT);
        }
        medicalRecords.add(medicalRecord);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        List<MedicalRecord> detailedMedicalRecords = new ArrayList<>();

        for (MedicalRecord medicalRecord : medicalRecords) {
            Doctor doctor = doctorDAO.getDoctorById(medicalRecord.getDoctorId());
            Patient patient = patientDAO.getPatientById(medicalRecord.getPatientId());

            medicalRecord.setDoctor(doctor);
            medicalRecord.setPatient(patient);

            detailedMedicalRecords.add(medicalRecord);
        }

        return detailedMedicalRecords;
    }

    public MedicalRecord getMedicalRecordById(String id) {

        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        MedicalRecord medicalRecord = medicalRecords.stream()
                .filter(mr -> mr.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new HealthSystemException("Medical Record not found", Response.Status.NOT_FOUND));

        Doctor doctor = doctorDAO.getDoctorById(medicalRecord.getDoctorId());
        Patient patient = patientDAO.getPatientById(medicalRecord.getPatientId());

        medicalRecord.setDoctor(doctor);
        medicalRecord.setPatient(patient);

        return medicalRecord;
    }

    public void updateMedicalRecord(String id, MedicalRecord updatedRecord) {
        if (!id.equals(updatedRecord.getId())) {
            throw new HealthSystemException("ID mismatch", Response.Status.BAD_REQUEST);
        }

        MedicalRecord existing = getMedicalRecordById(id);
        int index = medicalRecords.indexOf(existing);
        medicalRecords.set(index, updatedRecord);
    }

    public void deleteMedicalRecord(String id) {
        MedicalRecord existing = getMedicalRecordById(id);
        medicalRecords.remove(existing);
    }
}

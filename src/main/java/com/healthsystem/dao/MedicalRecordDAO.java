package com.healthsystem.dao;

import com.healthsystem.entity.MedicalRecord;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalRecordDAO {
    private  List<MedicalRecord> medicalRecords;

    public MedicalRecordDAO() {
        this.medicalRecords = new ArrayList<>();
    }

    // Create a new medical record
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    // Read all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }

    public MedicalRecord getMedicalRecordById(String id) {
        Optional<MedicalRecord> record = medicalRecords.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        if (record.isPresent()) {
            return record.get();
        } else {
            throw new HealthSystemException("Medical record not found", Response.Status.NOT_FOUND);
        }
    }

    public void updateMedicalRecord(String id, MedicalRecord updatedRecord) {
        MedicalRecord existing = getMedicalRecordById(id);
        int index = medicalRecords.indexOf(existing);
        medicalRecords.set(index, updatedRecord);
    }

    public void deleteMedicalRecord(String id) {
        MedicalRecord existing = getMedicalRecordById(id);
        medicalRecords.remove(existing);
    }

}

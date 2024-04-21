package com.healthsystem.entity;

public class MedicalRecord {
    private int id;
    private Patient patient;
    private String diagnosis;
    private String treatment;

    public MedicalRecord(int id, Patient patient, String diagnosis, String treatment) {
        this.id = id;
        this.patient = patient;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }
}

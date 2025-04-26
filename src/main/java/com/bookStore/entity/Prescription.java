package com.bookStore.entity;

public class Prescription {
    private String id;
    private String doctorId;
    private String patientId;
    private String medication;
    private String dosage;
    private String duration;

    private Doctor doctor;
    private Patient patient;

    public Prescription() {
    }

    public Prescription(String id, String doctorId, String patientId, String medication, String dosage,
            String duration) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

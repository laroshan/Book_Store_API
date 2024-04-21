package com.healthsystem.entity;

public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String medication;
    private String dosage;
    private String instructions;
    private int duration;

    public Prescription(int id, Patient patient, Doctor doctor, String medication, String dosage, String instructions, int duration) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getMedication() {
        return medication;
    }

    public String getDosage() {
        return dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getDuration() {
        return duration;
    }
}

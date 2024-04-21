package com.healthsystem.entity;

public class Patient extends Person {
    private String medicalHistory;
    private String currentHealthStatus;

    public Patient(String name, String contact, String address, String medicalHistory, String currentHealthStatus) {
        super(name, contact, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}

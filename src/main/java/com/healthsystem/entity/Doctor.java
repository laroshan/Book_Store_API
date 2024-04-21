package com.healthsystem.entity;

public class Doctor extends Person {
    private String specialty;

    public Doctor(String name, String contact, String address, String specialty) {
        super(name, contact, address);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}

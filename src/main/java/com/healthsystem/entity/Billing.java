package com.healthsystem.entity;

import java.time.LocalDateTime;

public class Billing {
    private String id;
    private Patient patient;
    private Appointment appointment;
    private Doctor doctor;
    private String invoiceNumber;
    private LocalDateTime transactionDate;
    private double amount;
    private String paymentStatus;

    public Billing(String id, Patient patient, Appointment appointment, Doctor doctor, String invoiceNumber, LocalDateTime transactionDate, double amount, String paymentStatus) {
        this.id = id;
        this.patient = patient;
        this.appointment = appointment;
        this.doctor = doctor;
        this.invoiceNumber = invoiceNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

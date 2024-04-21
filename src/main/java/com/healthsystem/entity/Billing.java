package com.healthsystem.entity;

import java.time.LocalDateTime;

public class Billing {
    private int id;
    private Patient patient;
    private Appointment appointment;
    private Doctor doctor;
    private String invoiceNumber;
    private LocalDateTime transactionDate;
    private double amount;
    private String paymentStatus;

    public Billing(int id, Patient patient, Appointment appointment, Doctor doctor, String invoiceNumber, LocalDateTime transactionDate, double amount, String paymentStatus) {
        this.id = id;
        this.patient = patient;
        this.appointment = appointment;
        this.doctor = doctor;
        this.invoiceNumber = invoiceNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}

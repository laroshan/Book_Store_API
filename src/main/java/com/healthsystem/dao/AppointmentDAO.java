package com.healthsystem.dao;

import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private static AppointmentDAO instance;
    private List<Appointment> appointments;

    private AppointmentDAO() {
        this.appointments = new ArrayList<>();
    }

    public static AppointmentDAO getInstance() {
        if (instance == null) {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    public void addAppointment(Appointment appointment) {
        if (appointments.stream().anyMatch(a -> a.getId().equals(appointment.getId()))) {
            throw new HealthSystemException("Duplicate ID: " + appointment.getId(), Response.Status.CONFLICT);
        }
        appointments.add(appointment);
    }

    public List<Appointment> getAllAppointments() {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();
        List<Appointment> detailedAppointments = new ArrayList<>();

        // Populate the doctor and patient details for each appointment
        for (Appointment appointment : appointments) {
            Doctor doctor = doctorDAO.getDoctorById(appointment.getDoctorId());
            Patient patient = patientDAO.getPatientById(appointment.getPatientId());

            appointment.setDoctor(doctor);
            appointment.setPatient(patient);

            detailedAppointments.add(appointment);
        }

        return detailedAppointments;
    }

    public Appointment getAppointmentById(String id) {
        DoctorDAO doctorDAO = DoctorDAO.getInstance();
        PatientDAO patientDAO = PatientDAO.getInstance();

        Appointment appointment = appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new HealthSystemException("Appointment not found", Response.Status.NOT_FOUND));

        // Set the full doctor and patient details
        appointment.setDoctor(doctorDAO.getDoctorById(appointment.getDoctorId()));
        appointment.setPatient(patientDAO.getPatientById(appointment.getPatientId()));

        return appointment;
    }

    public void updateAppointment(String id, Appointment updatedAppointment) {
        if (!id.equals(updatedAppointment.getId())) {
            throw new HealthSystemException("ID mismatch", Response.Status.BAD_REQUEST);
        }

        Appointment existing = getAppointmentById(id);
        int index = appointments.indexOf(existing);
        appointments.set(index, updatedAppointment);
    }

    public void deleteAppointment(String id) {
        Appointment existing = getAppointmentById(id);
        appointments.remove(existing);
    }
}

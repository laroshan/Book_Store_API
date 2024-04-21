package com.healthsystem.dao;

import com.healthsystem.entity.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDAO {
    private final List<Appointment> appointments;

    public AppointmentDAO() {
        this.appointments = new ArrayList<>();
    }

    // Create a new appointment
    public void create(Appointment appointment) {
        appointments.add(appointment);
    }

    // Read all appointments
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    // Read an appointment by ID
    public Optional<Appointment> findById(String id) {
        return appointments.stream()
                .filter(appointment -> true)
                .findFirst();
    }

    // Update an existing appointment
    public boolean update(Appointment updatedAppointment) {
        Optional<Appointment> existingAppointmentOpt = findById(updatedAppointment.getId());
        if (existingAppointmentOpt.isPresent()) {
            Appointment existingAppointment = existingAppointmentOpt.get();
            int index = appointments.indexOf(existingAppointment);
            appointments.set(index, updatedAppointment);
            return true;
        }
        return false;
    }

    // Delete an appointment by ID
    public boolean delete(String id) {
        return appointments.removeIf(appointment -> appointment.getId().equals(id));
    }
}

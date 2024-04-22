package com.healthsystem.dao;

import com.healthsystem.entity.Appointment;
import com.healthsystem.exception.HealthSystemException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDAO {
    private final List<Appointment> appointments;

    public AppointmentDAO() {
        this.appointments = new ArrayList<>();
    }

    // Create a new appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Read all appointments
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    // Read an appointment by ID
    public Appointment getAppointmentById(String id) {
        Optional<Appointment> appointment = appointments.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        if (appointment.isPresent()) {
            return appointment.get();
        } else {
            throw new HealthSystemException("Appointment not found", Response.Status.NOT_FOUND);
        }
    }

    // Update an existing appointment
    public void updateAppointment(String id, Appointment updatedAppointment) {
        Appointment existing = getAppointmentById(id); // This throws an exception if not found
        int index = appointments.indexOf(existing);
        appointments.set(index, updatedAppointment);
    }

    public void deleteAppointment(String id) {
        Appointment existing = getAppointmentById(id);
        appointments.remove(existing);
    }
}

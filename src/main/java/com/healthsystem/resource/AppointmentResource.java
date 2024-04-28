package com.healthsystem.resource;

import com.healthsystem.dao.*;
import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/appointments")
public class AppointmentResource {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentResource.class);
    private AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (Exception e) {
            logger.error("Error adding appointment: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding appointment").build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("id") String id) {
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(id);
            return Response.ok(appointment).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving appointment: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") String id, Appointment updatedAppointment) {
        try {
            if (!id.equals(updatedAppointment.getId())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID mismatch").build();
            }

            appointmentDAO.updateAppointment(id, updatedAppointment);
            return Response.ok("Appointment updated successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating appointment: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteAppointment(@PathParam("id") String id) {
        try {
            appointmentDAO.deleteAppointment(id);
            return Response.ok("Appointment deleted successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting appointment: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found").build();
        }
    }
}

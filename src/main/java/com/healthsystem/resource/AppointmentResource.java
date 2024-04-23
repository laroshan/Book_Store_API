package com.healthsystem.resource;

import com.healthsystem.entity.Appointment;
import com.healthsystem.dao.AppointmentDAO;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/appointments")
public class AppointmentResource {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static final Logger logger = LoggerFactory.getLogger(AppointmentResource.class);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (Exception e) {
            logger.error("Error adding appointment: {}", e.getMessage(), e);
            throw new HealthSystemException("Error adding appointment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

//    @GET
//    @Path("/getAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Appointment> getAllAppointments() {
//        return appointmentDAO.getAllAppointments();
//    }
@GET
@Path("/getAll")
@Produces(MediaType.APPLICATION_JSON)
public String getAllAppointments() {
    return "helloworld";
}

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentById(@PathParam("id") String id) {
        try {
            return appointmentDAO.getAppointmentById(id);
        } catch (HealthSystemException e) {
            logger.error("Error retrieving appointment: {}", e.getMessage());
            throw e;
        }
    }
    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") String id, Appointment updatedAppointment) {
        try {
            appointmentDAO.updateAppointment(id, updatedAppointment);
            logger.info("Appointment updated: {}", updatedAppointment);
            return Response.ok("Appointment updated successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating appointment: {}", e.getMessage());
            throw e;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteAppointment(@PathParam("id") String id) {
        try {
            appointmentDAO.deleteAppointment(id);
            logger.info("Appointment deleted with ID: {}", id);
            return Response.ok("Appointment deleted successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting appointment: {}", e.getMessage());
            throw e;
        }
    }}

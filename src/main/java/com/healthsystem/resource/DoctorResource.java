package com.healthsystem.resource;

import com.healthsystem.dao.DoctorDAO;
import com.healthsystem.entity.Doctor;
import com.healthsystem.exception.HealthSystemException;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/doctors")
public class DoctorResource {
    private static final Logger logger = LoggerFactory.getLogger(DoctorResource.class);
    private DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (Exception e) {
            logger.error("Error adding doctor: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding doctor").build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") String id) {
        try {
            Doctor doctor = doctorDAO.getDoctorById(id);
            return Response.ok(doctor).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving doctor: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") String id, Doctor updatedDoctor) {
        try {
            if (!id.equals(updatedDoctor.getId())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID mismatch").build();
            }

            doctorDAO.updateDoctor(id, updatedDoctor);
            return Response.ok("Doctor updated successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating doctor: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteDoctor(@PathParam("id") String id) {
        try {
            doctorDAO.deleteDoctor(id);
            return Response.ok("Doctor deleted successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting doctor: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found").build();
        }
    }
}

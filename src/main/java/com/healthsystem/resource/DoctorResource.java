package com.healthsystem.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthsystem.dao.DoctorDAO;
import com.healthsystem.entity.Doctor;
import com.healthsystem.exception.HealthSystemException;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/doctors")
public class DoctorResource {
    private DoctorDAO doctorDAO = new DoctorDAO();
    private static final Logger logger = LoggerFactory.getLogger(DoctorResource.class);


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (Exception e) {
            logger.error("Error adding doctor: {}", e.getMessage(), e);
            throw new HealthSystemException("Error adding doctor", Response.Status.INTERNAL_SERVER_ERROR);
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
            logger.error("Error retrieving doctor with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") String id, Doctor updatedDoctor) {
        try {
            doctorDAO.updateDoctor(id, updatedDoctor);
            return Response.noContent().build();
        } catch (HealthSystemException e) {
            logger.error("Error updating doctor with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteDoctor(@PathParam("id") String id) {
        try {
            doctorDAO.deleteDoctor(id);
            return Response.noContent().build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting doctor with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }}

package com.healthsystem.resource;

import com.healthsystem.dao.PatientDAO;
import com.healthsystem.entity.Patient;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/patients")
public class PatientResource {
    private PatientDAO patientDAO = new PatientDAO();
    private static final Logger logger =  LoggerFactory.getLogger(PatientResource.class);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            patientDAO.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity(patient).build();
        } catch (Exception e) {
            logger.error("Error adding patient: {}", e.getMessage(), e);
            throw new HealthSystemException("Error adding patient", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("id") String id) {
        try {
            Patient patient = patientDAO.getPatientById(id);
            return Response.ok(patient).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving patient with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") String id, Patient updatedPatient) {
        try {
            patientDAO.updatePatient(id, updatedPatient);
            return Response.noContent().build();
        } catch (HealthSystemException e) {
            logger.error("Error updating patient with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deletePatient(@PathParam("id") String id) {
        try {
            patientDAO.deletePatient(id);
            return Response.noContent().build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting patient with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }}

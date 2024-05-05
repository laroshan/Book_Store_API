package com.healthsystem.resource;

import com.healthsystem.entity.Patient;
import com.healthsystem.dao.PatientDAO;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/patients")
public class PatientResource {
    private PatientDAO patientDAO = PatientDAO.getInstance(); // Singleton instance
    private static final Logger logger = LoggerFactory.getLogger(PatientResource.class);

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
            return Response.ok(patientDAO.getPatientById(id)).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving patient: {}", e.getMessage());
            throw e;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") String id, Patient updatedPatient) {
        try {
            patientDAO.updatePatient(id, updatedPatient);
            return Response.ok("Patient updated successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating patient: {}", e.getMessage());
            throw e;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deletePatient(@PathParam("id")String id) {
        try {
            patientDAO.deletePatient(id);
            return Response.ok("Patient deleted successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting patient: {}", e.getMessage());
            throw e;
        }
    }
}
package com.healthsystem.resource;

import com.healthsystem.dao.*;
import com.healthsystem.entity.*;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/prescriptions")
public class PrescriptionResource {
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDAO prescriptionDAO = PrescriptionDAO.getInstance();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity(prescription).build();
        } catch (Exception e) {
            logger.error("Error adding prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding prescription").build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAO.getAllPrescriptions();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("id") String id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            return Response.ok(prescription).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving prescription: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") String id, Prescription updatedPrescription) {
        try {
            if (!id.equals(updatedPrescription.getId())) {
                return Response.status(Response.Status.BAD_REQUEST). entity("ID mismatch"). build();
            }

            prescriptionDAO.updatePrescription(id, updatedPrescription);
            return Response.ok("Prescription updated successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating prescription: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found"). build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deletePrescription(@PathParam("id") String id) {
        try {
            prescriptionDAO.deletePrescription(id);
            return Response.ok("Prescription deleted successfully"). build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting prescription: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND). entity("Prescription not found"). build();
        }
    }
}

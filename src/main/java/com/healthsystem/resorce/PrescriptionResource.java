package com.healthsystem.resorce;

import com.healthsystem.dao.PrescriptionDAO;
import com.healthsystem.entity.Prescription;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/prescriptions")
public class PrescriptionResource {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionResource.class);


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            prescriptionDAO.addPrescription(prescription);
            logger.info("Prescription added: {}", prescription);
            return Response.status(Response.Status.CREATED).entity(prescription).build();
        } catch (HealthSystemException e) {
            logger.error("Error adding prescription: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
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
    public Prescription getPrescriptionById(@PathParam("id") String id) {
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(id);
            logger.info("Retrieved prescription: {}", prescription);
            return prescription;
        } catch (HealthSystemException e) {
            logger.error("Error retrieving prescription: {}", e.getMessage());
            throw  e;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") String id, Prescription updatedPrescription) {
        try {
            prescriptionDAO.updatePrescription(id, updatedPrescription);
            logger.info("Prescription updated: {}", updatedPrescription);
            return Response.ok().entity("Prescription updated successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating prescription: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deletePrescription(@PathParam("id") String id) {
        try {
            prescriptionDAO.deletePrescription(id);
            logger.info("Prescription deleted with ID: {}", id);
            return Response.ok().entity("Prescription deleted successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}

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

@Path("/medicalrecords")
public class MedicalRecordResource {
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordResource.class);
    private MedicalRecordDAO medicalRecordDAO = MedicalRecordDAO.getInstance();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();
        } catch (Exception e) {
            logger.error("Error adding medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding medical record").build();
        }
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordDAO.getAllMedicalRecords();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("id") String id) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(id);
            return Response.ok(medicalRecord).build();
        } catch (HealthSystemException e) {
            logger.error("Error retrieving medical record: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Medical Record not found").build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") String id, MedicalRecord updatedRecord) {
        try {
            if (!id.equals(updatedRecord.getId())) {
                return Response.status(Response.Status.BAD_REQUEST). entity("ID mismatch"). build();
            }

            medicalRecordDAO.updateMedicalRecord(id, updatedRecord);
            return Response.ok("Medical Record updated successfully").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating medical record: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND). entity("Medical Record not found"). build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMedicalRecord(@PathParam("id") String id) {
        try {
            medicalRecordDAO.deleteMedicalRecord(id);
            return Response.ok("Medical Record deleted successfully"). build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting medical record: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND). entity("Medical Record not found"). build();
        }
    }
}

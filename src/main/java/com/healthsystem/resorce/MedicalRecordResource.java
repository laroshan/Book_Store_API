package com.healthsystem.resorce;

import com.healthsystem.dao.MedicalRecordDAO;
import com.healthsystem.entity.MedicalRecord;
import com.healthsystem.exception.HealthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/medicalrecords")
public class MedicalRecordResource {
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordResource.class);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            logger.info("Medical record added: {}", medicalRecord);
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();
        } catch (HealthSystemException e) {
            logger.error("Error adding medical record: {}", e.getMessage());
            throw e;
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
    public MedicalRecord getMedicalRecordById(@PathParam("id") String id) {
        try {
            return medicalRecordDAO.getMedicalRecordById(id);
        } catch (HealthSystemException e) {
            logger.error("Error retrieving medical record: {}", e.getMessage());
            throw e;
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") String id, MedicalRecord updatedMedicalRecord) {
        try {
            medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
            logger.info("Medical record updated: {}", updatedMedicalRecord);
            return Response.ok("Medical record updated successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error updating medical record: {}", e.getMessage());
            throw e;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteMedicalRecord(@PathParam("id") String id) {
        try {
            medicalRecordDAO.deleteMedicalRecord(id);
            logger.info("Medical record deleted with ID: {}", id);
            return Response.ok("Medical record deleted successfully.").build();
        } catch (HealthSystemException e) {
            logger.error("Error deleting medical record: {}", e.getMessage());
            throw e;
        }
    }}
